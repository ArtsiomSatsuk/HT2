package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;

    private static final int PAGE_LOAD_TIMEOUT = 10;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "D:/ChromeDriver/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            System.out.println("[WEB DRIVER CREATED][PAGE_LOAD_TIMEOUT = 10 Sec]");
        }
        return driver;
    }
}