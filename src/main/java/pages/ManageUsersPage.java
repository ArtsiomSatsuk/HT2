package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageUsersPage {

    public static final String URL_MANAGE_USERS = "http://localhost:8080/securityRealm/";

    By linkCreateUserLocator = By.xpath("//*[@id=\"tasks\"]/div[3]/a[2]");

    private WebDriverWait wait;
    private final WebDriver driver;

    public ManageUsersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Проверка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Users [Jenkins]"))) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    //Нажатие на ссылку 'Create User' и переход на страницу 'Create User'
    public CreateUserPage pressLinkCreateUser(){
        driver.findElement(linkCreateUserLocator).click();
        return new CreateUserPage(driver);
    }



    /////////////////////////////////////////////////////
    //Проверка, доступна ли ссылка 'Create User'
    public boolean isLinkCreateUserAvailable(){
        return driver.findElement(linkCreateUserLocator).isDisplayed();
    }
    /////////////////////////////////////////////////////
}