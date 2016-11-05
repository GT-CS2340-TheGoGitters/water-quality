package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WelcomeController implements Controller {
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

    @FXML
    public void handleLoadClick() {
        mainApp.loadData();
    }

    @FXML
    public void handleSaveClick() {
        mainApp.saveData();
    }
}
