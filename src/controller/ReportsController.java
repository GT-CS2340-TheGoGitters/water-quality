package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.WaterPurityReport;
import model.WaterReport;
import model.WaterReportsHolder;
import model.WaterSourceReport;

import java.io.File;
import java.text.SimpleDateFormat;

public class ReportsController extends Controller {
    @FXML
    private TableView reportsTable;

    private final ObservableList<WaterReport> data = FXCollections.observableArrayList();

    /**
     * Brings Account back to PostLogin screen
     */
    @FXML private void handleHomeClicked(){
        mainApp.showScreen(new File("../view/PostLogin.fxml"));
    }

    /**
     * Initializes ReportsController
     */
    @FXML
    private void initialize() {
        TableColumn reportCol = new TableColumn("Report Type");
        TableColumn reportNumberCol = new TableColumn("ID");
        TableColumn dateCol = new TableColumn("Date");
        TableColumn accountCol = new TableColumn("Account");
        TableColumn locationCol = new TableColumn("Location");
        TableColumn typeCol = new TableColumn("Water Type");
        TableColumn conditionCol = new TableColumn("Condition");
        TableColumn virusCol = new TableColumn("Virus");
        TableColumn contaminationCol = new TableColumn("Contaminant");

        reportCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        WaterReport report = (WaterReport) dataFeatures.getValue();

                        String type = "";

                        if (report instanceof WaterSourceReport){
                            type = "Source";
                        } else if (report instanceof WaterPurityReport){
                            type = "Purity";
                        }

                        return new SimpleStringProperty(type);
                    }
                }
        );

        reportNumberCol.setCellValueFactory(
                new PropertyValueFactory<>("reportNumber")
        );

        dateCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        WaterReport report = (WaterReport) dataFeatures.getValue();

                        String date = new SimpleDateFormat("mm/dd/yyyy HH:mm").format(report.getCreated());

                        return new SimpleStringProperty(date);
                    }
                }
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
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        WaterReport report = (WaterReport) dataFeatures.getValue();

                        String condition = "";

                        if (report instanceof WaterSourceReport){
                            condition = ((WaterSourceReport) report).getWaterOverallCondition().toString();
                        } else if (report instanceof WaterPurityReport){
                            condition = ((WaterPurityReport) report).getWaterCondition().toString();
                        }

                        return new SimpleStringProperty(condition);
                    }
                }
        );

        virusCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        WaterReport report = (WaterReport) dataFeatures.getValue();

                        String virus = "";

                        if (report instanceof WaterPurityReport){
                            virus = ((WaterPurityReport) report).getVirusPPM() + " PPM";
                        }

                        return new SimpleStringProperty(virus);
                    }
                }
        );

        contaminationCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        WaterReport report = (WaterReport) dataFeatures.getValue();

                        String contaminant = "";

                        if (report instanceof WaterPurityReport){
                            contaminant = ((WaterPurityReport) report).getContaminantPPM() + " PPM";
                        }

                        return new SimpleStringProperty(contaminant);
                    }
                }
        );

        data.addAll(WaterReportsHolder.getValues());

        reportsTable.setItems(data);

        reportsTable.getColumns().addAll(reportCol, reportNumberCol, dateCol, accountCol, locationCol, typeCol, conditionCol, virusCol, contaminationCol);
    }
}
