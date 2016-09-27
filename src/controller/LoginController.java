package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    private WaterQualityApplication mainApp;

    private User user;

    public LoginController() {
        user = new User();
    }

    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }

    @FXML
    private void handleCancelPressed() {
        mainApp.returnToWelcomeScreen();
    }

    @FXML
    private void handleLoginPressed() {
        if (usernameField.getText().equals(user.getUsername())
                && passwordField.getText().equals(user.getPassword())) {
            mainApp.showPostLogin();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid Login Information");
            alert.setContentText("One or both of the required fields are incorrect.");
            alert.showAndWait();
        }
    }
}
