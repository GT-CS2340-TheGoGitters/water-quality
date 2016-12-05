package controller;

import fxapp.WaterQualityApplication;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.*;
import model.logging.security.ReportDeleteEntry;
import view.ButtonCell;

import java.io.File;
import java.text.SimpleDateFormat;

public class ManageReportsController extends Controller {
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
     * Initializes ManageReportsController
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

                        String date = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(report.getCreated());

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

    @Override
    public void setApp(WaterQualityApplication newApp) {
        super.setApp(newApp);

        if (newApp.getCurrentAccount().getAccountType() == AccountType.MGR) {
            TableColumn deleteCol = new TableColumn<>("Delete");
            deleteCol.setSortable(false);

            deleteCol.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<WaterReport, WaterReport>,
                            ObservableValue<WaterReport>>() {

                        @Override
                        public ObservableValue<WaterReport> call(TableColumn.CellDataFeatures<WaterReport, WaterReport> p) {
                            return new ReadOnlyObjectWrapper<>(p.getValue());
                        }
                    });


            deleteCol.setCellFactory(
                    new Callback<TableColumn<WaterReport, WaterReport>, ButtonCell<WaterReport>>() {

                        @Override
                        public ButtonCell<WaterReport> call(TableColumn<WaterReport, WaterReport> p) {
                            return new ButtonCell<WaterReport>("Delete") {
                                @Override
                                protected void updateItem(WaterReport item, boolean empty) {
                                    super.updateItem(item, empty);

                                    if (this.getTableRow() == null) {
                                        this.button.setVisible(false);
                                        return;
                                    }

                                    WaterReport report = (WaterReport) this.getTableRow().getItem();

                                    if (report == null) {
                                        this.button.setVisible(false);
                                        return;
                                    } else {
                                        this.button.setVisible(true);
                                    }

                                    ButtonCell<WaterReport> cell = this;

                                    this.button.setOnAction((e) -> {
                                        mainApp.logSecurityEvent(new ReportDeleteEntry(mainApp.getCurrentAccount(), report.getReportNumber()));
                                        WaterReportsHolder.deleteReport(report);
                                        data.removeAll(report);
                                    });
                                }
                            };
                        }

                    });

            reportsTable.getColumns().add(deleteCol);
        }
    }
}
