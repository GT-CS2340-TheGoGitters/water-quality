package controller;

import fxapp.WaterQualityApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.WaterReport;
import model.WaterReportsHolder;

/**
 * Created by Joshua on 10/12/2016.
 */
public class ReportsController {
    @FXML
    private TableView reportsTable;

    private WaterQualityApplication mainApp;

    private ObservableList<WaterReport> data = FXCollections.observableArrayList();

    /**
     * Gives the controller access to the main application
     *
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }

    @FXML private void handleHomeClicked(){
        mainApp.showPostLogin();
    }

    @FXML
    private void initialize() {
        TableColumn dateCol = new TableColumn("Date");
        TableColumn accountCol = new TableColumn("Account");
        TableColumn locationCol = new TableColumn("Location");
        TableColumn typeCol = new TableColumn("Water Type");
        TableColumn conditionCol = new TableColumn("Water Condition");

        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("created")
        );

        accountCol.setCellValueFactory(
                new PropertyValueFactory<>("creator")
        );

        locationCol.setCellValueFactory(
                new PropertyValueFactory<>("location")
        );

        typeCol.setCellValueFactory(
                new PropertyValueFactory<>("waterType")
        );

        conditionCol.setCellValueFactory(
                new PropertyValueFactory<>("waterOverallCondition")
        );

        data.addAll(WaterReportsHolder.getReports());

        reportsTable.setItems(data);

        reportsTable.getColumns().addAll(dateCol, accountCol, locationCol, typeCol, conditionCol);
    }
}
