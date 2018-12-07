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

public class FirstTest {

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
    public void firstTest() throws InterruptedException {
        Thread.sleep(500);
        Assert.assertTrue(loginPage.pressManageJenkinsLink().isElementDt_textManageUsersDisplayed(), "Element 'Manage Users' didn't appear on 'Manage User' page");
        Thread.sleep(500);
        Assert.assertTrue(loginPage.pressManageJenkinsLink().isElementDD_textCreateDeleteModifyDisplayed());
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}