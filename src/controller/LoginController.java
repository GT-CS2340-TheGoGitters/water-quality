package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Account;
import model.AccountType;
import model.AccountsHolder;


/**
 * Created by Ashwin on 9/17/2016.
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private WaterQualityApplication mainApp;

    public LoginController() { }

    /**
     * Gives the controller access to the main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }

    /**
     * Returns to the Welcome screen when Cancel is pressed
     */
    @FXML
    private void handleCancelPressed() {
        mainApp.returnToWelcomeScreen();
    }

    /**
     * Logs in the user or generates an alert if it cannot authenticate
     */
    @FXML
    private void handleLoginPressed() {
        Account authenticatedAccount = null;

        Account identifiedAccount = AccountsHolder.getAccountByUsername(usernameField.getText());

        if (identifiedAccount != null) {
            if (identifiedAccount.getIsLocked()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Account Locked");
                alert.setContentText("Your account has been locked. Please contact an admin for assistance.");
                alert.showAndWait();

                return;
            } else if (passwordField.getText().equals(identifiedAccount.getPassword())) {
                authenticatedAccount = identifiedAccount;
                authenticatedAccount.resetIncorrectAttempts();
            } else {
                identifiedAccount.incrementIncorrectAttempts();
            }
        }

        if ( authenticatedAccount != null ) {
            if (authenticatedAccount.getAccountType() == AccountType.ADM) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Unsupported Role");
                alert.setContentText("Admin functionality has not been implemented yet.");
                alert.showAndWait();
            } else {
                mainApp.setCurrentAccount(authenticatedAccount);
                mainApp.showPostLogin();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid Login Information");
            alert.setContentText("One or both of the required fields are incorrect.");
            alert.showAndWait();
        }
    }
}
