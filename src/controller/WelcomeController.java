package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private Button login;

    private boolean _loginClicked = false;

    public final void setOnAction(EventHandler<ActionEvent> value) {
        _loginClicked = true;
    }

    @FXML
    private void handleLoginClick() {
        if (_loginClicked) {
            //TODO: make it switch screens
        }
    }

}
