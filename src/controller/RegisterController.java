package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.User;

/**
 * Created by Allison on 9/27/16.
 */
public class RegisterController {

    private WaterQualityApplication mainApp;

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    public void setApp(WaterQualityApplication newApp) { mainApp = newApp;}

    @FXML
    private void handleCancelPressed() {
        mainApp.returnToWelcomeScreen();
    }

    @FXML
    private void handleRegisterClick() {
        //TODO: what do we have to do here? Check that input is valid?
    }

}
