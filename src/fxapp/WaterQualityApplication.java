package fxapp;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import model.*;
import sun.rmi.runtime.Log;

import java.io.IOException;

public class WaterQualityApplication extends Application {

    private AnchorPane baseLayout;

    private Stage mainStage;

    private Account currentAccount = null;

    /**
     * Starts the application
     * @param primaryStage the main stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setResizable(false);
        intialize();
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
            System.out.print("Cannot load Login Screen");
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

            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Edit Account Screen");
        }
    }

    /**
     * Loads the submit water repport screen.
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
            System.out.print("Cannot load Edit Account Screen");
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

    public static void main(String[] args) {
        launch(args);
    }
}
