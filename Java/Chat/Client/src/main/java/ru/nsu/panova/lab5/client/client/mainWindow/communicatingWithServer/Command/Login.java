package ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.Command;

import ru.nsu.panova.lab5.client.client.mainWindow.ModelMainWindow;

public class Login extends CommandGetterType {
    private String userName;

    public void setLogin(String typeCommand, String userName) {
        setTypeCommand(typeCommand);
        this.userName = userName;
    }

    @Override
    public void runCommand(ModelMainWindow modelMainWindow, String json) {
    }
}
