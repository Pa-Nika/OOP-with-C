package ru.nsu.panova.lab5.chat.client.mainWindow.communicatingWithServer.Command;

import ru.nsu.panova.lab5.chat.client.mainWindow.ModelMainWindow;

public interface CommandInterface {
    void runCommand(ModelMainWindow modelMainWindow, String json);
}
