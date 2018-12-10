package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static driver.Driver.getDriver;

public class EntryPage extends AbstractPage {

    public static final String ADMIN_NAME = "Artiom";
    public static final String ADMIN_PASSWORD = "2887638";

    static final String BASE_URL = "http://localhost:8080/";

    // Подготовка элементов страницы.
    @FindBy(css = "input[id*=username]")
    private WebElement usernameInput;

    @FindBy(css = "input[name*=password]")
    private WebElement passwordInput;

    @FindBy(name = "Submit")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@href=\"/manage\"][@class=\"task-link\"]")
    private WebElement linkManageJenkins;

    @FindBy(css = "a[href*='refresh']")
    private WebElement autoRefreshLink;

    public EntryPage openThisPage() {
        getDriver().get(BASE_URL);
        return this;
    }

    // Заполнение имени.
    public EntryPage setUserName(String value) {
        usernameInput.clear();
        usernameInput.sendKeys(value);
        return this;
    }

    // Заполнение пароля.
    public EntryPage setUserPassword(String value) {
        passwordInput.clear();
        passwordInput.sendKeys(value);
        return this;
    }

    //Отправка логина и пароля
    public void submitLoginAndPassword() {
        signInButton.click();
    }

    //Переход на страницу 'Manage Jenkins'
    public ManageJenkinsPage pressManageJenkinsLink() {
        linkManageJenkins.click();
        return new ManageJenkinsPage();
    }

    //нажать на ссылку 'auto refresh'
    public EntryPage pressAutoRefreshLink() {
        autoRefreshLink.click();
        return this;
    }

    private boolean checkIfDisableReplacesEnableLink() {
        autoRefreshLink.click();
        return autoRefreshLink.getText().equals("DISABLE AUTO REFRESH");
    }

    private boolean checkIfEnableReplacesDisableLink() {
        autoRefreshLink.click();
        System.out.println("First was DISABLE");
        return autoRefreshLink.getText().equals("ENABLE AUTO REFRESH");
    }

    //Проверить меняют ли 'auto refresh' ссылки друг друга
    public boolean checkIfAutoRefreshLinksReplaceEachOther() {
        if (autoRefreshLink.getText().equals("ENABLE AUTO REFRESH")) {
            System.out.println("first was ENABLE");
            return checkIfDisableReplacesEnableLink();
        }
        return checkIfEnableReplacesDisableLink();
    }


}