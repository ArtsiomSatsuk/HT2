package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static constants.Constants.DELETE_USER_PAGE_URL;
import static driver.Driver.getDriver;

public class DeleteUserPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='main-panel']/form")
    private WebElement formWithTextAboutDeletingUser;

    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement buttonYesToDeleteUser;

    public boolean isTextAboutDeletingUserAppears() {
        return formWithTextAboutDeletingUser.getText().contains("Are you sure about deleting the user from Jenkins?");
    }

    public ManageUsersPage clickButtonYesToDeleteUser() {
        buttonYesToDeleteUser.click();
        return new ManageUsersPage();
    }

    @Override
    public DeleteUserPage openThisPage() {
        getDriver().navigate().to(DELETE_USER_PAGE_URL);
        return this;
    }
}
