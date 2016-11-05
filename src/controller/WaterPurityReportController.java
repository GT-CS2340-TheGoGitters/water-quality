package controller;

import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.*;

/**
 * Created by Jack on 10/13/16.
 */
public class WaterPurityReportController extends WaterReportController implements Controller{

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

    private int virusPPM;

    private int containmentPPM;


    public WaterPurityReportController() { }

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
    protected void handleSubmitPressed() {
        if (WaterLocationField.getText().length() > 0 &&
                VirusPPMField.getText().length() > 0 &&
                ContainmentPPMField.getText().length() > 0) {
            account = mainApp.getCurrentAccount();

            String waterLocation = WaterLocationField.getText();
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

            waterPurityReport = new WaterPurityReport(account, reportLocation, waterCondition, virusPPM, containmentPPM);

            try {
                WaterReportsHolder.addReport(waterPurityReport);
            } catch (Exception ex) {
                return;
            }

            mainApp.showPostLogin();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Report Submission Error");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText("Enter all required information.");
            alert.showAndWait();
            return;
        }
    }
}
