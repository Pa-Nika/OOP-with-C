package ru.nsu.panova.lab5.client.client.mainWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ControllerMainWindow extends ViewMainWindow{
    private ModelMainWindow modelMainWindow;

    @FXML
    private Button buttonSend;

    @FXML
    private TextField messageTextField;


    @FXML
    void sendMassage() {
        if (messageTextField.getLength() != 0) {
            modelMainWindow.sendMsg(messageTextField.getText());
            messageTextField.setText(null);
        }
    }

    public void setModelMainWindow(ModelMainWindow modelMainWindow) {
        this.modelMainWindow = modelMainWindow;
    }

    @FXML
    public void enterMessage() {
        sendMassage();
    }
}
