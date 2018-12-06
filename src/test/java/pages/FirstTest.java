package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static pages.AuthorizationPage.USER_NAME;
import static pages.AuthorizationPage.USER_PASSWORD;

public class FirstTest {

    // Это -- т.н. BaseURL, от него будут строиться все относительные URL'ы
    protected final String base_url = "http://localhost:8080/";

    // Сам драйвер
    WebDriver driver = null;
    AuthorizationPage loginPage = null;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "D:/ChromeDriver/chromedriver.exe");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));

        // Запуск драйвера (и браузера)
        driver = new ChromeDriver(capabilities);

        driver.get(base_url);

        loginPage = PageFactory.initElements(driver, AuthorizationPage.class);

        loginPage.setUserName(USER_NAME).setUserPassword(USER_PASSWORD);

        // Если нужно, можно выставить максимальный таймаут по операциям
        // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void firstTest() throws InterruptedException {
        loginPage.submitLoginAndPassword();
        ManageJenkinsPage manageJenkinsPage = loginPage.pressManageJenkinsLink();
        ManageUsersPage manageUsersPage = manageJenkinsPage.pressManageJenkinsLink();

    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}