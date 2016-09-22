package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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

    private boolean _okClicked = false;

    private void handleOKPressed() {
        if (isInputValid()) {
            if (usernameField.getAccessibleText().equals(user.getUsername())
                    && passwordField.getAccessibleText().equals(user.getPassword())) {
                _okClicked = true;
            }
        }
    }

    private boolean isInputValid() {
        return false;
    }
}
