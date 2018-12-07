package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationPage {

    public static final String USER_NAME = "Artiom";
    public static final String USER_PASSWORD = "2887638";

    protected final String URL = "http://localhost:8080/securityRealm/";

    private WebDriverWait wait;
    private final WebDriver driver;

    // Подготовка элементов страницы.
    @FindBy(css = "input[id*=username]")
    private WebElement username_input;

    @FindBy(css = "input[name*=password]")
    private WebElement password_input;

    @FindBy(name = "Submit")
    private WebElement signIn_button;

    @FindBy(xpath = "//a[@href=\"/manage\"][@class=\"task-link\"]")
    public WebElement linkManageJenkins;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Проверка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Sign in [Jenkins]"))) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    // Заполнение имени.
    public AuthorizationPage setUserName(String value) {
        username_input.clear();
        username_input.sendKeys(value);
        return this;
    }

    // Заполнение пароля.
    public AuthorizationPage setUserPassword(String value) {
        password_input.clear();
        password_input.sendKeys(value);
        return this;
    }

    //Отправка логина и пароля
    public void submitLoginAndPassword() {
        signIn_button.click();
    }

    //Переход на страницу 'Manage Jenkins'
    public ManageJenkinsPage pressManageJenkinsLink() {
        linkManageJenkins.click();
        return new ManageJenkinsPage(driver);
    }

//    public boolean isElementLinkManageJenkinsExists() {
//        if (linkManageJenkins != null) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}