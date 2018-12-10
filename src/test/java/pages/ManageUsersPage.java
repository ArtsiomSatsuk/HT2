package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collection;

import static driver.Driver.getDriver;
import static pages.CreateUserPage.NEW_USER_NAME;
import static pages.EntryPage.ADMIN_NAME;

public class ManageUsersPage extends AbstractPage{

    private static final String MANAGE_USERS_URL = "http://localhost:8080/securityRealm/";

    //Использую StringBuilder, чтобы не создавать несколько лишних объектов строк, а сразу создать одну строку
    private String newUserDeleteLinkLocator = new StringBuilder("a[href=\"user/"+NEW_USER_NAME.toLowerCase()+"/delete\"]").toString();
    private String linkDeleteAdminLocator = new StringBuilder("a[href=\"user/"+ ADMIN_NAME.toLowerCase()+"/delete\"]").toString();

    @FindBy(xpath = "//a[text()='Create User']")
    private WebElement linkCreateUser;

//    @FindBy(xpath = "a[href*=delete]")
//    private WebElement deleteUserLinks;

    ////////////////////////////////////////////////////////////
//    @FindBy(xpath = "//tr/td//a[text()]")   //элемент tr в которой есть ячейка td с текстом
//    private WebElement allRowsInUsersTableWithText;
/////////////////////////////////////////////////////////////////////////////////

    //все ссылки в таблице Юзеров в которых есть текст
    private By allRowsInUsersTableWithTextLocator = By.xpath("//tr/td//a[text()]");

    private By newUserDeleteLink = By.cssSelector(newUserDeleteLinkLocator);

    private Collection<WebElement> allRowsInUsersTableWithText = getDriver().findElements(allRowsInUsersTableWithTextLocator);

    //проверяет, есть ли хотя бы одна строка в таблице пользователей с новым, только что добавленным пользователем
    public boolean checkIfNewTableRowWithNewUserDisplays() {
        return allRowsInUsersTableWithText.stream().anyMatch(webElement -> webElement.getText().equals(NEW_USER_NAME));
    }

    //Нажатие на ссылку 'Create User' и переход на страницу 'Create User'
    public CreateUserPage pressLinkCreateUser(){
        linkCreateUser.click();
        return new CreateUserPage();
    }

    //Нажатие на ссылку 'Delete User' и переход на страницу 'DeleteUserPage'
    public  DeleteUserPage pressLinkDeleteUser(){
        getDriver().findElement(newUserDeleteLink).click();
        return new DeleteUserPage();
    }

    //существует ли ссылка на удаление пользователя 'Admin'
    public boolean checkIfLinkDeleteAdminExists() {
        return getDriver().findElements(By.cssSelector(linkDeleteAdminLocator)).size() != 0;
    }

    //существует ли ссылка на удаление пользователя, которого только что удалили
    public boolean checkIfLinkToDeleteUserExistsAfterDeletingUser() {
        return getDriver().findElements(By.cssSelector(newUserDeleteLinkLocator)).size()!=0;
    }

    //существует ли строка в таблице, про юзера, которого только что удалили
    public boolean checkIfTableRowExistsAfterDeletingUser(){
        return allRowsInUsersTableWithText.stream().anyMatch(webElement -> webElement.getText().equals(NEW_USER_NAME));
    }

    //Проверка, доступна ли ссылка 'Create User'
    public boolean checkIfLinkCreateUserAvailable() {
        return linkCreateUser.isEnabled();
    }
}