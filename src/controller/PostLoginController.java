package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;

/**
 * Created by Ashwin on 9/17/2016.
 */
public class PostLoginController {
    private WaterQualityApplication mainApp;

    public void setApp(WaterQualityApplication newApp) { mainApp = newApp;}

    @FXML
    private void handleLogOutClick() {
        mainApp.setCurrentAccount(null);
        mainApp.returnToWelcomeScreen();
    }
}
