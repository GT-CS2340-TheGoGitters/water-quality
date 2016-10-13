package controller;

import com.lynden.gmapsfx.GoogleMapView;
import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import model.AccountType;

/**
 * Created by Ashwin on 9/17/2016.
 */
public class PostLoginController {
    @FXML
    private MenuItem EditProfileDropDown;

    @FXML
    private MenuItem ViewProfileDropDown;

    @FXML
    private MenuItem WaterPurityReportDropDown;

    @FXML
    private GoogleMapView reportsMap;

    private WaterQualityApplication mainApp;

    /**
     * Gives the controller access to the main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;

        // Handle user ACL
        WaterPurityReportDropDown.setDisable(mainApp.getCurrentAccount().getAccountType() == AccountType.USR);
    }

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

    @FXML
    private void handleWaterPurityReports() {
        if (mainApp.getCurrentAccount().getAccountType() == AccountType.USR) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You do no have permission to submit a Water Purity Report");
            alert.showAndWait();
            return;
        } else {
            mainApp.showWaterPutrityReport();
        }
    }

    @FXML
    private void handlViewReports() { mainApp.showWaterReports(); }
}
