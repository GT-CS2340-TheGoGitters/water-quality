package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Account;

import java.text.SimpleDateFormat;


/**
 * Created by Ashwin on 10/5/2016.
 */
public class ProfileController {

    @FXML
    private Text name;

    @FXML
    private Text username;

    @FXML
    private Text passwordCreated;

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

    @FXML
    private Button back;

    @FXML
    private Button editprofile;

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
        passwordCreated.setText(new SimpleDateFormat("MMMMM F, y").format(account.getLastPasswordChange()));
        accounttype.setText(account.getAccountType().getUserType());
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

    /**
     * Gives the controller access to the main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }

    /**
     * Returns to the post login screen when Done is clicked.
     */
    @FXML
    public void backClicked() {
        mainApp.showPostLogin();
    }

    /**
     * Returns to the edit account screen when Edit profile is clicked.
     */
    @FXML
    public void editProfileClicked() {
        mainApp.showEditAccount();
    }
}
