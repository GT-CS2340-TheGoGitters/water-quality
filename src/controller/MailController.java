package controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import model.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class MailController extends Controller {
    private static final String BASE_URL = "https://api.mailgun.net/v3/h2locator.jdwire.co";
    private static final String FROM = "noreply@h2locator.jdwire.co";
    private static final String SUBJECT = "Reset your H2LOcator Password";

    private String apiKey;

    /**
     * controls where mail is sent from
     * @throws IOException When the api key fails to load.
     */
    public MailController() throws IOException {
        apiKey = new String(Files.readAllBytes(Paths.get("mailgunkey.dat")));
    }

    /**
     * if clicked, this will send a password reset to the email listed
     * @param account the user's account on the app
     * @param code code to reset the password
     * @throws Exception When the email fails to send.
     */
    public void sendPasswordReset(Account account, String code) throws Exception {
        String txtTemplate = new String(Files.readAllBytes(Paths.get("recovery_email.txt")));
        String htmlTemplate = new String(Files.readAllBytes(Paths.get("recovery_email.html")));

        String txtBody = txtTemplate.replace("{name}", account.getName()).replace("{code}", code);
        String htmlBody = htmlTemplate.replace("{name}", account.getName()).replace("{code}", code);

        HttpResponse<JsonNode> response = Unirest.post(BASE_URL+"/messages")
                .basicAuth("api", apiKey)
                .field("from", FROM)
                .field("to", account.getEmailAddress())
                .field("subject", SUBJECT)
                .field("text", txtBody)
                .field("html", htmlBody)
                .asJson();

        if (response.getStatus() != 200){
            throw new Exception(response.getBody().getObject().get("message").toString());
        }
    }
}
