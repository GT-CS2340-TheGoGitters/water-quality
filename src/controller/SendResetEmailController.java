package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.*;

import java.io.File;


/**
 * Created by Joshua on 10/25/2016.
 */
public class SendResetEmailController implements Controller {

    @FXML
    private TextField emailField;

    private WaterQualityApplication mainApp;

    public SendResetEmailController() {
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
     * Returns to the Welcome screen when Cancel is pressed
     */
    @FXML
    private void handleCancelPressed() {
        mainApp.showScreen(new File("../view/Login.fxml"));
    }

    /**
     * Sends the user a password reset email
     */
    @FXML
    private void handleResetPasswordPressed() {
        String email = emailField.getText();

        Account identifiedAccount = null;

        for (Account account : AccountsHolder.getValues()) {
            if(account.getEmailAddress().equals(email)){
                identifiedAccount = account;
                break;
            }
        }

        if(identifiedAccount == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Information");
            alert.setContentText("This email address is not associated with any user.");
            alert.showAndWait();
            return;
        }

        try {
            PasswordResetCode code = new PasswordResetCode();
            new MailController().sendPasswordReset(identifiedAccount, code.getCode());

            EnterResetCodeController newController = mainApp.showEnterResetCode();

            newController.setAccount(identifiedAccount);
            newController.setCode(code);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Email Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
    }
}
