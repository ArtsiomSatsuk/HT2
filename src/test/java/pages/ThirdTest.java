package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static pages.AuthorizationPage.USER_NAME;
import static pages.AuthorizationPage.USER_PASSWORD;

public class ThirdTest {

    protected final String base_url = "http://localhost:8080/";


    WebDriver driver = null;
    AuthorizationPage loginPage = null;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "D:/ChromeDriver/chromedriver.exe");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));

        driver = new ChromeDriver(capabilities);

        driver.get(base_url);

        loginPage = PageFactory.initElements(driver, AuthorizationPage.class);

        loginPage.setUserName(USER_NAME).setUserPassword(USER_PASSWORD);
        loginPage.submitLoginAndPassword();

        // Если нужно, можно выставить максимальный таймаут по операциям
        // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void secondTest() throws InterruptedException {
        // 1-действие: "Открыть"
        Thread.sleep(500);
        driver.get("http://localhost:8080/securityRealm/");
        Thread.sleep(500);

        // С этого момента можно использовать PajeObject.
        // С применением PageFactory это выглядит так:
        ManageUsersPage page = PageFactory.initElements(driver, ManageUsersPage.class);
        Thread.sleep(500);
        CreateUserPage page1 = page.pressLinkCreateUser();
        Thread.sleep(500);
        Assert.assertTrue(page1.isCreateUserFormDisplayed(), "Form 'Create User' has not been displayed");

        // 1-проверка: "Страница содержит форму с полями «Имя», «Рост», «Вес», радиокнопкой «Пол» и
        // кнопкой отправки данных «Рассчитать». Также на странице есть соответствующие текстовые надписи."

    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

}
