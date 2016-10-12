package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.*;

/**
 * Created by Jack on 10/8/16.
 */
public class WaterSourceReportController {

    private WaterQualityApplication mainApp;

    @FXML
    private TextField WaterLocationField;

    @FXML
    private RadioButton WellButton;
    @FXML
    private RadioButton StreamButton;
    @FXML
    private RadioButton LakeButton;
    @FXML
    private RadioButton SpringButton;
    @FXML
    private RadioButton BottledButton;

    @FXML
    private RadioButton WasteButton;
    @FXML
    private RadioButton TreatableClearButton;
    @FXML
    private RadioButton TreatableMuddyButton;
    @FXML
    private RadioButton PotableButton;

    private Account account;

    private WaterType waterType;

    private WaterOverallCondition waterOverallCondition;

    private ReportLocation reportLocation;

    private WaterSourceReport waterSourceReport;


    public WaterSourceReportController() {

    }

    /**
     * Gives the controller access to the main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) { mainApp = newApp;}


    /*
     * Brings user back to PostLogin screen
     */
    @FXML
    private void handleCancelPressed() {
        mainApp.showPostLogin();
    }

    /*
     * Checks to make sure that latitude and logitude are filled out
     * Then adds information to WaterReportsHolder
     * Then returns to PostLogin screen
     */
    @FXML
    private void handleSubmitPressed() {
        if (WaterLocationField.getText().length() > 0) {
            account = mainApp.getCurrentAccount();

            String waterLocation = WaterLocationField.getText();
            try {
                String[] latLong = waterLocation.split(",");
                double latitude = Double.parseDouble(latLong[0]);
                double longitude = Double.parseDouble(latLong[1]);
                reportLocation = new ReportLocation(latitude, longitude);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error in Water Location.");
                alert.setContentText("Enter location properly\nlatitude, longitude");
                alert.showAndWait();
                return;
            }

            if (WellButton.isSelected()) {
                waterType = WaterType.WELL;
            } else if (StreamButton.isSelected()) {
                waterType = WaterType.STREAM;
            } else if (LakeButton.isSelected()) {
                waterType = WaterType.LAKE;
            } else if (SpringButton.isSelected()) {
                waterType = WaterType.SPRING;
            } else if (BottledButton.isSelected()) {
                waterType = WaterType.BOTTLED;
            }

            if (WasteButton.isSelected()) {
                waterOverallCondition = WaterOverallCondition.WASTE;
            } else if (TreatableClearButton.isSelected()) {
                waterOverallCondition = WaterOverallCondition.TREATABLE_CLEAR;
            } else if (TreatableMuddyButton.isSelected()) {
                waterOverallCondition = WaterOverallCondition.TREATABLE_MUDDY;
            } else if (PotableButton.isSelected()) {
                waterOverallCondition = WaterOverallCondition.POTABLE;
            }

            waterSourceReport = new WaterSourceReport(account, reportLocation, waterType, waterOverallCondition);

            try {
                WaterReportsHolder.addReport(waterSourceReport);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Submitting Report.");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                return;
            }
        }

        mainApp.showPostLogin();
    }

}
