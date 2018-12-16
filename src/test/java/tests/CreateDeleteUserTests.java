package tests;

import fixture.SuiteFixture;
import org.testng.annotations.Test;
import pages.CreateUserPage;
import pages.DeleteUserPage;
import pages.ManageUsersPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CreateDeleteUserTests extends SuiteFixture {

    @Test(description = "check if new table row appears on manage users page after creating new user")
    public void checkCreatedUserInfoExistence() {
        boolean actualResult = new CreateUserPage().openThisPage().fillInCreateUserForm()
                .clickCreateUserButton().isNewTableRowWithNewUserDisplayed();
        new DeleteUserPage().openThisPage().clickButtonYesToDeleteUser();
        assertTrue(actualResult, "[New table row with new user is not displayed]");
    }

    @Test(description = "check if text appears on delete users page after clicking link delete user")
    public void checkWarningMessage() {
        boolean actualResult = new CreateUserPage().openThisPage().fillInCreateUserForm()
                .clickCreateUserButton().clickLinkDeleteUser().isTextAboutDeletingUserAppears();
        new DeleteUserPage().openThisPage().clickButtonYesToDeleteUser();
        assertTrue(actualResult, "[Text 'Are you sure about deleting the user from Jenkins?' has not been displayed on DeleteUserPage]");
    }

    @Test(description = "check if create user form is displayed properly")
    public void verifyCreateUserForm() {
        new ManageUsersPage().openThisPage().clickLinkCreateUser().isCreateUserFormDisplayedProperly();
    }

    @Test(description = "check if text in table row and link exists after deleting user")
    public void checkInfoExistence() {
        new CreateUserPage().openThisPage().fillInCreateUserForm().clickCreateUserButton()
                .clickLinkDeleteUser().clickButtonYesToDeleteUser().isInfoAboutDeletedUserExists();
    }

    @Test(description = "check that there is no link to delete admin account")
    public void checkAbsenceOfLinkDeleteAdmin() {
        boolean actualResult = new ManageUsersPage().openThisPage().isLinkDeleteAdminExists();
        assertFalse(actualResult, "[Delete admin account link exists on 'Manage Users' page]");
    }

    @Test(description = "check if warning message appears when you try to create account with empty name input")
    public void checkExistenceOfWarningMessage() {
        boolean actualResult = new CreateUserPage().openThisPage()
                .fillInCreateUserFormWithoutName().clickCreateUserButtonAndCheckWarningMessage();
        assertTrue(actualResult, "[Text '\"\" is prohibited as a full name for security reasons.' did not appear after" +
                " attempt to create user without any name]");
    }
}