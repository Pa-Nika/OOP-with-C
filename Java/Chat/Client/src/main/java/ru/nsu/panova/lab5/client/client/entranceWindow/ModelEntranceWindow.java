package ru.nsu.panova.lab5.client.client.entranceWindow;

import ru.nsu.panova.lab5.client.client.Client;
import ru.nsu.panova.lab5.client.client.Constants;
import ru.nsu.panova.lab5.client.client.mainWindow.LoaderMainWindow;
import ru.nsu.panova.lab5.client.client.observer.Observable;

import java.io.*;
import java.net.Socket;

public class ModelEntranceWindow extends Observable {
    public void connectToServer(String nameUser) {
        try {
            Socket clientSocket = new Socket(Constants.HOST_NAME, Constants.SOCKET);
            LoaderMainWindow loaderMainWindow = new LoaderMainWindow();

            loaderMainWindow.setClientSocket(clientSocket);
            loaderMainWindow.setUserName(nameUser);
            Client.setNewLoader(loaderMainWindow);
        } catch (IOException e) {
            notifyObservers();
        }

    }
}
