package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.BasePage;
import utils.credentialsLoader;


public class LoginPage extends BasePage {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers
    //~ ----------------------------------------------------------------------------------------------------------------

    public static String baseUrl = "https://web.bitpanda.com/user/login";

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    credentialsLoader credentialsUser1 = new credentialsLoader();
    By emailAddressBy = By.xpath("//*[@id=\"email\"]");
    By passwordBy = By.xpath("//*[@id=\"password\"]");
    By loginButtonBy = By.xpath("/html/body/bitpanda/div[1]/div[2]/ng-component/div/form/button");

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public LoginPage goToPage() {
        driver.get(baseUrl);
        return this;
    }

    public LoginPage logIn() {

        writeText(emailAddressBy, credentialsUser1.getEmailAddress());
        writeText(passwordBy, credentialsUser1.getPassword());
        click(loginButtonBy);
        return this;
    }

}
