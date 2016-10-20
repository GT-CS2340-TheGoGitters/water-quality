package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import model.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

/**
 * Created by Ashwin on 9/17/2016.
 */
public class PostLoginController implements Initializable, MapComponentInitializedListener {
    @FXML
    private MenuItem WaterPurityReportDropDown;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    private WaterQualityApplication mainApp;

    private Queue<WaterReport> pendingReports = new LinkedList<>();

    private boolean mapInitialized = false;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    /**
     * Gives the controller access to the main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;

        // Handle user ACL
        WaterPurityReportDropDown.setDisable(mainApp.getCurrentAccount().getAccountType() == AccountType.USR);

        for (WaterReport report : WaterReportsHolder.getReports()) {
            addWaterReport(report);
        }
    }

    /**
     * Initialize the map for the PostLogin page
     */
    @Override
    public void mapInitialized() {
        mapInitialized = true;

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(39.8282, -98.5795))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(4);

        map = mapView.createMap(mapOptions);

        while (pendingReports.size() != 0) {
            addWaterReport(pendingReports.remove());
        }
    }

    /**
     * Puts WaterReport on map
     * @param report WaterReport to be added to map
     */
    private void addWaterReport(WaterReport report) {
        if (!mapInitialized) {
            pendingReports.add(report);
            return;
        }

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(report.getLocation().toLatLong());

        // Icons from http://kml4earth.appspot.com/icons.html
        String infoWindowContent = "";
        if (report instanceof WaterSourceReport) {
            WaterSourceReport sourceReport = (WaterSourceReport) report;

            // Set Icon
            markerOptions.icon("http://maps.google.com/mapfiles/kml/pal5/icon11.png");

            // Set Info Window Text
            infoWindowContent += "<h2>Water Source Report</h2>";
            infoWindowContent += "<div><strong>Account:</strong> " + report.getCreator() + "</div>";
            infoWindowContent += "<div><strong>Date:</strong> " + new SimpleDateFormat("mm/dd/yyy HH:mm").format(report.getCreated()) + "</div>";
            infoWindowContent += "<div><strong>Type:</strong> " + sourceReport.getWaterType() + "</div>";
            infoWindowContent += "<div><strong>Overall Condition:</strong> " + sourceReport.getWaterOverallCondition() + "</div>";
        } else if (report instanceof WaterPurityReport) {
            WaterPurityReport purityReport = (WaterPurityReport) report;
            WaterCondition condition = purityReport.getWaterCondition();

            if (condition == WaterCondition.SAFE) {
                markerOptions.icon("http://maps.google.com/mapfiles/kml/pal3/icon53.png");
            } else if (condition == WaterCondition.TREATABLE) {
                markerOptions.icon("http://maps.google.com/mapfiles/kml/pal3/icon49.png");
            } else if (condition == WaterCondition.UNSAFE) {
                markerOptions.icon("http://maps.google.com/mapfiles/kml/pal3/icon51.png");
            }

            // Set Info Window Text
            infoWindowContent += "<h2>Water Purity Report</h2>";
            infoWindowContent += "<div><strong>Account:</strong> " + report.getCreator() + "</div>";
            infoWindowContent += "<div><strong>Date:</strong> " + new SimpleDateFormat("mm/dd/yyy HH:mm").format(report.getCreated()) + "</div>";
            infoWindowContent += "<div><strong>Condition:</strong> " + condition + "</div>";
            infoWindowContent += "<div><strong>Virus:</strong> " + purityReport.getVirusPPM() + " PPM</div>";
            infoWindowContent += "<div><strong>Contaminant:</strong> " + purityReport.getContaminantPPM() + " PPM</div>";
        }

        Marker marker = new Marker(markerOptions);

        map.addMarker(marker);

        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content(infoWindowContent);

        InfoWindow infoWindow = new InfoWindow(infoWindowOptions);

        map.addUIEventHandler(marker, UIEventType.click, (netscape.javascript.JSObject obj) -> {
            infoWindow.open(map, marker);
        });
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

    /**
     * Brings user to WaterSourceReport page
     */
    @FXML
    private void handleWaterSourceReport() { mainApp.showWaterSourceReport(); }

    /**
     * Makes sure that Users don't have access to submit WaterPurityReport
     */
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

    /**
     * Shows WaterReports
     */
    @FXML
    private void handlViewReports() { mainApp.showWaterReports(); }
}
