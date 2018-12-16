package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.Collection;

import static constants.Constants.MANAGE_USERS_PAGE_URL;
import static constants.Constants.NEW_USER_NAME;
import static credentials.InputData.ADMIN_NAME;
import static driver.Driver.getDriver;

public class ManageUsersPage extends AbstractPage {

    private String newUserDeleteLinkLocator = String.format("a[href='user/%s/delete']", NEW_USER_NAME.toLowerCase());
    private String linkDeleteAdminLocator = String.format("a[href='user/%s/delete']", ADMIN_NAME.toLowerCase());

    @FindBy(xpath = "//div[@id='side-panel']//a[text()='Create User']")
    private WebElement linkCreateUser;

    private By newUserDeleteLink = By.cssSelector(newUserDeleteLinkLocator);

    private Collection<WebElement> allRowsInUsersTable = getDriver().findElements(By.xpath("//tr/td"));

    public boolean isNewTableRowWithNewUserDisplayed() {
        return allRowsInUsersTable.stream().anyMatch(tableRow -> tableRow.getText().equals(NEW_USER_NAME));
    }

    public CreateUserPage clickLinkCreateUser() {
        linkCreateUser.click();
        return new CreateUserPage();
    }

    public DeleteUserPage clickLinkDeleteUser() {
        getDriver().findElement(newUserDeleteLink).click();
        return new DeleteUserPage();
    }

    public boolean isLinkDeleteAdminExists() {
        return getDriver().findElements(By.cssSelector(linkDeleteAdminLocator)).size() != 0;
    }

    private SoftAssert softAssert = new SoftAssert();

    public void isInfoAboutDeletedUserExists() {
        softAssert.assertFalse(getDriver().findElements(By.cssSelector(newUserDeleteLinkLocator)).size() > 0,
                "[Delete user link exists after deleting this user]");
        softAssert.assertFalse(allRowsInUsersTable.stream().anyMatch(tableRow -> tableRow.getText().equals(NEW_USER_NAME)),
                String.format("[Table row with '%s' exists after deleting this user]", NEW_USER_NAME));
        softAssert.assertAll();
    }

    public boolean isLinkCreateUserAvailable() {
        return linkCreateUser.isEnabled();
    }

    public ManageUsersPage openThisPage() {
        getDriver().navigate().to(MANAGE_USERS_PAGE_URL);
        return this;
    }
}