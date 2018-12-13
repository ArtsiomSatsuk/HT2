package tests;

import fixture.SuiteFixture;
import org.testng.annotations.Test;
import pages.EntryPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class JenkinsTests extends SuiteFixture {

    private EntryPage entryPage = new EntryPage();

    @Test(description = "check if elements dt and dd on manage jenkins page are displayed properly")
    public void verifyElementsVisibility() {
        entryPage.clickManageJenkinsLink().isManageUsersElementsDisplayed();
    }

    @Test(description = "check if create user link on manage users page available")
    public void checkCreateUserLinkAvailability() {
        boolean actualResult = entryPage.clickManageJenkinsLink().clickManageUsersLink().isLinkCreateUserAvailable();
        assertTrue(actualResult, "[Link 'Create User' on 'Manage Users' page is not available]");
    }

    @Test(description = "check if create user form is displayed properly")
    public void verifyCreateUserForm() {
        entryPage.clickManageJenkinsLink().clickManageUsersLink()
                .clickLinkCreateUser().isCreateUserFormDisplayedProperly();
    }

    @Test(description = "check if text in table row and link exists after deleting user")
    public void checkInfoExistence() {
        entryPage.clickManageJenkinsLink().clickManageUsersLink().clickLinkCreateUser()
                .fillInCreateUserForm().clickCreateUserButton()
                .clickLinkDeleteUser().clickButtonYesToDeleteUser().isInfoAboutDeletedUserExists();
    }

    @Test(description = "check that there is no link to delete admin account")
    public void checkAbsenceOfLinkDeleteAdmin() {
        boolean actualResult = entryPage.clickManageJenkinsLink().clickManageUsersLink().isLinkDeleteAdminExists();
        assertFalse(actualResult, "[Delete admin account link exists on 'Manage Users' page]");
    }

    @Test(description = "check if warning message appears when you try to create account with empty name input")
    public void checkExistenceOfWarningMessage() {
        boolean actualResult = entryPage.clickManageJenkinsLink().clickManageUsersLink()
                .clickLinkCreateUser().fillInCreateUserFormWithoutName().clickCreateUserButtonAndCheckWarningMessage();
        assertTrue(actualResult,
                "[Text '\"\" is prohibited as a full name for security reasons.' did not appear after attempt to create user without any name]");
    }

    @Test(description = "check if enable and disable auto refresh links replace each other")
    public void checkAutoRefreshLink() {
        entryPage.openThisPage().clickAndCheckAutoRefreshLink().clickAndCheckAutoRefreshLink()
                .clickAndCheckAutoRefreshLink().clickAndCheckAutoRefreshLink();
    }
}