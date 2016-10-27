package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;

/**
 * Created by Allison on 10/20/16.
 */
public class AdminHomeController {

    private WaterQualityApplication mainApp;

    private Account account;

    @FXML
    private Button mngUsers;

    @FXML
    private Button viewLog;

    @FXML
    private Button mngReports;

    @FXML
    private Button logout;

    public AdminHomeController() {

    }

    /**
     * Give controller access to main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }

    @FXML
    public void handleMngUsersClicked() {
        //mainApp.showMngUsersScreen();
    }

    @FXML
    public void handleViewLogClicked() {
        //mainApp.showSecurityLogScree();
    }

    @FXML
    public void handleMngReportsClicked() {
        //mainApp.showMngReportsScreen();
    }

    @FXML
    public void handleLogout() {
        mainApp.returnToWelcomeScreen();
    }

}
