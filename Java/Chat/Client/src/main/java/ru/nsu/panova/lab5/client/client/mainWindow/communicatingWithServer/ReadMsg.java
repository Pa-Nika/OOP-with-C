package ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer;

import ru.nsu.panova.lab5.client.client.Constants;
import ru.nsu.panova.lab5.client.client.mainWindow.ModelMainWindow;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import static ru.nsu.panova.lab5.client.client.Constants.COUNT_OF_TRYING_TO_CONNECT;
import static ru.nsu.panova.lab5.client.client.Constants.TIME_TO_SLEEP;

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
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        String str;
        try {
            while (active) {
                try {
                    str = readerToServer.readLine();
                    if (active)
                        modelMainWindow.jsonAdapter(str);

                } catch (SocketException ex) {
                    for (int i = 0; i < COUNT_OF_TRYING_TO_CONNECT; i++) {
                        sleep(TIME_TO_SLEEP);
                        try {
                            Socket clientSocket = new Socket(Constants.HOST_NAME, Constants.SOCKET);
                            readerToServer.close();
                            readerToServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            modelMainWindow.setClientSocket(clientSocket);
                            i = COUNT_OF_TRYING_TO_CONNECT;
                        } catch (SocketException ignored) { }

                    }
                }
            }
        }
        catch(IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeBuffer() throws IOException {
        readerToServer.close();
    }

}
