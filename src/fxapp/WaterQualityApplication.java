package fxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import controller.*;

import model.*;
import sun.rmi.runtime.Log;

import java.io.IOException;

public class WaterQualityApplication extends Application {

    private AnchorPane baseLayout;

    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        intialize();
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
            mainStage.setScene(new Scene(baseLayout));
        } catch (IOException e) {
            System.out.print("Cannot load Login Screen");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
