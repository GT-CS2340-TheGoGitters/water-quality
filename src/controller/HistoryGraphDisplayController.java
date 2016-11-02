package controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.*;
import model.logging.security.Log;
import model.logging.security.LoginAttemptEntry;

import javax.swing.*;
import java.io.IOException;


/**
 * Created by Joshua on 11/1/2016.
 */
public class HistoryGraphDisplayController {

    @FXML
    BorderPane containerPane;

    private WaterQualityApplication mainApp;

    public HistoryGraphDisplayController() {
    }

    /**
     * Gives the controller access to the main application
     *
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }

    public void setChart(Chart chart){
        containerPane.setCenter(chart);
    }

    /**
     * Returns to the history select screen when done is pressed
     */
    @FXML
    private void handleDonePressed() {
        mainApp.showHistoryInput();
    }
}
