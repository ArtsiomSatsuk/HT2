package tests;

import fixture.SuiteFixture;
import org.testng.annotations.Test;
import pages.EntryPage;

import static org.testng.Assert.assertTrue;

public class JenkinsTests extends SuiteFixture {

    private EntryPage entryPage = new EntryPage();

    @Test(description = "check if elements dt and dd on manage jenkins page are displayed properly")
    public void verifyElementsVisibility() {
        entryPage.clickManageJenkinsLink().isManageUsersElementsDisplayed();
    }

    @Test(description = "check if create user link on manage users page available")
    public void checkCreateUserLinkAvailability() {
        boolean actualResult = entryPage.clickManageJenkinsLink().clickManageUsersLink().isLinkCreateUserAvailable();
        assertTrue(actualResult, "[Link 'Create User' on 'Manage Users' page is not available]");
    }

    @Test(description = "check if enable and disable auto refresh links replace each other")
    public void checkAutoRefreshLink() {
        entryPage.openThisPage().clickAndCheckAutoRefreshLink().clickAndCheckAutoRefreshLink()
                .clickAndCheckAutoRefreshLink().clickAndCheckAutoRefreshLink();
    }
}