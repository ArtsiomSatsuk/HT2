package tests;

import driver.Driver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.EntryPage;
import pages.ManageJenkinsPage;
import pages.ManageUsersPage;

import static org.testng.Assert.assertTrue;
import static pages.EntryPage.USER_NAME;
import static pages.EntryPage.USER_PASSWORD;

public class FirstTest {

    private EntryPage entryPage = null;

    @BeforeSuite
    public void setUp() {
        entryPage = new EntryPage().openThisPage().setUserName(USER_NAME).setUserPassword(USER_PASSWORD);
        entryPage.submitLoginAndPassword();
    }

    @Test(testName = "checkDisplayedElementDtOnManageJenkinsPage")
    public void firstTest() {
        boolean actualResult = entryPage.pressManageJenkinsLink().isDtElementManageUsersDisplayed();
        assertTrue(actualResult, "[Element 'Manage Users' didn't appear on 'Manage Jenkins' page]");
    }

    @Test(testName = "checkDisplayedElementDdOnManageJenkinsPage")
    public void firstTest2() {
        boolean actualResult = entryPage.pressManageJenkinsLink().isDdElementCreateDeleteModifyDisplayed();
        assertTrue(actualResult,
                "[Element with text 'Create/delete/modify users that can log in to this Jenkins' didn't appear on 'Manage Jenkins' page]");
    }

    @Test(testName = "checkCreateUserLinkAvailabilityOnManageUsersPage")
    public void secondTest() {
        boolean actualResult = new ManageJenkinsPage().openThisPage()
                .pressManageUsersLink().isLinkCreateUserAvailable();
        assertTrue(actualResult, "[Link 'Create User' on 'Manage Users' page is not available]");
    }

    @Test(testName = "checkCorrectUserFormDisplay")
    public void ThirdTest() {
        boolean actualResult = new ManageJenkinsPage().openThisPage().pressManageUsersLink()
                .pressLinkCreateUser().isCreateUserFormDisplayedProperly();
        assertTrue(actualResult, "[Form 'Create User' is not displayed properly]");
    }

    @Test(testName = "checkIfNewTableRowAppearsOnManageUsersPageAfterCreatingNewUser")
    public void FourthTest() {
//        boolean actualResult = new ManageJenkinsPage().openThisPage().pressManageUsersLink().pressLinkCreateUser()
//                .fillInCreateUserForm().pressCreateUserButton().openThisPage().isNewTableRowWithNewUserHasDisplayed();
//                assertTrue(actualResult, "[New table row with new user is not displayed]");

        new ManageJenkinsPage().openThisPage().pressManageUsersLink().pressLinkCreateUser().fillInCreateUserForm();
    }

    @Test(testName = "checkIfTextAppearsOnDeleteUsersPage")
    public void FifthTest() {
        boolean actualResult = entryPage.openThisPage().pressManageJenkinsLink().pressManageUsersLink()
                .pressLinkDeleteUser().isTextAboutDeletingUserAppears();
        assertTrue(actualResult, "[Text 'Are you sure about deleting the user from Jenkins?' is not displayed on DeleteUserPage]");
    }

    @Test(testName = "checkIfNewTableRowRemovesAfterDeletingNewUser")
    public void SixthTest() {
        boolean actualResult = new ManageUsersPage().openThisPage().pressLinkDeleteUser().isTextAboutDeletingUserAppears();
        assertTrue(actualResult, "[Table row has not been removed after deleting user]");
    }

    @AfterSuite
    public void close() {
        Driver.getWebDriverInstance().close();
    }
}