package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static constants.Constants.ENTRY_PAGE_URL;
import static credentials.InputData.ADMIN_NAME;
import static credentials.InputData.ADMIN_PASSWORD;
import static driver.Driver.getDriver;
import static org.testng.Assert.fail;

public class EntryPage extends AbstractPage {

    @FindBy(css = "input[id*=username]")
    private WebElement usernameInput;

    @FindBy(css = "input[name*=password]")
    private WebElement passwordInput;

    @FindBy(name = "Submit")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@href='/manage'][@class='task-link']")
    private WebElement linkManageJenkins;

    @FindBy(css = "a[href*='refresh']")
    private WebElement autoRefreshLink;

    public EntryPage openThisPage() {
        getDriver().get(ENTRY_PAGE_URL);
        return this;
    }

    public EntryPage setAdminName() {
        usernameInput.clear();
        usernameInput.sendKeys(ADMIN_NAME);
        return this;
    }

    public EntryPage setAdminPassword() {
        passwordInput.clear();
        passwordInput.sendKeys(ADMIN_PASSWORD);
        return this;
    }

    public void submitLoginAndPassword() {
        signInButton.click();
    }

    public ManageJenkinsPage clickManageJenkinsLink() {
        linkManageJenkins.click();
        return new ManageJenkinsPage();
    }

    //Verification if 'auto refresh' links replace each other
    public EntryPage clickAndCheckAutoRefreshLink() {
        checkAutoRefreshLink();
        return this;
    }

    private void checkAutoRefreshLink() {
        switch (autoRefreshLink.getText()) {
            case "ENABLE AUTO REFRESH":
                Assert.assertTrue(checkReplacementEnableOnDisableLink(), "[Disable link didn't replace Enable link]");
                break;
            case "DISABLE AUTO REFRESH":
                Assert.assertTrue(checkReplacementDisableOnEnableLink(), "[Enable link didn't replace Disable link");
                break;
            default:
                fail("[Incorrect text in Auto Refresh link]");
                break;
        }
    }

    private boolean checkReplacementEnableOnDisableLink() {
        autoRefreshLink.click();
        return autoRefreshLink.getText().equals("DISABLE AUTO REFRESH");
    }

    private boolean checkReplacementDisableOnEnableLink() {
        autoRefreshLink.click();
        return autoRefreshLink.getText().equals("ENABLE AUTO REFRESH");
    }
}