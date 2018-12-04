package tests;

import org.testng.annotations.Test;
import pageobjects.LoginPage;
import static utils.BasePage.driver;

import utils.BaseTest;
import utils.credentialsLoader;


public class LogInTest extends BaseTest {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    @Test
    public void logIn() {
        LoginPage lp = new LoginPage(driver);
        lp.goToPage();
        lp.logIn();

    }
}
