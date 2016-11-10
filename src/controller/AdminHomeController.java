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
        // TODO: Implement manage users
    }

    @FXML
    public void handleViewLogClicked() {
        // TODO: Implement view security log
    }

    @FXML
    public void handleMngReportsClicked() {
        // TODO: Implement manage reports
    }

    @FXML
    public void handleLogout() {
        mainApp.showScreen(new File("../view/Welcome.fxml"));
    }

}
