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

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

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
        mainApp.showSceen(new File("../view/Welcome.fxml"));
    }

}
