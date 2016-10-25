package controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Joshua on 10/25/16.
 */
public class MailController {
    protected static final String BASE_URL = "https://api.mailgun.net/v3/h2locator.jdwire.co";
    protected static final String FROM = "noreply@h2locator.jdwire.co";
    protected static final String SUBJECT = "Reset your H2Locator Password";

    protected String apiKey;

    public MailController() throws IOException {
        apiKey = new String(Files.readAllBytes(Paths.get("mailgunkey.dat")));
    }

    public void sendPasswordReset(Account account, String code) throws Exception {
        HttpResponse<JsonNode> response = Unirest.post(BASE_URL+"/messages")
                .basicAuth("api", apiKey)
                .field("from", FROM)
                .field("to", account.getEmailAddress())
                .field("subject", SUBJECT)
                .field("text", code)
                .asJson();

        if (response.getStatus() != 200){
            throw new Exception(response.getBody().getObject().get("message").toString());
        }
    }
}
