package controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.*;
import model.logging.security.Log;
import model.logging.security.LoginAttemptEntry;

import javax.swing.*;
import java.io.IOException;


/**
 * Created by Joshua on 10/25/2016.
 */
public class EnterResetCodeController implements Controller {

    @FXML
    private TextField codeField;

    private WaterQualityApplication mainApp;
    private Account account;
    private PasswordResetCode code;

    public EnterResetCodeController() {
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
     * Gives the controller access to the recovery code that was sent
     *
     * @param code the reset code
     */
    public void setCode(PasswordResetCode code)
    {
        this.code = code;
    }

    /**
     * Returns to the Welcome screen when Cancel is pressed
     */
    @FXML
    private void handleCancelPressed() {
        mainApp.showLogin();
    }

    /**
     * Validates the code and sends the user to the reset screen.
     */
    @FXML
    private void handleContinuePressed() {
        String codeStr = codeField.getText();

        if(!code.validate(codeStr)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Information");
            alert.setContentText("The code you entered is either invalid or expired.");
            alert.showAndWait();
            return;
        } else {
            ResetPasswordController newController = mainApp.showResetPassword();

            newController.setAccount(account);
        }
    }
}
