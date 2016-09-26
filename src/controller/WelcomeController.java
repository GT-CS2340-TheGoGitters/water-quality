package controller;

import fxapp.WaterQualityApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.ActionListener;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private Button login;

    private WaterQualityApplication mainApp;

    @FXML
    private ImageView titlelogo;

    @FXML
    private Image logo;

    private boolean _loginClicked = false;

    public final void setOnAction(EventHandler<ActionEvent> value) {
        _loginClicked = true;
    }

    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }

    @FXML
    public void handleLoginClick() {
        mainApp.showLogin();
    }
}
