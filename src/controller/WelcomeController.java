package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WelcomeController {
    private WaterQualityApplication mainApp;

    @FXML
    private ImageView titlelogo;

    @FXML
    private Image logo;

    /**
     * Gives the controller access to the main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) { mainApp = newApp;}

    /**
     * Brings user to login screen when login is clicked
     */
    @FXML
    public void handleLoginClick() {
        mainApp.showLogin();
    }

    /**
     * Brings user to register screen when Register is clicked
     */
    @FXML
    public void handleRegisterClick() {
        mainApp.showRegister();
    }
}
