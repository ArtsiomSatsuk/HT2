package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static pages.CreateUserPage.NEW_USER_NAME;
import static pages.EntryPage.BASE_URL;

public class DeleteUserPage extends AbstractPage{

    //Использую StringBuilder, чтобы не создавать несколько лишних объектов строк, а сразу создать одну строку
    private static final String DELETE_USER_URL = new StringBuilder(BASE_URL+"user/"+NEW_USER_NAME+"/delete").toString();

    @FindBy(xpath = "//*[@id='main-panel']/form")
    private WebElement formWithTextAboutDeletingUser;

    @FindBy(id = "yui-gen1-button")
    private WebElement buttonYesToDeleteUser;

    public boolean checkIfTextAboutDeletingUserAppears() {
        return formWithTextAboutDeletingUser.getText().contains("Are you sure about deleting the user from Jenkins?");
    }

    public ManageUsersPage pressButtonYesToDeleteUser(){
        buttonYesToDeleteUser.click();
        return new ManageUsersPage();
    }
}
