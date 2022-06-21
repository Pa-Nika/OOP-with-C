package ru.nsu.panova.lab5.client.client.entranceWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerEntranceWindow extends ViewEntranceWindow {
    private ModelEntranceWindow modelEntranceWindow;

    @FXML
    private TextField textFieldWithLogin;

    @FXML
    private Label setName;

    @FXML
    void clickInButtonConnect() {
        if (textFieldWithLogin.getLength() != 0)
            modelEntranceWindow.connectToServer(textFieldWithLogin.getText());
        else
            setNameMessage();
    }

    public void setModelEntranceWindow(ModelEntranceWindow modelEntranceWindow) {
        this.modelEntranceWindow = modelEntranceWindow;
    }

    public void setNameMessage() {
        setName.setText("Enter your name");
    }

    @FXML
    public void enterName() {
        clickInButtonConnect();
    }
}
