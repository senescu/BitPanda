package tests;

import org.testng.annotations.Test;

import pageobjects.SignUpPage;

import static utils.BasePage.driver;

import utils.BaseTest;


public class SignUpTest extends BaseTest {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    @Test(priority = 0)
    public void signUpTestUser() {
        SignUpPage sp = new SignUpPage(driver);
        sp.goToHomePage();
        sp.goToSignUpPage();
        sp.signUpTestUser();
    }

}
