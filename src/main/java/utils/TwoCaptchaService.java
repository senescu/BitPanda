package utils;

public class TwoCaptchaService {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    private String apiKey;

    private String googleKey;

    private String pageUrl;

    private HttpWrapper hw;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------

    public TwoCaptchaService(String apiKey, String googleKey, String pageUrl) {
        this.apiKey = apiKey;
        this.googleKey = googleKey;
        this.pageUrl = pageUrl;
        hw = new HttpWrapper();
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public String solveCaptcha() throws InterruptedException {
        System.out.println("Sending recaptcha challenge to 2captcha.com");

        String parameters = createParameters(apiKey, googleKey, pageUrl);

        hw.get("http://2captcha.com/in.php?" + parameters);

        String captchaId = hw.getHtml().replaceAll("\\D", "");
        int timeCounter = 0;

        do {
            hw.get("http://2captcha.com/res.php?key=" + apiKey +
                "&action=get" +
                "&id=" + captchaId);

            Thread.sleep(1000);

            timeCounter++;
            System.out.println("Waiting for captcha to be solved");
        } while (hw.getHtml().contains("NOT_READY"));

        System.out.println("It took " + timeCounter + " seconds to solve the captcha");
        String gRecaptchaResponse = hw.getHtml().replaceAll("OK\\|", "").replaceAll("\\n", "");
        return gRecaptchaResponse;
    }

    private String createParameters(String apiKey, String googleKey, String url) {
        StringBuilder builder = new StringBuilder();
        builder.append("key=")
               .append(apiKey)
               .append("&method=userrecaptcha")
               .append("&googlekey=")
               .append(googleKey)
               .append("&pageurl=")
               .append(url);
        return builder.toString();
    }
}
