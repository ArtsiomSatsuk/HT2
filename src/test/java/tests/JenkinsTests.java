package tests;

import driver.Driver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.EntryPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static pages.EntryPage.ADMIN_NAME;
import static pages.EntryPage.ADMIN_PASSWORD;

public class JenkinsTests {

    private EntryPage entryPage = null;

    @BeforeSuite
    public void setUp() {
        entryPage = new EntryPage().openThisPage().setUserName(ADMIN_NAME).setUserPassword(ADMIN_PASSWORD);
        entryPage.submitLoginAndPassword();
    }

    @Test(testName = "checkDisplayedElementDtOnManageJenkinsPage")
    public void firstTest() {
        boolean actualResult = entryPage.pressManageJenkinsLink().checkIfDtElementManageUsersDisplayed();
        assertTrue(actualResult, "[Element 'Manage Users' didn't appear on 'Manage Jenkins' page]");
    }

    @Test(testName = "checkDisplayedElementDdOnManageJenkinsPage")
    public void firstTest2() {
        boolean actualResult = entryPage.pressManageJenkinsLink().checkIfDdElementCreateDeleteModifyDisplayed();
        assertTrue(actualResult,
                "[Element with text 'Create/delete/modify users that can log in to this Jenkins' didn't appear on 'Manage Jenkins' page]");
    }

    @Test(testName = "checkCreateUserLinkAvailabilityOnManageUsersPage")
    public void secondTest() {
        boolean actualResult = entryPage.pressManageJenkinsLink()
                .pressManageUsersLink().checkIfLinkCreateUserAvailable();
        assertTrue(actualResult, "[Link 'Create User' on 'Manage Users' page is not available]");
    }

    @Test(testName = "checkCorrectUserFormDisplay")
    public void ThirdTest() {
        boolean actualResult = entryPage.pressManageJenkinsLink().pressManageUsersLink()
                .pressLinkCreateUser().checkIfCreateUserFormDisplayedProperly();
        assertTrue(actualResult, "[Form 'Create User' is not displayed properly]");
    }


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//добавить проверку на уже существующего пользователя
//    @Test(testName = "checkIfNewTableRowAppearsOnManageUsersPageAfterCreatingNewUser")
//    public void FourthTest() {
//        boolean actualResult = entryPage.pressManageJenkinsLink().pressManageUsersLink().pressLinkCreateUser()
//                .fillInCreateUserForm().pressCreateUserButton().checkIfNewTableRowWithNewUserDisplays();
//
//        assertTrue(actualResult, "[New table row with new user is not displayed]");
//    }
//
//    @Test(testName = "checkIfTextAppearsOnDeleteUsersPage")
//    public void FifthTest() {
//        boolean actualResult = entryPage.pressManageJenkinsLink().pressManageUsersLink().pressLinkCreateUser()
//                .fillInCreateUserForm().pressCreateUserButton().pressLinkDeleteUser().checkIfTextAboutDeletingUserAppears();
//        assertTrue(actualResult, "[Text 'Are you sure about deleting the user from Jenkins?' has not been displayed on DeleteUserPage]");
//    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Test(testName = "checkIfAllInformationAboutNewUserDisappearsAfterUserRemoval")
    public void SixthTest() {
        boolean actualResult = entryPage.pressManageJenkinsLink().pressManageUsersLink().pressLinkCreateUser()
                .fillInCreateUserForm().pressCreateUserButton()
                .pressLinkDeleteUser().pressButtonYesToDeleteUser().checkIfLinkToDeleteUserExistsAfterDeletingUser();
        assertFalse(actualResult, "[Table row has not been removed after deleting user]");
    }

    @Test(testName = "checkIfAllInformationAboutNewUserDisappearsAfterUserRemoval")
    public void SixthTest2() {
        boolean actualResult = entryPage.pressManageJenkinsLink().pressManageUsersLink().pressLinkCreateUser()
                .fillInCreateUserForm().pressCreateUserButton()
                .pressLinkDeleteUser().pressButtonYesToDeleteUser().checkIfTableRowExistsAfterDeletingUser();
        assertFalse(actualResult, "[Table row has not been removed after deleting user]");
    }

    @Test(testName = "checkThatThereIsNoLinkToDeleteAdminAccount")
    public void SeventhTest() {
        boolean actualResult = entryPage.pressManageJenkinsLink().pressManageUsersLink().checkIfLinkDeleteAdminExists();
        assertFalse(actualResult, "[link to delete admin account exists on 'Manage Users' page]");
    }

    @Test(testName = "checkIfWarningMessageAppearsWhenYouCreateAccountWithEmptyNameInput")
    public void ninthTest(){
        boolean actualResult = entryPage.pressManageJenkinsLink().pressManageUsersLink()
                .pressLinkCreateUser().fillInCreateUserFormWithoutName()
                .pressCreateUserButtonAndCheckWarningMessage();
        assertTrue(actualResult,
                "[Warning message '\"\" is prohibited as a full name for security reasons.' did not appear after attempt to create user without a name]");
    }

    @Test(testName = "checkIfDisableLinkReplacesEnableLink")
    public void tenthTest() {
        boolean actualResult = entryPage.pressAutoRefreshLink().checkIfAutoRefreshLinksReplaceEachOther();
        assertTrue(actualResult, "[Link Enable/Disable auto refresh don't replace each other]");
    }

    @Test(testName = "checkIfEnableLinkReplacesDisableLink")
    public void tenthTest2() {
        boolean actualResult = entryPage.checkIfAutoRefreshLinksReplaceEachOther();
        assertTrue(actualResult, "[Link Enable/Disable auto refresh don't replace each other]");
    }

    @AfterSuite
    public void close() {
        Driver.getDriver().close();
    }
}