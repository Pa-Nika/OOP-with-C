package ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.Command;

import ru.nsu.panova.lab5.client.client.mainWindow.ModelMainWindow;

public interface CommandInterface {
    void runCommand(ModelMainWindow modelMainWindow, String json);
}
