package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Account;


/**
 * Created by Ashwin on 10/5/2016.
 */
public class ProfileController {

    @FXML
    private Text name;

    @FXML
    private Text username;

    @FXML
    private Text password;

    @FXML
    private Text email;

    @FXML
    private Text address;

    @FXML
    private Text accounttype;

    @FXML
    private Text title;

    @FXML
    private Button passbutton;

    private WaterQualityApplication mainApp;

    private Account account;

    private int count;

    public ProfileController() {

    }

    /**
     * Puts required values in the appropriate fields.
     */
    public void setUpProfile() {
        account = mainApp.getCurrentAccount();
        name.setText(account.getName());
        username.setText(account.getUsername());
        accounttype.setText(account.getAccountType().getUserType());
        for (int i = 0; i < account.getPassword().length(); i++) {
            password.setText(password.getText() + "*");
        }
        if (account.getEmailAddress() != null) {
            email.setText(account.getEmailAddress());
        }
        if (account.getHomeAddress() != null) {
            address.setText(account.getHomeAddress());
        }

        if (account.getTitle() != null) {
            title.setText(account.getTitle());
        }
        count = 0;
    }

    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }

    /**
     * Returns to the post login screen when Done is clicked.
     */
    @FXML
    public void doneClicked() {
        mainApp.showPostLogin();
    }

    /**
     * Shows and hides the password.
     */
    @FXML
    public void passButtonClicked() {
        if (count % 2 == 0) {
            count++;
            password.setText(account.getPassword());
        } else {
            count++;
            password.setText("");
            for (int i = 0; i < account.getPassword().length(); i++) {
                password.setText(password.getText() + "*");
            }
        }
    }
}
