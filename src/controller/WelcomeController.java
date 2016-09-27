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

    public void setApp(WaterQualityApplication newApp) { mainApp = newApp;}

    @FXML
    public void handleLoginClick() {
        mainApp.showLogin();
    }
}
