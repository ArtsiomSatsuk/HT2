package pages;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    private static WebDriver driver;

    public AbstractPage() {
        driver = Driver.getWebDriverInstance();
        PageFactory.initElements(driver,this);
    }

    public WebDriver getDriver(){
        return driver;
    }

    public abstract AbstractPage openThisPage();

}
