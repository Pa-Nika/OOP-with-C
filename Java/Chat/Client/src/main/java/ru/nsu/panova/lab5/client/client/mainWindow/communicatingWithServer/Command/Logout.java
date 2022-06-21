package ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.Command;

import ru.nsu.panova.lab5.client.client.Constants;
import ru.nsu.panova.lab5.client.client.mainWindow.ModelMainWindow;

public class Logout extends CommandGetterType {
    private String userName;

    public void setLogout(String name) {
        setTypeCommand(Constants.COMMAND_LOGOUT);
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void runCommand(ModelMainWindow modelMainWindow, String json) {
        modelMainWindow.sendNotificationLogout();
    }
}
