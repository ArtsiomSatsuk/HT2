package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    //Синглтон. Создастся один объект драйвера при первой инициализации, а дальше между тестами будем его передавать.

    private Driver(){}
    private static final int IMPLICIT_WAIT_TIMEOUT = 30;
    private static final int PAGE_LOAD_TIMEOUT = 10;

    public static WebDriver getWebDriverInstance()  {
        WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
            driver.manage().window().maximize();
        return driver;
    }
}