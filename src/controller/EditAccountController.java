package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
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

        mainApp.showPostLogin();
    }
}
