package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.*;

import java.io.File;

public class WaterPurityReportController extends WaterReportController {

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

    private WaterCondition waterCondition;


    public WaterPurityReportController() { }

    /*
     * Brings user back to PostLogin screen
     */
    @FXML
    private void handleCancelPressed() {
        mainApp.showScreen(new File("../view/PostLogin.fxml"));
    }

    /*
     * Checks to make sure that latitude and longitude are filled out
     * Then adds information to WaterReportsHolder
     * Then returns to PostLogin screen
     */
    @FXML
    protected void handleSubmitPressed() {
        if (WaterLocationField.getText().length() > 0 &&
                VirusPPMField.getText().length() > 0 &&
                ContainmentPPMField.getText().length() > 0) {
            Account account = mainApp.getCurrentAccount();

            String waterLocation = WaterLocationField.getText();
            ReportLocation reportLocation;
            try {
                String[] latLong = waterLocation.split(",");
                double latitude = Double.parseDouble(latLong[0]);
                double longitude = Double.parseDouble(latLong[1]);
                reportLocation = new ReportLocation(latitude, longitude);
            } catch (Exception ex) {
                // Geocode and wait
                doGeoCode(true);
                return;
            }

            if (SafeButton.isSelected()) {
                waterCondition = WaterCondition.SAFE;
            } else if (TreatableButton.isSelected()) {
                waterCondition = WaterCondition.TREATABLE;
            } else if (UnsafeButton.isSelected()) {
                waterCondition = WaterCondition.UNSAFE;
            }

            int virusPPM;
            try {
                virusPPM = Integer.parseInt(VirusPPMField.getText());
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("VirusPPM Error");
                alert.setHeaderText("Improper data");
                alert.setContentText("Enter integer value for virusPPM");
                alert.showAndWait();
                return;
            }

            int containmentPPM;
            try {
                containmentPPM = Integer.parseInt(ContainmentPPMField.getText());
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ContainmentPPM Error");
                alert.setHeaderText("Improper data");
                alert.setContentText("Enter integer value for containmentPPM");
                alert.showAndWait();
                return;
            }

            WaterPurityReport waterPurityReport = new WaterPurityReport(account, reportLocation, waterCondition, virusPPM, containmentPPM);

            try {
                WaterReportsHolder.addReport(waterPurityReport);
            } catch (Exception ex) {
                return;
            }

            mainApp.showScreen(new File("../view/PostLogin.fxml"));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Report Submission Error");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText("Enter all required information.");
            alert.showAndWait();
        }
    }
}
