package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageJenkinsPage {

    public final static String URL_MANAGE_JENKINS = "http://localhost:8080/manage";

    private By dt_manageUsersLocator = By.xpath("//*[@id=\"main-panel\"]/div[16]/a/dl/dt");
    private By dd_textCreateDeleteModifyLocator = By.xpath("//*[@id=\"main-panel\"]/div[16]/a/dl/dd[1]");
    private By linkManageUsersLocator = By.xpath("//*[@id=\"main-panel\"]/div[16]/a");


//    By body_locator = By.xpath("//body");
//    By form_locator = By.xpath("//form[@action='/testlab/wt/index.php']");
//    By username_locator = By.name("name");
//    By weight_locator = By.name("weight");
//    By height_locator = By.name("height");
//    By gender_m_locator = By.xpath("//input[@name='gender'][@value='m']");
//    By gender_f_locator = By.xpath("//input[@name='gender'][@value='f']");
//    By submit_button_locator = By.xpath("//input[@type='submit']");
//    By user_message_locator = By.xpath("//table/tbody/tr[2]/td[2]");
//    By error_message_locator = By.xpath("//form/table/tbody/tr/td");

//    @FindBy(xpath = "//*[@id=\"main-panel\"]/div[16]/a")
//    public WebElement linkManageUsers;

//    @FindBy(xpath = "//*[@id=\"main-panel\"]/div[16]/a/dl/dt")
//    public WebElement dt_textManageUsers;
//
//    @FindBy(xpath = "//*[@id=\"main-panel\"]/div[16]/a/dl/dd[1]")
//    public WebElement dd_textCreateDeleteModify;

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

    //Переход на 'Manage Users' и переход на страницу 'Manage Users'
    public ManageUsersPage pressManageUsersLink() {
        driver.findElement(linkManageUsersLocator).click();
        return new ManageUsersPage(driver);
    }

    //Проверка наличия на странице 'Manage Jenkins' элемента dt с текстом 'Manage Users'
    public boolean isElementDt_textManageUsersDisplayed() {
        return driver.findElement(dt_manageUsersLocator).isDisplayed()
                && driver.findElement(dt_manageUsersLocator).getText().equals("Manage Users");
    }

    //Проверка наличия на странице 'Manage Jenkins' элемента dd с текстом 'Create/delete/modify users that can log in to this Jenkins'
    public boolean isElementDD_textCreateDeleteModifyDisplayed() {
        return driver.findElement(dd_textCreateDeleteModifyLocator).isDisplayed()
                && driver.findElement(dd_textCreateDeleteModifyLocator).getText().equals("Create/delete/modify users that can log in to this Jenkins");
    }
}