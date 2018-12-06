package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageUsersPage {

    protected final String url = "http://localhost:8080/manage";


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
}
