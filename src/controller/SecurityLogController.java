package controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.*;
import model.logging.security.Log;
import model.logging.security.SecurityLogEntry;
import view.ButtonCell;

import java.io.File;
import java.text.SimpleDateFormat;

public class SecurityLogController extends Controller {
    @FXML
    private TableView logTable;

    private ObservableList<SecurityLogEntry> data = FXCollections.observableArrayList();

    /**
     * Brings Account back to Admin Home screen
     */
    @FXML
    private void handleHomeClicked() {
        mainApp.showScreen(new File("../view/AdminHome.fxml"));
    }

    /**
     * Initializes SecurityLogController
     */
    @FXML
    private void initialize() {
        TableColumn timeCol = new TableColumn("Time");
        TableColumn usernameCol = new TableColumn("Acting User");
        TableColumn labelCol = new TableColumn("Label");
        TableColumn messageCol = new TableColumn("Message");

        timeCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<SecurityLogEntry, Boolean>, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures<SecurityLogEntry, Boolean> p) {
                        SecurityLogEntry entry = p.getValue();

                        String date = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(entry.getTime());

                        return new SimpleStringProperty(date);
                    }
                }
        );

        labelCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<SecurityLogEntry, Boolean>, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures<SecurityLogEntry, Boolean> p) {
                        return new SimpleStringProperty(p.getValue().getLabel());
                    }
                }
        );

        usernameCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<SecurityLogEntry, Boolean>, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures<SecurityLogEntry, Boolean> p) {
                        Account account = p.getValue().getActingAccount();

                        return new SimpleStringProperty(account != null ? account.toString() : "");
                    }
                }
        );

        messageCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<SecurityLogEntry, Boolean>, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures<SecurityLogEntry, Boolean> p) {
                        return new SimpleStringProperty(p.getValue().getMessage());
                    }
                }
        );

        data.addAll(Log.getEntries());

        logTable.setItems(data);

        logTable.getColumns().addAll(timeCol, usernameCol, labelCol, messageCol);
    }
}
