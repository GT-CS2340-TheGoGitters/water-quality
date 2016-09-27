package controller;

import fxapp.WaterQualityApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.User;
import model.UserType;

/**
 * Created by Allison on 9/27/16.
 */
public class RegisterController {

    private WaterQualityApplication mainApp;

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private RadioButton UserSelectButton;

    @FXML
    private RadioButton WorkerSelectButton;

    @FXML
    private RadioButton ManagerSelectButton;

    @FXML
    private RadioButton AdminSelectButton;

    private User user;

    public RegisterController() {
        user = new User();
    }

    public void setApp(WaterQualityApplication newApp) { mainApp = newApp;}

    @FXML
    private void handleCancelPressed() {
        mainApp.returnToWelcomeScreen();
    }

    @FXML
    private void handleRegisterClick() {
        if (name.getText().length() != 0 &&
                username.getText().length() != 0 &&
                password.getText().length() != 0) {
            user.setName(name.getAccessibleText());
            user.setUsername(username.getAccessibleText());
            user.setPassword(password.getAccessibleText());

            if (UserSelectButton.isSelected()) {
                user.setAccount(UserType.USR);
            } else if (WorkerSelectButton.isSelected()) {
                user.setAccount(UserType.WKR);
            } else if (ManagerSelectButton.isSelected()) {
                user.setAccount(UserType.MGR);
            } else if (AdminSelectButton.isSelected()) {
                user.setAccount(UserType.ADM);
            }
            mainApp.returnToWelcomeScreen();
        }
    }

}
