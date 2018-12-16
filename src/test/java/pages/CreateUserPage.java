package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.Collection;

import static constants.Constants.*;
import static driver.Driver.getDriver;

public class CreateUserPage extends AbstractPage {

    private By inputFieldsWithTextTypeLocator = By.xpath("//input[@type='text']");

    private By inputFieldsWithPasswordTypeLocator = By.xpath("//input[@type='password']");

    @FindBy(xpath = "//*[@id='main-panel']/form")
    private WebElement formCreateUser;

    @FindBy(xpath = "//div[@id='main-panel']//button[text()='Create User']")
    private WebElement buttonCreateUser;

    @FindBy(id = "username")
    private WebElement inputUsernameField;

    @FindBy(name = "password1")
    private WebElement inputPasswordField;

    @FindBy(name = "password2")
    private WebElement inputFieldToConfirmPassword;

    @FindBy(xpath = "//input[@name='fullname']")
    private WebElement inputFullnameField;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement inputEmailField;

    private Collection<WebElement> textTypeFieldsInForm = getDriver().findElements(inputFieldsWithTextTypeLocator);
    private Collection<WebElement> passwordTypeFieldsInForm = getDriver().findElements(inputFieldsWithPasswordTypeLocator);

    private SoftAssert softAssert = new SoftAssert();

    /*
    Verification if 'Create User' form has three fields with 'text' type, two fields with 'password type'
    and all input fields are empty
    */

    public void isCreateUserFormDisplayedProperly() {
        softAssert.assertTrue(formCreateUser.isDisplayed(), "[Form 'Create User' is not displayed on 'Create User' page]");
        softAssert.assertTrue(textTypeFieldsInForm.size() == 3, "[There aren't three input fields with 'text' type in 'Create User' form]");
        softAssert.assertTrue(passwordTypeFieldsInForm.size() == 2, "[There aren't two input fields with 'text' type in 'Create User' form]");
        softAssert.assertTrue(textTypeFieldsInForm.stream().filter(inputField -> inputField.getText().equals("")).count() == 3,
                "[At least one of input fields with 'text' type is not empty in 'Create User' form]");
        softAssert.assertTrue(passwordTypeFieldsInForm.stream().filter(inputField -> inputField.getText().equals("")).count() == 2,
                "[At least one of input fields with 'password' type is not empty in 'Create User' form]");
        softAssert.assertAll();
    }

    private void inputUsername() {
        inputUsernameField.sendKeys(NEW_USER_NAME);
    }

    private void inputPassword() {
        inputPasswordField.sendKeys(NEW_USER_PASSWORD);
    }

    private void inputPasswordToConfirm() {
        inputFieldToConfirmPassword.sendKeys(NEW_USER_PASSWORD);
    }

    private void inputFullName() {
        inputFullnameField.sendKeys(NEW_USER_FULL_NAME);
    }

    private void inputEmail() {
        inputEmailField.sendKeys(NEW_USER_EMAIL);
    }

    public CreateUserPage fillInCreateUserForm() {
        inputUsername();
        inputPassword();
        inputPasswordToConfirm();
        inputFullName();
        inputEmail();
        return this;
    }

    public CreateUserPage fillInCreateUserFormWithoutName() {
        inputPassword();
        inputPasswordToConfirm();
        inputFullName();
        inputEmail();
        return this;
    }

    public boolean clickCreateUserButtonAndCheckWarningMessage() {
        buttonCreateUser.click();
        return getDriver().getPageSource().contains("\"\" is prohibited as a full name for security reasons.");
    }

    public ManageUsersPage clickCreateUserButton() {
        buttonCreateUser.click();
        return new ManageUsersPage();
    }

    @Override
    public CreateUserPage openThisPage() {
        getDriver().navigate().to(CREATE_USER_PAGE_URL);
        return this;
    }
}