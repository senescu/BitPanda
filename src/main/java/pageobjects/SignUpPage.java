package pageobjects;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import utils.BasePage;
import utils.TwoCaptchaService;
import utils.credentialsLoader;


public class SignUpPage extends BasePage {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers
    //~ ----------------------------------------------------------------------------------------------------------------

    public static String baseUrl = "https://www.bitpanda.com/en";

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    String apiKey = "7beab65e8d389bfe91a2ac61209b5177";
    String googleKey = "6LfL2GcUAAAAAFIzSnrotyanhIl59jjXW74KFOES";
    String pageUrl = "https://web.bitpanda.com/user/register";

    credentialsLoader credentialsUser;

    By firstName = By.xpath("//*[@id=\"forename\"]");
    By surName = By.xpath("//*[@id=\"surname\"]");
    By passWord = By.xpath("//*[@id=\"password\"]");
    By emailAddress = By.xpath("//*[@id=\"email\"]");
    By countryOfResidence = By.xpath("//*[@id=\"countrySelect\"]");
    By readTerms = By.xpath("/html/body/bitpanda/div[1]/div[2]/ng-component/div/form/legal-settings/bp-switch[1]/div/div[2]/div/label/span[2]");
    By readPolicy = By.xpath("/html/body/bitpanda/div[1]/div[2]/ng-component/div/form/legal-settings/bp-switch[2]/div/div[2]/div/label/span[2]");
    By createAccount = By.xpath("/html/body/bitpanda/div[1]/div[2]/ng-component/div/form/loading-button/button/span[1]");

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------

    public SignUpPage(WebDriver driver) {
        super(driver);
        credentialsUser = new credentialsLoader();
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public SignUpPage goToHomePage() {
        driver.get(baseUrl);
        return this;
    }

    public SignUpPage goToSignUpPage() {
        HomePage hp = new HomePage(driver);
        waitVisibility(HomePage.signUpLink);
        click(HomePage.signUpLink);
        return this;
    }

    public SignUpPage signUpTestUser() {
        fillSignUpForm();
        selectCountry();

        click(readTerms);
        click(readPolicy);
        click(createAccount);

        String responseToken = getResponseToken();
        if (responseToken != null) {
            sendResponseToken(responseToken);
        }
        return this;
    }

    private void selectCountry() {
        click(countryOfResidence);
        Select dropdown = new Select(driver.findElement(By.id("countrySelect")));
        dropdown.selectByVisibleText("Romania");
    }

    private void fillSignUpForm() {
        writeText(firstName, credentialsUser.getFirstname());
        writeText(surName, credentialsUser.getSurname());
        writeText(passWord, credentialsUser.getPassword());
        writeText(emailAddress, credentialsUser.getEmailAddress());
    }

    private String getResponseToken() {
        TwoCaptchaService service = new TwoCaptchaService(apiKey, googleKey, pageUrl);
        String responseToken = null;
        try {
            responseToken = service.solveCaptcha();

            System.out.println("The response token is: " + responseToken);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return responseToken;
    }

    private String createJsonBody(String responseToken) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"id\":\"\",\n" +
            "   \"_saving\":false,\n" +
            "   \"meta\":{  \n" +
            "\n" +
            "   },\n" +
            "   \"privacy_policy\":true,\n" +
            "   \"terms_accepted\":true,\n" +
            "   \"email\":\"Tester3@fastmail.com\",\n" +
            "   \"password\":\"Parola#3210983\",\n" +
            "   \"language\":\"eng\",\n" +
            "   \"loggedIn\":false,\n" +
            "   \"TwoFA\":false,\n" +
            "   \"fiat_id\":\"\",\n" +
            "   \"last_last_login\":{  \n" +
            "      \"date_iso8601\":\"\",\n" +
            "      \"unix\":\"\"\n" +
            "   },\n" +
            "   \"password_reset\":{  \n" +
            "      \"date_iso8601\":\"\",\n" +
            "      \"unix\":\"\"\n" +
            "   },\n" +
            "   \"withdraw_allowed\":{  \n" +
            "      \"date_iso8601\":\"\",\n" +
            "      \"unix\":\"\"\n" +
            "   },\n" +
            "   \"enforce_two_factor_auth\":false,\n" +
            "   \"auth_code\":\"\",\n" +
            "   \"verified\":\"unverified\",\n" +
            "   \"device_id\":\"\",\n" +
            "   \"unique_payment_number\":\"\",\n" +
            "   \"referee_key\":\"\",\n" +
            "   \"is_affiliate\":false,\n" +
            "   \"expiration_sec\":\"\",\n" +
            "   \"ignoredVerify\":false,\n" +
            "   \"device_type\":\"pc\",\n" +
            "   \"forename\":\"Tester3\",\n" +
            "   \"surname\":\"Surname3\",\n" +
            "   \"country_id\":\"176\",\n" +
            "   \"newsletters\":false,\n");
        builder.append("\t\"g-recaptcha-response\":");
        builder.append("\"");
        builder.append(responseToken).append("\"").append(",\n");
        builder.append("\"pap_visitor_id\":null,\n" +
            "   \"ref\":null,\n" +
            "   \"tag\":null\n" +
            "}");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private void sendResponseToken(String token) {
        RestAssured.baseURI = "https://api.bitpanda.com";
        RestAssured.basePath = "/v1/users";

        given().contentType("application/json")
               .accept("application/json")
               .body(createJsonBody(token))
               .when()
               .post("")
               .then()
               .statusCode(200);
    }
}
