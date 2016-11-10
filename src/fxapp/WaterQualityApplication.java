package fxapp;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import model.*;
import model.logging.security.ApplicationStartedEntry;
import model.logging.security.Log;
import model.logging.security.SecurityLogEntry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WaterQualityApplication extends Application {

    private AnchorPane baseLayout;

    private Stage mainStage;

    private Account currentAccount = null;

    private PrintWriter securityLog;

    /**
     * Starts the application
     * @param primaryStage the main stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setResizable(false);
        initialize();

        // Initialize Log
        File file = new File("security.log");
        FileWriter writer;
        try {
            writer = new FileWriter(file, true);
            securityLog = new PrintWriter(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logSecurityEvent(new ApplicationStartedEntry());
    }

    public Account getCurrentAccount(){
        return currentAccount;
    }

    /**
     * Sets up the current account
     * @param account the user account
     */
    public void setCurrentAccount(Account account){
        this.currentAccount = account;
    }

    /**
     * Initializes the application
     */
    private void initialize() {
        this.loadData();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/Welcome.fxml"));
            baseLayout = loader.load();

            WelcomeController controller = loader.getController();
            controller.setApp(this);

            mainStage.setTitle("H2lOcator");
            mainStage.setScene(new Scene(baseLayout));
            mainStage.show();
        } catch (IOException e) {
            System.out.print("Cannot load Welcome Screen");
        }

        Runtime.getRuntime().addShutdownHook(new Thread(this::saveData));
    }

    /**
     * Changes the screen
     *
     * @param file the FXML file that will be displayed
     */
    public void showScreen(File file) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/" + file.getName()));
            baseLayout = loader.load();

            Controller controller = loader.getController();
            controller.setApp(this);
            if (controller instanceof ProfileController) {
                ((ProfileController) controller).setUpProfile();
            }

            mainStage.setScene(new Scene(baseLayout));

        } catch (Exception ex) {
            System.out.println("Cannot load " + file.getName());
        }
    }

    /**
     * Loads the history graph screen.
     */
    public HistoryGraphDisplayController showHistoryDisplay() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/HistoryGraphDisplay.fxml"));
            baseLayout = loader.load();

            HistoryGraphDisplayController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));

            return controller;
        } catch (IOException e) {
            System.out.print("Cannot load History Display Screen: " + e.getMessage());
            return null;
        }
    }

    /**
     * Loads the recovery code screen
     */
    public EnterResetCodeController showEnterResetCode() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/EnterResetCode.fxml"));
            baseLayout = loader.load();

            EnterResetCodeController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));

            return controller;
        } catch (IOException e) {
            System.out.print("Cannot load Enter Reset Code Screen");
            return null;
        }
    }

    /**
     * Loads the reset password screen
     */
    public ResetPasswordController showResetPassword() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/ResetPassword.fxml"));
            baseLayout = loader.load();

            ResetPasswordController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));

            return controller;
        } catch (IOException e) {
            System.out.print("Cannot load Reset Password Screen");
            return null;
        }
    }

    /**
     * Logs and failed login attempts
     *
     * @param event the failed login attempt.
     */
    public void logSecurityEvent(SecurityLogEntry event){
        if(securityLog != null){
            securityLog.println(event.toString());
            securityLog.flush();
        }
        model.logging.security.Log.addEntry(event);
    }

    /**
     * Loads previously submitted WaterReports and Accounts
     */
    public void loadData() {
        WaterReportsHolder.loadReportsFromBinary();
        AccountsHolder.loadAccountsFromBinary();
        Log.loadAccountsFromBinary();
    }

    /**
     * Saves any newly submitted WaterReports and Accounts
     */
    public void saveData() {
        WaterReportsHolder.saveReportsToBinary();
        AccountsHolder.saveAccountsToBinary();
        Log.saveAccountsToBinary();
    }

    /**
     * launches the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
