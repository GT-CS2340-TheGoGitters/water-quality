package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Account;

import java.io.File;
import java.text.SimpleDateFormat;


public class ProfileController extends Controller {

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
    private Text accountType;

    @FXML
    private Text title;

    @FXML
    private Button back;

    @FXML
    private Button editProfile;

    public ProfileController() {

    }

    /**
     * Puts required values in the appropriate fields.
     */
    public void setUpProfile() {
        Account account = mainApp.getCurrentAccount();
        name.setText(account.getName());
        username.setText(account.getUsername());
        passwordCreated.setText(new SimpleDateFormat("MMMMM F, y").format(account.getLastPasswordChange()));
        accountType.setText(account.getAccountType().getUserType());
        if (account.getEmailAddress() != null) {
            email.setText(account.getEmailAddress());
        }
        if (account.getHomeAddress() != null) {
            address.setText(account.getHomeAddress());
        }

        if (account.getTitle() != null) {
            title.setText(account.getTitle());
        }
    }

    /**
     * Returns to the post login screen when Done is clicked.
     */
    @FXML
    public void backClicked() {
        mainApp.showScreen(new File("../view/PostLogin.fxml"));
    }

    /**
     * Returns to the edit account screen when Edit profile is clicked.
     */
    @FXML
    public void editProfileClicked() {
        mainApp.showScreen(new File("../view/EditAccount.fxml"));
    }
}
