package ru.nsu.panova.lab5.chat.client.entranceWindow;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerEntranceWindow extends ViewEntranceWindow {
    private ModelEntranceWindow modelEntranceWindow;

    @FXML
    private TextField textFieldWithLogin;

    @FXML
    void clickInButtonConnect() {
        modelEntranceWindow.connectToServer(textFieldWithLogin.getText());
    }

    public void setModelEntranceWindow(ModelEntranceWindow modelEntranceWindow) {
        this.modelEntranceWindow = modelEntranceWindow;
    }

    @FXML
    public void enterName() {
        clickInButtonConnect();
    }
}
