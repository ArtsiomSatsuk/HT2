package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    //Синглтон. Создастся один объект драйвера при первой инициализации, а дальше между тестами будем его передавать.
    private static WebDriver driver;
//
////    private Driver(){}
//
    private static final int IMPLICIT_WAIT_TIMEOUT = 30;
    private static final int PAGE_LOAD_TIMEOUT = 10;

    public static WebDriver getWebDriverInstance()  {
        if (driver == null){
            System.setProperty("webdriver.chrome.driver", "D:/ChromeDriver/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
            driver.manage().window().maximize();
            System.out.println("[WEB DRIVER CREATED][IMPLICIT_WAIT_TIMEOUT = 30 Sec, PAGE_LOAD_TIMEOUT = 10 Sec]");
        }
        return driver;
    }
}