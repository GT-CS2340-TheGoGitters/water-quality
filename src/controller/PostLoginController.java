package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * Created by Ashwin on 9/17/2016.
 */
public class PostLoginController {
    @FXML
    private MenuItem EditProfileDropDown;

    private WaterQualityApplication mainApp;

    public void setApp(WaterQualityApplication newApp) { mainApp = newApp;}

    @FXML
    private void handleLogOutClick() {
        mainApp.setCurrentAccount(null);
        mainApp.returnToWelcomeScreen();
    }

    @FXML
    private void handleEditProfile() {
        mainApp.showEditAccount();
    }
}
