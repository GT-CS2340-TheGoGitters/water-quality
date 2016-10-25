package controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.*;
import model.logging.security.Log;
import model.logging.security.LoginAttemptEntry;

import javax.swing.*;
import java.io.IOException;


/**
 * Created by Joshua on 10/25/2016.
 */
public class ResetPasswordController {

    @FXML
    private PasswordField passwordField;

    private WaterQualityApplication mainApp;
    private Account account;

    public ResetPasswordController() {
    }

    /**
     * Gives the controller access to the main application
     *
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }

    /**
     * Gives the controller access to the account whose password is being reset
     *
     * @param account the account
     */
    public void setAccount(Account account)
    {
        this.account = account;
    }

    /**
     * Returns to the Welcome screen when Cancel is pressed
     */
    @FXML
    private void handleCancelPressed() {
        mainApp.returnToWelcomeScreen();
    }

    /**
     * Sets the password and returns to the welcome screen
     */
    @FXML
    private void handleSetPasswordPressed() {
        String password = passwordField.getText();

        if(password.length() < 1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Information");
            alert.setContentText("You must enter a password.");
            alert.showAndWait();
            return;
        } else {
            try {
                account.setPassword(password);
            } catch (Password.CannotPerformOperationException e) {
                e.printStackTrace();

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Internal Error");
                alert.setContentText("Could not set password.");
                alert.showAndWait();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password Set");
            alert.setHeaderText("Password Set");
            alert.setContentText("Your password has been reset. You may now log in.");
            alert.showAndWait();

            mainApp.returnToWelcomeScreen();
        }
    }
}