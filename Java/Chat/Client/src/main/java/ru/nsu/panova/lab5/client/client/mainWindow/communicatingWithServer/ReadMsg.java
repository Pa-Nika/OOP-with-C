package ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer;

import ru.nsu.panova.lab5.client.client.mainWindow.ModelMainWindow;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ReadMsg extends Thread {
    private final ModelMainWindow modelMainWindow;
    private BufferedReader readerToServer = null;
    private boolean active = true;

    public void stopRead() {
        active = false;
    }

    public ReadMsg(Socket clientSocket, ModelMainWindow modelMainWindow) {
        this.modelMainWindow = modelMainWindow;
        System.out.println(clientSocket);
        try {
            readerToServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String str;
        try {
            while (active) {
                str = readerToServer.readLine();
                if (active)
                    modelMainWindow.jsonAdapter(str);
            }
        } catch (SocketException ignored) {
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeBuffer() throws IOException {
        readerToServer.close();
    }

}
