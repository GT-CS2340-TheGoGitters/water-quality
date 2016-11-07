package controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import fxapp.WaterQualityApplication;
import model.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Joshua on 10/25/16.
 */
public class MailController extends Controller {
    protected static final String BASE_URL = "https://api.mailgun.net/v3/h2locator.jdwire.co";
    protected static final String FROM = "noreply@h2locator.jdwire.co";
    protected static final String SUBJECT = "Reset your H2LOcator Password";

    protected String apiKey;

    /**
     * controls where mail is sent from
     * @throws IOException
     */
    public MailController() throws IOException {
        apiKey = new String(Files.readAllBytes(Paths.get("mailgunkey.dat")));
    }

    /**
     * if clicked, this will send a password reset to the email listed
     * @param account the user's account on the app
     * @param code code to reset the password
     * @throws Exception
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
