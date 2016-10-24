package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Account;
import model.AccountType;
import model.AccountsHolder;
import model.logging.security.AccountCreationEntry;

/**
 * Created by Allison on 9/27/16.
 */
public class RegisterController {

    private WaterQualityApplication mainApp;

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private RadioButton UserSelectButton;

    @FXML
    private RadioButton WorkerSelectButton;

    @FXML
    private RadioButton ManagerSelectButton;

    @FXML
    private RadioButton AdminSelectButton;

    private Account account;

    public RegisterController() {}

    /**
     * Gives the controller access to the main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) { mainApp = newApp;}

    /**
     * Returns to the welcome screen when Cancel is pressed
     */
    @FXML
    private void handleCancelPressed() {
        mainApp.returnToWelcomeScreen();
    }

    /**
     * Brings user to the register screen when Register is clicked
     */
    @FXML
    private void handleRegisterClick() {
        if (name.getText().length() != 0 &&
                username.getText().length() != 0 &&
                password.getText().length() != 0) {

            AccountType accountType = null;

            if (UserSelectButton.isSelected()) {
                accountType = AccountType.USR;
            } else if (WorkerSelectButton.isSelected()) {
                accountType = AccountType.WKR;
            } else if (ManagerSelectButton.isSelected()) {
                accountType = AccountType.MGR;
            } else if (AdminSelectButton.isSelected()) {
                accountType = AccountType.ADM;
            }


            try{
                Account newAccount = new Account(
                        name.getText(),
                        username.getText(),
                        password.getText(),
                        accountType
                );

                AccountsHolder.addAccount(newAccount);
                mainApp.logSecurityEvent(new AccountCreationEntry(newAccount));
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                return;
            }


            mainApp.returnToWelcomeScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Error");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText("Fill in all required information.");
            alert.showAndWait();
        }
    }

}
