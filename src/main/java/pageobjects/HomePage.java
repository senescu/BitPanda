package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.BasePage;


public class HomePage extends BasePage {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers
    //~ ----------------------------------------------------------------------------------------------------------------

    public static String baseUrl = "https://www.bitpanda.com/en";

    public static By signUpLink = By.xpath("/html/body/nav/div[2]/ul/li[8]/a");
    public static By getStartedNow = By.xpath("/html/body/nav/div[2]/ul/li[8]/a");
    public static By logIn = By.xpath("/html/body/nav/div[2]/ul/li[7]/a");

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public HomePage goToHomePage() {
        driver.get(baseUrl);
        return this;
    }

    public LoginPage goToLoginPage() {
        click(logIn);
        return new LoginPage(driver);
    }

}
