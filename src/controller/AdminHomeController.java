package controller;

import javafx.fxml.FXML;
import model.*;

import java.io.File;

public class AdminHomeController extends Controller {


    private Account account;

    public AdminHomeController() {

    }

    @FXML
    public void handleMngUsersClicked() {
        //mainApp.showMngUsersScreen();
    }

    @FXML
    public void handleViewLogClicked() {
        //mainApp.showSecurityLogScree();
    }

    @FXML
    public void handleMngReportsClicked() {
        //mainApp.showMngReportsScreen();
    }

    @FXML
    public void handleLogout() {
        mainApp.showScreen(new File("../view/Welcome.fxml"));
    }

}
