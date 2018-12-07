package pages;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    private final WebDriver driver;

    public AbstractPage()  {
        this.driver = Driver.getWebDriverInstance();
        PageFactory.initElements(driver,this);
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public abstract AbstractPage openPage();

//    public boolean isElementPresent(List<WebElement> element){
//        return element.size() > 0;
//    }
}
