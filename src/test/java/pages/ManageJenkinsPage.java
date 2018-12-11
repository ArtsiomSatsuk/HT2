package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class ManageJenkinsPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='main-panel']//dt[text()='Manage Users']")
    private WebElement dtElementManageUsers;

    @FindBy(xpath = "//*[@id='main-panel']//dd[text()='Create/delete/modify users that can log in to this Jenkins']")
    private WebElement ddElementCreateDeleteModify;

    @FindBy(xpath = "//a[@title='Manage Users']")
    private WebElement linkManageUsers;

    public ManageUsersPage clickManageUsersLink() {
        linkManageUsers.click();
        return new ManageUsersPage();
    }

    private SoftAssert softAssert = new SoftAssert();

    //Verification if elements 'dt' and 'dd' on 'Manage Jenkins' page are displayed properly
    public void isManageUsersElementsDisplayed() {
        softAssert.assertTrue(ddElementCreateDeleteModify.isDisplayed(),
                "[Element 'dd' with 'Create/delete/modify users that can log in to this Jenkins' text isn't displayed]");
        softAssert.assertTrue(ddElementCreateDeleteModify.getText().equals("Create/delete/modify users that can log in to this Jenkins"),
                "[Text in 'dd' element on 'Manage Jenkins' page is not correct]");
        softAssert.assertTrue(dtElementManageUsers.isDisplayed(),
                "[Element 'dt' with 'Manage Users' text is not displayed]");
        softAssert.assertTrue(dtElementManageUsers.getText().equals("Manage Users"),
                "[Text in 'dt' element on 'Manage Jenkins' page is not correct]");
        softAssert.assertAll();
    }
}