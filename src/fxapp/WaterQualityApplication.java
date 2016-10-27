package fxapp;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import model.*;
import model.logging.security.ApplicationStartedEntry;
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
        intialize();

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
    public void intialize() {
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
    }

    /**
     * Returns the user to the welcome screen
     */
    public void returnToWelcomeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/Welcome.fxml"));
            baseLayout = loader.load();

            WelcomeController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Welcome Screen");
        }
    }

    /**
     * Loads the login screen
     */
    public void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/Login.fxml"));
            baseLayout = loader.load();

            LoginController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Login Screen");
        }
    }

    /**
     * Loads the post login screen
     */
    public void showPostLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/PostLogin.fxml"));
            baseLayout = loader.load();

            PostLoginController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Post Login Screen");
        }
    }

    /**
     * Loads the registration screen
     */
    public void showRegister() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/Registration.fxml"));
            baseLayout = loader.load();

            RegisterController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Registration Screen");
        }
    }

    /**
     * Loads the profile screen
     */
    public void showProfile() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/Profile.fxml"));
            baseLayout = loader.load();

            ProfileController controller = loader.getController();
            controller.setApp(this);
            controller.setUpProfile();

            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Profile Screen.");
        }
    }

    /**
     * Loads the edit account screen
     */
    public void showEditAccount() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/EditAccount.fxml"));
            baseLayout = loader.load();

            EditAccountController controller = loader.getController();
            controller.setApp(this);
            controller.setUpEditPage();

            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Edit Account Screen");
        }
    }

    /**
     * Loads the submit water source report screen.
     */
    public void showWaterSourceReport() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/WaterSourceReport.fxml"));
            baseLayout = loader.load();

            WaterSourceReportController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Water Source Report Screen");
        }
    }

    /**
     * Loads the submit water purity report screen.
     */
    public void showWaterPutrityReport() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/WaterPurityReport.fxml"));
            baseLayout = loader.load();

            WaterPurityReportController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Water Purity Report Screen");
        }
    }

    /**
     * Loads the view water reports screen.
     */
    public void showWaterReports() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/ReportsView.fxml"));
            baseLayout = loader.load();

            ReportsController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Reports Screen: " + e.getMessage());
        }
    }

    /**
     * Loads the reset password screen
     */
    public void showSendResetEmail() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/SendResetEmail.fxml"));
            baseLayout = loader.load();

            SendResetEmailController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Send Reset Email Screen");
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
     * Loads the reset password screen
     */
    public AdminHomeController showAdminHome() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WaterQualityApplication.class.getResource("../view/AdminHome.fxml"));
            baseLayout = loader.load();

            AdminHomeController controller = loader.getController();
            controller.setApp(this);

            mainStage.setScene(new Scene(baseLayout));

            return controller;
        } catch (IOException e) {
            System.out.print("Cannot load Admin Home screen");
            return null;
        }
    }

    public void logSecurityEvent(SecurityLogEntry event){
        if(securityLog != null){
            securityLog.println(event.toString());
            securityLog.flush();
        }
        model.logging.security.Log.addEntry(event);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
