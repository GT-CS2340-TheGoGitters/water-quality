package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

/**
 * Created by Allison on 10/20/16.
 */
public class AdminHomeController implements Initializable, MapComponentInitializedListener{

    private WaterQualityApplication mainApp;

    private Account account;

    private GoogleMap map;

    @FXML
    private GoogleMapView mapView;

    @FXML
    private MenuItem mngUsers;

    @FXML
    private MenuItem viewLog;

    @FXML
    private MenuItem mngReports;

    @FXML
    private MenuItem logout;

    private boolean mapInitialized = false;

    private Queue<WaterReport> pendingReports = new LinkedList<>();

    public AdminHomeController() {

    }

    /**
     * Give controller access to main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;

        for (WaterReport report : WaterReportsHolder.getValues()) {
            addWaterReport(report);
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    /**
     * Initialize the map for the AdminHome page
     */
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
