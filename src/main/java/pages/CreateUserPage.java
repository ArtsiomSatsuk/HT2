package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateUserPage {

    public static final String URL_CREATE_USER = "http://localhost:8080/securityRealm/addUser";

    private WebDriverWait wait;
    private final WebDriver driver;

    By formCreateUserLocator = By.xpath("//*[@id=\"main-panel\"]/form");
    By inputUserNameInCreateUserForm = By.id("username");

    By createUserButton = By.cssSelector("#yui-gen1-button");

    public CreateUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Проверка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Create User [Jenkins]"))) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    //Проверка, появиляется ли форма на странице 'Create User'
    public boolean isCreateUserFormDisplayed(){
       return driver.findElement(formCreateUserLocator).isDisplayed();
    }

    //Проверка, пустые ли поля в форме
//    public boolean areFieldsInFormCreateUserEmpty(){
//
//    }

    //Проверка типов и количества полей в форме (3 текстовых и 2 типа password)

//    public boolean checkInputFieldsInForm(){
//
//    }

    //Заполнение полей в форме


    //Нажатие на кнопку 'Create user'
//    public SomePage pressCreateUserButton(){
//        driver.findElement(createUserButton).click();
//        return SomePage(driver);
//    }
}
