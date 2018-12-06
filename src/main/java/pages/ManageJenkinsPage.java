package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageJenkinsPage {

    protected final String url = "http://localhost:8080/manage";


    @FindBy(xpath = "//a[@href=\"securityRealm/\"][@title=\"Manage Users\"]")
    public WebElement linkManageUsers;

    @FindBy(xpath = "")
    public WebElement dt_textManageUsers;

    @FindBy(xpath = "")
    public WebElement dd_textCreateDeleteModify;

    private WebDriverWait wait;
    private final WebDriver driver;

    public ManageJenkinsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Проверка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Manage Jenkins [Jenkins]"))) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    public ManageUsersPage pressManageJenkinsLink() {
        linkManageUsers.click();
        return new ManageUsersPage(driver);
    }

    public boolean isElementLinkManageUsersExists() {
        if (linkManageUsers != null) {
            return true;
        } else {
            return false;
        }
    }

}