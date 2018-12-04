package utils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static utils.BasePage.driver;


public class BaseTest {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    BasePage bp = new BasePage(driver);

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    @BeforeMethod(alwaysRun = true)
    protected void beforeScenario() throws InterruptedException {
        bp.createDriver();
    }
//
//    @AfterMethod(alwaysRun = true)
//    protected void afterScenario() {
//        bp.tearDown();
//    }
}
