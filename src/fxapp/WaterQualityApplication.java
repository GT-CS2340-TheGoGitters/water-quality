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

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        intialize();
    }

    public Account getCurrentAccount(){
        return currentAccount;
    }

    public void setCurrentAccount(Account account){
        this.currentAccount = account;
    }

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

    public static void main(String[] args) {
        launch(args);
    }
}
