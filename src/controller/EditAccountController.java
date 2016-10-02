package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;

/**
 * Created by Jack on 9/29/16.
 */
public class EditAccountController {

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

    private WaterQualityApplication mainApp;

    private Account account;

    public EditAccountController() {

    }

    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }

    @FXML
    private void handleCancelPressed() {
        mainApp.showPostLogin();
    }

    @FXML
    private void handleSaveClick() {
        account = mainApp.getCurrentAccount();
        if (EditAccountName.getText().length() != 0) {
            account.setName(EditAccountName.getText());
        }
        if (EditAccountPassword.getText().length() != 0) {
            account.setPassword(EditAccountPassword.getText());
        }
        if (EditAccountEmail.getText().length() != 0) {
            account.setEmailAddress(EditAccountEmail.getText());
        }
        if (EditAccountAddress.getText().length() != 0) {
            if (EditAccountCity.getText().length() == 0 ||
                    EditAccountState.getText().length() == 0 ||
                    EditAccountZIP.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error in Address Fields");
                alert.setHeaderText("Incomplete Address Information");
                alert.setContentText("Fill in remaining address fields.");
                alert.showAndWait();
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

        mainApp.showPostLogin();
    }
}
