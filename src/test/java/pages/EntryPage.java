package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EntryPage extends AbstractPage{

    public static final String USER_NAME = "Artiom";
    public static final String USER_PASSWORD = "2887638";

    static final String BASE_URL = "http://localhost:8080/";

    // Подготовка элементов страницы.
    @FindBy(css = "input[id*=username]")
    private WebElement usernameInput;

    @FindBy(css = "input[name*=password]")
    private WebElement passwordInput;

    @FindBy(name = "Submit")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@href=\"/manage\"][@class=\"task-link\"]")
    private WebElement linkManageJenkins;

    public EntryPage() {
        super();
    }

    @Override
    public EntryPage openThisPage() {
        getDriver().get(BASE_URL);
        return this;
    }

    // Заполнение имени.
    public EntryPage setUserName(String value) {
        usernameInput.clear();
        usernameInput.sendKeys(value);
        return this;
    }

    // Заполнение пароля.
    public EntryPage setUserPassword(String value) {
        passwordInput.clear();
        passwordInput.sendKeys(value);
        return this;
    }

    //Отправка логина и пароля
    public void submitLoginAndPassword() {
        signInButton.click();
    }

    //Переход на страницу 'Manage Jenkins'
    public ManageJenkinsPage pressManageJenkinsLink() {
        linkManageJenkins.click();
        return new ManageJenkinsPage();
    }
}