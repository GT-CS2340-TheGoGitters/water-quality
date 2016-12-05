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
import model.logging.security.AccountBanEntry;
import model.logging.security.AccountDeleteEntry;
import model.logging.security.AccountUnbanEntry;
import model.logging.security.AccountUnblockEntry;
import view.ButtonCell;

import java.io.File;
import java.text.SimpleDateFormat;

public class ManageUsersController extends Controller {
    @FXML
    private TableView usersTable;

    private ObservableList<Account> data = FXCollections.observableArrayList();

    /**
     * Brings Account back to Admin Home screen
     */
    @FXML
    private void handleHomeClicked() {
        mainApp.showScreen(new File("../view/AdminHome.fxml"));
    }

    /**
     * Initializes ManageReportsController
     */
    @FXML
    private void initialize() {
        TableColumn usernameCol = new TableColumn("Username");
        TableColumn accountTypeCol = new TableColumn("Type");
        TableColumn nameCol = new TableColumn("Name");
        TableColumn passwordChangedCol = new TableColumn("Last Password Change");

        TableColumn banCol = new TableColumn<>("Ban/Unban");
        banCol.setSortable(false);

        banCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Account, Account>,
                        ObservableValue<Account>>() {

                    @Override
                    public ObservableValue<Account> call(TableColumn.CellDataFeatures<Account, Account> p) {
                        return new ReadOnlyObjectWrapper<>(p.getValue());
                    }
                });


        banCol.setCellFactory(
                new Callback<TableColumn<Account, Account>, ButtonCell<Account>>() {

                    @Override
                    public ButtonCell<Account> call(TableColumn<Account, Account> p) {
                        return new ButtonCell<Account>("") {
                            @Override
                            protected void updateItem(Account item, boolean empty) {
                                super.updateItem(item, empty);

                                if(this.getTableRow() == null){
                                    this.button.setVisible(false);
                                    return;
                                }

                                Account account = (Account)this.getTableRow().getItem();

                                if(account == null){
                                    this.button.setVisible(false);
                                    return;
                                }

                                if(account.getAccountType() == AccountType.ADM){
                                    this.button.setVisible(false);
                                } else {
                                    this.button.setVisible(true);
                                }

                                this.button.setText(account.getIsBanned() ? "Unban" : "Ban");

                                this.button.setOnAction((e) -> {
                                    if (account.getIsBanned()) {
                                        account.unban();
                                        mainApp.logSecurityEvent(new AccountUnbanEntry(mainApp.getCurrentAccount(), account.getUsername()));
                                        this.button.setText("Ban");
                                    } else {
                                        account.ban();
                                        mainApp.logSecurityEvent(new AccountBanEntry(mainApp.getCurrentAccount(), account.getUsername()));
                                        this.button.setText("Unban");
                                    }
                                });
                            }
                        };
                    }

                });

        TableColumn lockCol = new TableColumn<>("Unlock");
        lockCol.setSortable(false);

        lockCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Account, Account>,
                        ObservableValue<Account>>() {

                    @Override
                    public ObservableValue<Account> call(TableColumn.CellDataFeatures<Account, Account> p) {
                        return new ReadOnlyObjectWrapper<>(p.getValue());
                    }
                });


        lockCol.setCellFactory(
                new Callback<TableColumn<Account, Account>, ButtonCell<Account>>() {

                    @Override
                    public ButtonCell<Account> call(TableColumn<Account, Account> p) {
                        return new ButtonCell<Account>("Unlock") {
                            @Override
                            protected void updateItem(Account item, boolean empty) {
                                super.updateItem(item, empty);

                                if(this.getTableRow() == null){
                                    this.button.setVisible(false);
                                    return;
                                }

                                Account account = (Account)this.getTableRow().getItem();

                                if(account == null){
                                    this.button.setVisible(false);
                                    return;
                                }

                                if(!account.getIsLocked()){
                                    this.button.setVisible(false);
                                } else {
                                    this.button.setVisible(true);
                                }

                                this.button.setOnAction((e) -> {
                                    account.resetIncorrectAttempts();
                                    mainApp.logSecurityEvent(new AccountUnblockEntry(mainApp.getCurrentAccount(), account.getUsername()));
                                    this.button.setVisible(false);
                                });
                            }
                        };
                    }

                });

        TableColumn deleteCol = new TableColumn<>("Delete");
        deleteCol.setSortable(false);

        deleteCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Account, Account>,
                        ObservableValue<Account>>() {

                    @Override
                    public ObservableValue<Account> call(TableColumn.CellDataFeatures<Account, Account> p) {
                        return new ReadOnlyObjectWrapper<>(p.getValue());
                    }
                });


        deleteCol.setCellFactory(
                new Callback<TableColumn<Account, Account>, ButtonCell<Account>>() {

                    @Override
                    public ButtonCell<Account> call(TableColumn<Account, Account> p) {
                        return new ButtonCell<Account>("Delete") {
                            @Override
                            protected void updateItem(Account item, boolean empty) {
                                super.updateItem(item, empty);

                                if(this.getTableRow() == null){
                                    this.button.setVisible(false);
                                    return;
                                }

                                Account account = (Account)this.getTableRow().getItem();

                                if(account == null){
                                    this.button.setVisible(false);
                                    return;
                                }

                                if(account == mainApp.getCurrentAccount()){
                                    this.button.setVisible(false);
                                } else {
                                    this.button.setVisible(true);
                                }

                                ButtonCell<Account> cell = this;

                                this.button.setOnAction((e) -> {
                                    mainApp.logSecurityEvent(new AccountDeleteEntry(mainApp.getCurrentAccount(), account.getUsername()));
                                    AccountsHolder.deleteAccount(account);
                                    data.removeAll(account);
                                });
                            }
                        };
                    }

                });

        passwordChangedCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Account, Boolean>, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures<Account, Boolean> p) {
                        Account account = p.getValue();

                        String date = new SimpleDateFormat("m/dd/yyyy HH:mm").format(account.getLastPasswordChange());

                        return new SimpleStringProperty(date);
                    }
                }
        );

        usernameCol.setCellValueFactory(
                new PropertyValueFactory<>("username")
        );

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        accountTypeCol.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        data.addAll(AccountsHolder.getValues());

        usersTable.setItems(data);

        usersTable.getColumns().addAll(usernameCol, accountTypeCol, nameCol, passwordChangedCol, banCol, lockCol, deleteCol);
    }
}
