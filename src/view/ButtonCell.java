package view;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class ButtonCell<S> extends TableCell<S, S> {


    public Button button;

    public ButtonCell(String text) {
        button = new Button("Action");
        button.setText(text);
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(S t, boolean empty) {
        super.updateItem(t, empty);
        if (!empty) {
            setGraphic(button);
        }
    }
}