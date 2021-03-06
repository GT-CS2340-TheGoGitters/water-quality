package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;
import model.Password;

import java.io.File;

public class EditAccountController extends Controller {

    @FXML
    private TextField EditAccountName;

    @FXML
    private TextField EditAccountPassword;

    @FXML
    private TextField EditAccountEmail;

    @FXML
    private TextField EditAccountAddress;

    @FXML
    private TextField EditAccountCity;

    @FXML
    private TextField EditAccountState;

    @FXML
    private TextField EditAccountZIP;

    @FXML
    private TextField EditAccountTitle;

    @FXML
    private Button EditProfileCancel;

    @FXML
    private Button EditProfileSave;

    private Account account;

    public EditAccountController() {

    }

    /**
     * Puts required values in the appropriate fields.
     */
    public void setUpEditPage() {
        account = mainApp.getCurrentAccount();
        EditAccountName.setText(account.getName());
        if (account.getEmailAddress() != null) {
            EditAccountEmail.setText(account.getEmailAddress());
        }
        if (account.getHomeAddress() != null) {
            String address = account.getHomeAddress();
            String[] addressComponents = address.split("\n");
            EditAccountAddress.setText(addressComponents[0]);
            String[] city = addressComponents[1].split(",");
            EditAccountCity.setText(city[0]);
            String[] stateZip = city[1].split(" ");
            EditAccountState.setText(stateZip[1]);
            EditAccountZIP.setText(stateZip[2]);
        }
        if (account.getTitle() != null) {
            EditAccountTitle.setText(account.getTitle());
        }
    }

    /**
     * Returns to the post login screen when Cancel is pressed
     */
    @FXML
    private void handleCancelPressed() {
        mainApp.showScreen(new File("../view/PostLogin.fxml"));
    }

    /**
     * Saves the information entered by the user
     */
    @FXML
    private void handleSaveClick() {
        account = mainApp.getCurrentAccount();
        if (EditAccountName.getText().length() != 0) {
            account.setName(EditAccountName.getText());
        }
        if (EditAccountPassword.getText().length() != 0) {
            try {
                account.setPassword(EditAccountPassword.getText());
            } catch (Password.CannotPerformOperationException e) {
                e.printStackTrace();

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Error");
                alert.setHeaderText("Password Hashing Error");
                alert.setContentText("Could not hash password.");
                alert.showAndWait();
                return;
            }
        }
        if (EditAccountEmail.getText().length() != 0) {
            account.setEmailAddress(EditAccountEmail.getText());
        }
        if (EditAccountAddress.getText().length() != 0) {
            if (EditAccountCity.getText().length() == 0 ||
                    EditAccountState.getText().length() == 0 ||
                    EditAccountZIP.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Address Error");
                alert.setHeaderText("Incomplete Address Information");
                alert.setContentText("Fill in remaining address fields.");
                alert.showAndWait();
                return;
            } else {
                String address = EditAccountAddress.getText() +"\n" +
                        EditAccountCity.getText() + ", " +
                        EditAccountState.getText() + " " +
                        EditAccountZIP.getText();
                account.setHomeAddress(address);
            }
        }
        if (EditAccountTitle.getText().length() != 0) {
            account.setTitle(EditAccountTitle.getText());
        }

        mainApp.showScreen(new File("../view/PostLogin.fxml"));
    }
}
