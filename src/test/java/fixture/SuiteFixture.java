package fixture;

import credentials.InputData;
import driver.Driver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.EntryPage;

import java.io.IOException;

public class SuiteFixture {

    @BeforeSuite
    public void logIn() throws IOException {
        InputData.readProperties();
        EntryPage entryPage = new EntryPage().openThisPage().setAdminName().setAdminPassword();
        entryPage.submitLoginAndPassword();
    }

    @AfterSuite
    public void close() {
        Driver.getDriver().close();
    }
}
