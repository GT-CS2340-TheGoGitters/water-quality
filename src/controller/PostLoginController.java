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

    @FXML
    private MenuItem ViewProfileDropDown;

    private WaterQualityApplication mainApp;

    /**
     * Gives the controller access to the main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) { mainApp = newApp;}

    /**
     * Brings user to the post login screen after logging in
     */
    @FXML
    private void handleLogOutClick() {
        mainApp.setCurrentAccount(null);
        mainApp.returnToWelcomeScreen();
    }

    /**
     * Show the edit account screen
     */
    @FXML
    private void handleEditProfile() {
        mainApp.showEditAccount();
    }

    /**
     * Show the profile screen
     */
    @FXML
    private void handleViewProfile() { mainApp.showProfile(); }

    @FXML
    private void handleWaterSourceReport() { mainApp.showWaterSourceReport(); }
}
