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
import static pages.ManageJenkinsPage.URL_MANAGE_JENKINS;

public class SecondTest {

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
        Thread.sleep(500);
        driver.get(URL_MANAGE_JENKINS);
        ManageJenkinsPage manageJenkinsPage = PageFactory.initElements(driver, ManageJenkinsPage.class);
        Assert.assertFalse(manageJenkinsPage.pressManageUsersLink().isLinkCreateUserAvailable(), "Link 'Create User' on 'Manage Users' page is not available");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

}
