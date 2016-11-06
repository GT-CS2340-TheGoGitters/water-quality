package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import model.*;

import java.io.File;

/**
 * Created by Allison on 10/20/16.
 */
public class AdminHomeController implements Controller {

    private WaterQualityApplication mainApp;

    private Account account;

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
        mainApp.showScreen(new File("../view/Welcome.fxml"));
    }

}
