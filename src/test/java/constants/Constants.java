package constants;

public class Constants {

    //for 'create user' input fields
    public static final String NEW_USER_NAME = "someuser";
    public static final String NEW_USER_PASSWORD = "somepassword";
    public static final String NEW_USER_FULL_NAME = "Some Full Name";
    public static final String NEW_USER_EMAIL = "some@addr.dom";

    public static final String ENTRY_PAGE_URL = "http://localhost:8080/";
    public static final String MANAGE_JENKINS_PAGE_URL = "http://localhost:8080/manage";
    public static final String MANAGE_USERS_PAGE_URL = "http://localhost:8080/securityRealm/";
    public static final String CREATE_USER_PAGE_URL = "http://localhost:8080/securityRealm/addUser";
    public static final String DELETE_USER_PAGE_URL = String.format("http://localhost:8080/securityRealm/user/%s/delete",
            NEW_USER_NAME.toLowerCase());

    public static final int PAGE_LOAD_TIMEOUT = 10;
    public static final int IMPLICITLY_WAIT_TIMEOUT = 5;
    public static final String DRIVER_NAME = "webdriver.chrome.driver";
    public static final String PATH_TO_DRIVER = "D:/ChromeDriver/chromedriver.exe";
}