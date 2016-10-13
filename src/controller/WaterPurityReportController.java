package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.*;

/**
 * Created by Jack on 10/13/16.
 */
public class WaterPurityReportController {

    private WaterQualityApplication mainApp;

    @FXML
    private TextField WaterLocationField;
    @FXML
    private TextField VirusPPMField;
    @FXML
    private TextField ContainmentPPMField;

    @FXML
    private RadioButton SafeButton;
    @FXML
    private RadioButton TreatableButton;
    @FXML
    private RadioButton UnsafeButton;

    private Account account;

    private WaterCondition waterCondition;

    private ReportLocation reportLocation;

    private WaterPurityReport waterPurityReport;


    public WaterPurityReport() {

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
        if (WaterLocationField.getText().length() > 0 &&
                VirusPPMField.getText().length() > 0 &&
                ContainmentPPMField.getText().length() > 0) {
            String waterLocation = WaterLocationField.getText();
            try {
                String[] latLong = waterLocation.split(",");
                double latitude = Double.parseDouble(latLong[0]);
                double longitude = Double.parseDouble(latLong[1]);
                reportLocation = new ReportLocation(latitude, longitude);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Report Submission Error");
                alert.setHeaderText("Error in Water Location");
                alert.setContentText("Enter location properly.\nlatitude, longitude");
                alert.showAndWait();
                return;
            }

            if (SafeButton.isSelected()) {
                waterCondition = WaterCondition.SAFE;
            } else if (TreatableButton.isSelected()) {
                waterCondition = WaterCondition.TREATABLE;
            } else if (UnsafeButton.isSelected()) {
                waterCondition = WaterCondition.UNSAFE;
            }

            int virusPPM = Integer.parseInt(VirusPPMField.getText());

            int containmentPPM = Integer.parseInt(ContainmentPPMField.getText());

            waterPurityReport = new WaterPurityReport(account, reportLocation, waterCondition, virusPPM, containmentPPM);

            try {
                WaterReportsHolder.addReport(waterPurityReport);
            } catch (Exception ex) {
                return;
            }
        }
    }
}
