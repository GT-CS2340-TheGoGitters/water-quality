package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;



/**
 * Created by Ashwin on 9/17/2016.
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private User user;

    private Stage _dialogStage;

    private boolean _loginAllowed = false;

    private void handleLoginPressed() {
        if (isInputValid()) {
            if (usernameField.getAccessibleText().equals(user.getUsername())
                    && passwordField.getAccessibleText().equals(user.getPassword())) {
                _loginAllowed = true;
                _dialogStage.close();
            } else {
                _loginAllowed = false;
            }

        }
    }

    private boolean isInputValid() {
        String error = "";
        if (usernameField.getText() == null) {
            error += "No username entered. Try again!\n";
        } else if (passwordField.getText() == null) {
            error+= "No password entered. Try again!\n";
        }

        if (error.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
