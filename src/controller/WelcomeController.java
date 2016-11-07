package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class WelcomeController extends Controller {

    @FXML
    private ImageView titlelogo;

    @FXML
    private Image logo;

    /**
     * Brings user to login screen when login is clicked
     */
    @FXML
    public void handleLoginClick() {
        mainApp.showScreen(new File("../view/Login.fxml"));
    }

    /**
     * Brings user to register screen when Register is clicked
     */
    @FXML
    public void handleRegisterClick() {
        mainApp.showScreen(new File("../view/Registration.fxml"));
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
