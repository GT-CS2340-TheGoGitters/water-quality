package controller;

import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import fxapp.WaterQualityApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Created by Joshua on 10/13/16.
 */
public class WaterReportController {

    protected WaterQualityApplication mainApp;

    @FXML
    protected TextField WaterLocationField;

    @FXML
    protected void handleGeocodePressed() {
        doGeoCode(false);
    }

    protected void handleSubmitPressed() {
    }

    protected void doGeoCode(boolean submitOnCompletion) {
        String waterLocation = WaterLocationField.getText();
        new GeocodingService().geocode(waterLocation, new GeocodingServiceCallback() {
            @Override
            public void geocodedResultsReceived(GeocodingResult[] geocodingResults, GeocoderStatus geocoderStatus) {

                if (geocodingResults.length == 0) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Geocode Error");
                        alert.setHeaderText("Address Error");
                        alert.setContentText("Please enter a valid address or lattitude, longitude.");
                        alert.showAndWait();
                    });
                }

                GeocodingResult result = geocodingResults[0];

                LatLong location = result.getGeometry().getLocation();

                WaterLocationField.setText(location.getLatitude() + ", " + location.getLongitude());

                if (submitOnCompletion) {
                    handleSubmitPressed();
                }
            }
        });
    }

    /**
     * Gives the controller access to the main application
     *
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }
}
