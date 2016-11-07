package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.Chart;
import javafx.scene.layout.BorderPane;

import java.io.File;


public class HistoryGraphDisplayController extends Controller{

    @FXML
    BorderPane containerPane;

    public HistoryGraphDisplayController() {
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
