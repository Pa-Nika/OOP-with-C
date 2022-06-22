package ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.client.client.mainWindow.ModelMainWindow;
import ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.Command.CommandInterface;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class WriteMsg {
    private BufferedWriter senderInServer;
    private final ModelMainWindow modelMainWindow;

    public WriteMsg(Socket clientSocket, ModelMainWindow modelMainWindow) {
        this.modelMainWindow = modelMainWindow;
        try {
            senderInServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sender(CommandInterface command) {
        Gson gson = new Gson();
        String json = gson.toJson(command);
        System.out.println(json);
        try {
            senderInServer.write(json);
            senderInServer.newLine();
            senderInServer.flush();
        } catch (SocketException e) {
            System.out.println(e.getMessage());
            modelMainWindow.serverEndWork();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeBuffer() throws IOException {
        senderInServer.close();
    }

    public void setClientSocket(Socket socket) {
        try {
            closeBuffer();
            senderInServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loginWithNewSocket() {
        modelMainWindow.login();
    }

    public void requestWithNewSocket() {
        modelMainWindow.sendRequest();
    }
}
