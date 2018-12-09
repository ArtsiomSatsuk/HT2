package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collection;

import static pages.CreateUserPage.NEW_USER_NAME;

public class ManageUsersPage extends AbstractPage{

    public static final String MANAGE_USERS_URL = "http://localhost:8080/securityRealm/";

    private String newUserDeleteLinkLocator = "a[href=\"user/"+NEW_USER_NAME.toLowerCase()+"/delete\"]";

    @FindBy(xpath = "//a[text()=\"Create User\"]")
    private WebElement linkCreateUser;

//    @FindBy(xpath = "//tr/td//text()")   //элемент tr в которой есть ячейка td с текстом
//    private WebElement allRowsInUsersTableWithText;

    private By allRowsInUsersTableWithTextLocator = By.xpath("//tr/td//a");

    private By newUserDeleteLink = By.cssSelector(newUserDeleteLinkLocator);



    @FindBy(xpath = "a[href*=delete]")
    private WebElement deleteUserLinks;

    private Collection<WebElement> allCellsWithText = getDriver().findElements(allRowsInUsersTableWithTextLocator);

    public boolean isNewTableRowWithNewUserHasDisplayed() {
        return allCellsWithText.stream().anyMatch(webElement -> webElement.getAttribute("value").equals(NEW_USER_NAME));
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

    /////////////////////////////////////////////////////
    //Проверка, доступна ли ссылка 'Create User'
    public boolean isLinkCreateUserAvailable(){
        return linkCreateUser.isDisplayed();
    }
    /////////////////////////////////////////////////////
}