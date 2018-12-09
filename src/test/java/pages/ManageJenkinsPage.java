package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageJenkinsPage extends AbstractPage{

    public final static String MANAGE_JENKINS_URL = "http://localhost:8080/manage";

    @FindBy(xpath = "//*[@id=\"main-panel\"]//dt[text()=\"Manage Users\"]") //++++
    private WebElement dtElementManageUsers;

    @FindBy(xpath = "//*[@id=\"main-panel\"]//dd[text()=\"Create/delete/modify users that can log in to this Jenkins\"]")  //++++
    private WebElement ddElementCreateDeleteModify;

    @FindBy(xpath = "//a[@title=\"Manage Users\"]")  //++++
    private WebElement linkManageUsers;

    public ManageJenkinsPage() {
        super();
    }

    @Override
    public ManageJenkinsPage openThisPage() {
        getDriver().get(MANAGE_JENKINS_URL);
        return this;
    }

    //Переход на 'Manage Users' и переход на страницу 'Manage Users'
    public ManageUsersPage pressManageUsersLink() {
        linkManageUsers.click();
        return new ManageUsersPage();
    }

    //Проверка наличия на странице 'Manage Jenkins' элемента dt с текстом 'Manage Users'
    public boolean isDtElementManageUsersDisplayed() {
        return dtElementManageUsers.isDisplayed() && dtElementManageUsers.getText().equals("Manage Users");
    }

    //Проверка наличия на странице 'Manage Jenkins' элемента dd с текстом 'Create/delete/modify users that can log in to this Jenkins'
    public boolean isDdElementCreateDeleteModifyDisplayed() {
        return ddElementCreateDeleteModify.isDisplayed() && ddElementCreateDeleteModify
                .getText().equals("Create/delete/modify users that can log in to this Jenkins");
    }
}