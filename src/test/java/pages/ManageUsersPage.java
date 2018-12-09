package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collection;

import static pages.CreateUserPage.NEW_USER_NAME;
import static pages.EntryPage.USER_NAME;

public class ManageUsersPage extends AbstractPage{

    private static final String MANAGE_USERS_URL = "http://localhost:8080/securityRealm/";

    private String newUserDeleteLinkLocator = "a[href=\"user/"+NEW_USER_NAME.toLowerCase()+"/delete\"]";
    private String linkDeleteAdminLocator = "a[href=\"user/"+USER_NAME.toLowerCase()+"/delete\"]";

    @FindBy(xpath = "//a[text()=\"Create User\"]")
    private WebElement linkCreateUser;

    @FindBy(xpath = "a[href*=delete]")
    private WebElement deleteUserLinks;

    ////////////////////////////////////////////////////////////
//    @FindBy(xpath = "//tr/td//a[text()]")   //элемент tr в которой есть ячейка td с текстом
//    private WebElement allRowsInUsersTableWithText;
/////////////////////////////////////////////////////////////////////////////////

    //все ссылки в таблице Юзеров в которых есть текст
    private By allRowsInUsersTableWithTextLocator = By.xpath("//tr/td//a[text()]");

    private By newUserDeleteLink = By.cssSelector(newUserDeleteLinkLocator);

    private Collection<WebElement> allRowsWithText = getDriver().findElements(allRowsInUsersTableWithTextLocator);

    public boolean isNewTableRowWithNewUserDisplays() {
        return allRowsWithText.stream().anyMatch(webElement -> webElement.getText().equals(NEW_USER_NAME));
    }

    public ManageUsersPage() {
        super();
    }

    @Override
    public ManageUsersPage openThisPage() {
        getDriver().get(MANAGE_USERS_URL);
        return this;
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

    public boolean isLinkDeleteAdminExists(){
        try{
            Driver.getWebDriverInstance().findElement(By.cssSelector(linkDeleteAdminLocator));
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    /////////////////////////////////////////////////////
    //Проверка, доступна ли ссылка 'Create User'
    public boolean isLinkCreateUserAvailable() {
        return linkCreateUser.isEnabled();
    }
    /////////////////////////////////////////////////////
}