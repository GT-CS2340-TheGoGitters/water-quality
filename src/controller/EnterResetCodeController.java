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
public class EnterResetCodeController extends Controller {

    @FXML
    private TextField codeField;

    private Account account;
    private PasswordResetCode code;

    public EnterResetCodeController() {
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
     * Returns to the Login screen when Cancel is pressed
     */
    @FXML
    private void handleCancelPressed() {

        mainApp.showScreen(new File("../view/Login.fxml"));
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
        } else {
            ResetPasswordController newController = mainApp.showResetPassword();

            newController.setAccount(account);
        }
    }
}
