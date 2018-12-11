package tests;

import constants.InputParameters;
import driver.Driver;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.EntryPage;

import java.io.IOException;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class JenkinsTests {

    private EntryPage entryPage = null;

    @BeforeSuite
    public void logIn() throws IOException {
        InputParameters.readProperties();
        entryPage = new EntryPage().openThisPage().setAdminName().setAdminPassword();
        entryPage.submitLoginAndPassword();
    }

    @Test(description = "checkIfElementsDtAndDdOnManageJenkinsPageAreDisplayedProperly")
    public void verifyElementsVisibility() {
        entryPage.clickManageJenkinsLink().isManageUsersElementsDisplayed();
    }

    @Test(description = "checkIfCreateUserLinkOnManageUsersPageAvailable")
    public void checkCreateUserLinkAvailability() {
        boolean actualResult = entryPage.clickManageJenkinsLink().clickManageUsersLink().isLinkCreateUserAvailable();
        assertTrue(actualResult, "[Link 'Create User' on 'Manage Users' page is not available]");
    }

    @Test(description = "checkIfCreateUserFormIsDisplayedProperly")
    public void verifyCreateUserForm() {
        entryPage.clickManageJenkinsLink().clickManageUsersLink()
                .clickLinkCreateUser().isCreateUserFormDisplayedProperly();
    }

//----------------------------------------------------------------------------------------------------------------------
//
//    @Test(description = "checkIfNewTableRowAppearsOnManageUsersPageAfterCreatingNewUser", groups = "CreateUser")
//    public void FourthTest() {
//        boolean actualResult = entryPage.clickManageJenkinsLink().clickManageUsersLink().clickLinkCreateUser()
//                .fillInCreateUserForm().clickCreateUserButton().isNewTableRowWithNewUserDisplayed();
//        assertTrue(actualResult, "[New table row with new user is not displayed]");
//
//    }
//
//    @Test(description = "checkIfTextAppearsOnDeleteUsersPageAfterPressingLinkDeleteUser", groups = "CreateUser")
//    public void FifthTest() {
//        boolean actualResult = entryPage.clickManageJenkinsLink().clickManageUsersLink().clickLinkCreateUser()
//                .fillInCreateUserForm().clickCreateUserButton().clickLinkDeleteUser().isTextAboutDeletingUserAppears();
//        assertTrue(actualResult, "[Text 'Are you sure about deleting the user from Jenkins?' has not been displayed on DeleteUserPage]");
//    }

//----------------------------------------------------------------------------------------------------------------------

    @Test(description = "checkIfTextInTableRowAndLinkExistsAfterDeletingUser")
    public void checkInfoExistence() {
        entryPage.clickManageJenkinsLink().clickManageUsersLink().clickLinkCreateUser()
                .fillInCreateUserForm().clickCreateUserButton()
                .clickLinkDeleteUser().clickButtonYesToDeleteUser().isInfoAboutDeletedUserExists();
    }

    @Test(description = "checkThatThereIsNoLinkToDeleteAdminAccount")
    public void checkAbsenceOfLinkDeleteAdmin() {
        boolean actualResult = entryPage.clickManageJenkinsLink().clickManageUsersLink().isLinkDeleteAdminExists();
        assertFalse(actualResult, "[Delete admin account link exists on 'Manage Users' page]");
    }

    @Test(description = "checkIfWarningMessageAppearsWhenYouTryToCreateAccountWithEmptyNameInput")
    public void checkExistenceOfWarningMessage(){
        boolean actualResult = entryPage.clickManageJenkinsLink().clickManageUsersLink()
                .clickLinkCreateUser().fillInCreateUserFormWithoutName().clickCreateUserButtonAndCheckWarningMessage();
        assertTrue(actualResult,
                "[Text '\"\" is prohibited as a full name for security reasons.' did not appear after attempt to create user without any name]");
    }

    @Test(description = "checkIfEnableAndDisableAutoRefreshLinksReplaceEachOther")
    public void checkAutoRefreshLink() {
        entryPage.openThisPage().clickAndCheckAutoRefreshLink().clickAndCheckAutoRefreshLink()
        .clickAndCheckAutoRefreshLink().clickAndCheckAutoRefreshLink();
    }

    @AfterGroups(groups = "CreateUser")
    public void deleteUserAfterTest(){
        entryPage.openThisPage().clickManageJenkinsLink().clickManageUsersLink().clickLinkDeleteUser();
    }

    @AfterSuite
    public void close() {
        Driver.getDriver().close();
    }
}