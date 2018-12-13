package tests;

import fixture.SuiteFixture;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.EntryPage;

import static org.testng.Assert.assertTrue;

public class FourthAndFifthTests extends SuiteFixture {

    private EntryPage entryPage = new EntryPage();

    @Test(description = "check if new table row appears on manage users page after creating new user")
    public void checkCreatedUserInfoExistence() {
        boolean actualResult = entryPage.clickManageJenkinsLink().clickManageUsersLink().clickLinkCreateUser()
                .fillInCreateUserForm().clickCreateUserButton().isNewTableRowWithNewUserDisplayed();
        assertTrue(actualResult, "[New table row with new user is not displayed]");
    }

    @Test(description = "check if text appears on delete users page after clicking link delete user")
    public void checkWarningMessage() {
        boolean actualResult = entryPage.clickManageJenkinsLink().clickManageUsersLink().clickLinkCreateUser()
                .fillInCreateUserForm().clickCreateUserButton().clickLinkDeleteUser().isTextAboutDeletingUserAppears();
        assertTrue(actualResult, "[Text 'Are you sure about deleting the user from Jenkins?' has not been displayed on DeleteUserPage]");
    }

    @AfterMethod
    public void deleteUserAfterTest() {
        entryPage.openThisPage().clickManageJenkinsLink().clickManageUsersLink()
                .clickLinkDeleteUser().clickButtonYesToDeleteUser();
    }


}
