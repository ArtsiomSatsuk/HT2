package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static pages.CreateUserPage.NEW_USER_NAME;
import static pages.EntryPage.BASE_URL;

public class DeleteUserPage extends AbstractPage{

    //Используем StringBuilder, чтобы не создавать несколько лишних объектов строк, а сразу создать одну строку
    private static final String DELETE_USER_URL = new StringBuilder(BASE_URL+"user/"+NEW_USER_NAME+"/delete").toString();

    @FindBy(xpath = "//*[@id=\"main-panel\"]/form")
    private WebElement textAboutDeletingUser;

    @FindBy(id = "yui-gen1-button")
    private WebElement buttonYesToDeleteUser;

    @Override
    public DeleteUserPage openThisPage() {
        getDriver().get(DELETE_USER_URL);
        return this;
    }

    public boolean isTextAboutDeletingUserAppears() {
        return textAboutDeletingUser.getText().contains("Are you sure about deleting the user from Jenkins?");
    }

    public ManageUsersPage pressButtonYesToDeleteUser(){
        buttonYesToDeleteUser.click();
        return new ManageUsersPage();
    }
}
