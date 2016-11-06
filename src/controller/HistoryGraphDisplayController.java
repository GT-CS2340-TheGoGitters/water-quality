package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.chart.Chart;
import javafx.scene.layout.BorderPane;

import java.io.File;


/**
 * Created by Joshua on 11/1/2016.
 */
public class HistoryGraphDisplayController implements Controller{

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
        mainApp.showScreen(new File("../view/HistoryGraphInput.fxml"));
    }
}
