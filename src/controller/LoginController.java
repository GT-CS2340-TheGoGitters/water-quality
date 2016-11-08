package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Account;
import model.AccountType;
import model.AccountsHolder;
import model.Password;
import model.logging.security.LoginAttemptEntry;

import java.io.File;


public class LoginController extends Controller {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    public boolean validated;

    public LoginController() { }

    public void setUsernameField(String text) {
        usernameField = new TextField("");
        usernameField.setText(text);
    }

    public void setPasswordField(String text) {
        passwordField = new TextField("");
        passwordField.setText(text);
    }

    /**
     * Returns to the Welcome screen when Cancel is pressed
     */
    @FXML
    private void handleCancelPressed() {
        mainApp.showScreen(new File("../view/Welcome.fxml"));
    }

    /**
     * Logs in the user or generates an alert if it cannot authenticate
     */
    @FXML
    public void handleLoginPressed() {
        Account authenticatedAccount = null;

        String username = usernameField.getText();
        Account identifiedAccount = AccountsHolder.getAccountByUsername(username);

        if (identifiedAccount != null) {
            try {
                if (identifiedAccount.getIsLocked()) {
                    mainApp.logSecurityEvent(new LoginAttemptEntry(identifiedAccount, username, LoginAttemptEntry.SuccessStatus.LOCKED));
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Error");
                    alert.setHeaderText("Account Locked");
                    alert.setContentText("Your account has been locked. Please contact an admin for assistance.");
                    alert.showAndWait();

                    return;
                } else if (identifiedAccount.verifyPassword(passwordField.getText())) {
                    mainApp.logSecurityEvent(new LoginAttemptEntry(null, username, LoginAttemptEntry.SuccessStatus.SUCCESS));
                    authenticatedAccount = identifiedAccount;
                    authenticatedAccount.resetIncorrectAttempts();
                } else {
                    mainApp.logSecurityEvent(new LoginAttemptEntry(identifiedAccount, username, LoginAttemptEntry.SuccessStatus.INCORRECT_PASSWORD));
                    identifiedAccount.incrementIncorrectAttempts();
                }
            } catch (Password.CannotPerformOperationException e) {
                e.printStackTrace();
            }
        } else {
            mainApp.logSecurityEvent(new LoginAttemptEntry(null, username, LoginAttemptEntry.SuccessStatus.UNKNOWN_USER));
        }

        if ( authenticatedAccount != null ) {
            if (authenticatedAccount.getAccountType() == AccountType.ADM) {
                mainApp.setCurrentAccount(authenticatedAccount);
                mainApp.showAdminHome();
                validated = true;
            } else {
                mainApp.setCurrentAccount(authenticatedAccount);
                mainApp.showScreen(new File("../view/PostLogin.fxml"));
                validated = true;
            }
        } else {
            validated = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid Login Information");
            alert.setContentText("One or both of the required fields are incorrect.");
            alert.showAndWait();
        }
    }

    /**
     * Prompts the user for their email address and sends a password reset email.
     */
    @FXML
    private void handleForgotPasswordClicked() {
        mainApp.showScreen(new File("..view/SendResetEmail.fxml"));
    }
}
