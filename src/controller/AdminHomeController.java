package controller;

import javafx.fxml.FXML;
import model.*;

import java.io.File;

public class AdminHomeController extends Controller {


    @FXML
    public void handleMngUsersClicked() {
        mainApp.showScreen(new File("../view/ManageUsers.fxml"));
    }

    @FXML
    public void handleViewLogClicked() {
        mainApp.showScreen(new File("../view/SecurityLog.fxml"));
    }

    @FXML
    public void handleLogout() {
        mainApp.setCurrentAccount(null);
        mainApp.showScreen(new File("../view/Welcome.fxml"));
    }

}
