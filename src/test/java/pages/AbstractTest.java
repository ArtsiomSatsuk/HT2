package pages;

//
//public abstract class AbstractTest {
    //
//    protected final String base_url = "http://localhost:8080/";
//
//    protected WebDriver driver = null;
//    protected AuthorizationPage loginPage = null;
//
//    @BeforeMethod
//    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "D:/ChromeDriver/chromedriver.exe");
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
//
//        driver = new ChromeDriver(capabilities);
//        driver.get(base_url+"/manage");
//
//        loginPage = PageFactory.initElements(driver, AuthorizationPage.class);
//        loginPage.setUserName(USER_NAME).setUserPassword(USER_PASSWORD);
//
//    }
//
//    @AfterMethod
//    public void close() {
//        driver.quit();
//    }
//
//}
