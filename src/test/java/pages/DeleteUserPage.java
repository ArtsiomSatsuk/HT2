package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteUserPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='main-panel']/form")
    private WebElement formWithTextAboutDeletingUser;

    @FindBy(id = "yui-gen1-button")
    private WebElement buttonYesToDeleteUser;

    public boolean isTextAboutDeletingUserAppears() {
        return formWithTextAboutDeletingUser.getText().contains("Are you sure about deleting the user from Jenkins?");
    }

    public ManageUsersPage clickButtonYesToDeleteUser() {
        buttonYesToDeleteUser.click();
        return new ManageUsersPage();
    }
}
