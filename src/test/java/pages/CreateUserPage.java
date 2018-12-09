package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collection;

public class CreateUserPage extends AbstractPage {

    public static final String CREATE_USER_URL = "http://localhost:8080/securityRealm/addUser";

    public static String NEW_USER_NAME = "NewUserName"; //подумать, может сделать генератор имени

    //Все поля в форме 'CreateUser' с типом текст 'text'
    private By inputFieldsWithTextTypeLocator = By.xpath("//input[@type=\"text\"]");

    //Все поля в форме 'CreateUser' с типом текст 'password'
    private By inputFieldsWithPasswordTypeLocator = By.xpath("//input[@type=\"password\"]");


    @FindBy(xpath = "//*[@id=\"main-panel\"]/form") //++++
    private WebElement formCreateUser;

    @FindBy(css = "#yui-gen1-button") //++++
    private WebElement buttonCreateUser;

    @FindBy(id = "username") //++++
    private WebElement inputUsernameField;

    @FindBy(name = "password1") //++++
    private WebElement inputPasswordField;

    @FindBy(name = "password2") //++++
    private WebElement inputFieldToConfirmPassword;

    @FindBy(xpath = "//input[@name=\"fullname\"]") //++++
    private WebElement inputFullnameField;

    @FindBy(xpath = "//input[@name=\"email\"]") //++++
    private WebElement inputEmailField;

    public CreateUserPage() {
        super();
    }

    @Override
    public CreateUserPage openThisPage() {
        getDriver().get(CREATE_USER_URL);
        return this;
    }

    private Collection<WebElement> textTypeFieldsInForm = getDriver().findElements(inputFieldsWithTextTypeLocator);
    private Collection<WebElement> passwordTypeFieldsInForm = getDriver().findElements(inputFieldsWithPasswordTypeLocator);

    //Появиляется ли форма на странице 'Create User' c тремя полями типа 'text' и двумя полями типа 'password' и все поля пустые
    public boolean isCreateUserFormDisplayedProperly() {
        if ((formCreateUser.isDisplayed()) && (textTypeFieldsInForm.size() == 3) && (passwordTypeFieldsInForm.size() == 2) &&
                (textTypeFieldsInForm.stream().filter(webElement -> webElement.getAttribute("value").equals("")).count() == 3) &&
                (passwordTypeFieldsInForm.stream().filter(webElement -> webElement.getAttribute("value").equals("")).count() == 2)) {
            return true;
        }
        return false;
    }

    // Заполнение поля 'Username'.
    private CreateUserPage inputUsername(String value) {
        inputUsernameField.sendKeys(value);
        return this;
    }

    // Заполнение поля 'Password'.
    private CreateUserPage inputPassword(String value) {
        inputPasswordField.sendKeys(value);
        return this;
    }

    // Заполнение поля 'Confirm password'.
    private CreateUserPage inputPasswordToConfirm(String value) {
        inputFieldToConfirmPassword.sendKeys(value);
        return this;
    }

    // Заполнение поля 'Full name'.
    private CreateUserPage inputFullname(String value) {
        inputFullnameField.sendKeys(value);
        return this;
    }

    // Заполнение поля 'E-mail address'.
    private CreateUserPage inputEmail(String value) {
        inputEmailField.sendKeys(value);
        return this;
    }

    public CreateUserPage fillInCreateUserForm(){
        inputUsername(NEW_USER_NAME);
        inputPassword("12345");
        inputPasswordToConfirm("12345");
        inputFullname(NEW_USER_NAME + " "+NEW_USER_NAME);
        inputEmail(NEW_USER_NAME+"@qqq.by");
        return this;
    }


//    Нажатие на кнопку 'Create user'
    public ManageUsersPage pressCreateUserButton(){
        buttonCreateUser.click();
        return new ManageUsersPage();
    }
}
