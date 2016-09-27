package controller;

import fxapp.WaterQualityApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.User;

import java.awt.event.ActionListener;

import java.io.IOException;


/**
 * Created by Ashwin on 9/17/2016.
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginLogin;

    @FXML
    private Button loginCancel;

    private User user;

    private boolean _loginAllowed = false;

    private WaterQualityApplication mainApp2;

    public void setApp2(WaterQualityApplication newApp) {
        mainApp2 = newApp;
    }

    public final void setOnAction(EventHandler<ActionEvent> value) {
        _loginAllowed = true;
    }

    @FXML
    public void handleLoginPressed() {
        mainApp2.showPostLogin();
    }

    @FXML
    public void handleCancelPressed() {
        mainApp2.showWelcome();
    }

    private boolean isInputValid() {
        String error = "";
        if (usernameField.getAccessibleText() == null) {
            error += "No username entered. Try again!\n";
        }
        if (passwordField.getAccessibleText() == null) {
            error+= "No password entered. Try again!\n";
        }

        if (error.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLoginClicked() {
        return _loginAllowed;
    }
}
