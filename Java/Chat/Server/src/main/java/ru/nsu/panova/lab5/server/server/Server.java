package ru.nsu.panova.lab5.server.server;

import ru.nsu.panova.lab5.server.server.Command.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static ru.nsu.panova.lab5.server.server.Constants.BUFFER_SIZE;

public class Server {

    public static final int PORT = 11111;
    public static List<CommunicatorForClients> serverList = new ArrayList<>();
    public static List<Message> messageList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println("CONNECT");
                try {
                    serverList.add(new CommunicatorForClients(socket, new CommandExecutor()));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }

    public static void delMember(CommunicatorForClients communicatorDell) {
        serverList.remove(communicatorDell);
        if (serverList.isEmpty()) {
            messageList.clear();
        }
    }

    public static void addMessage(Message msg) {
        if (messageList.toArray().length != BUFFER_SIZE){
            messageList.add(msg);
        }
        if (messageList.toArray().length == BUFFER_SIZE){
            messageList.remove(0);
            messageList.add(msg);
        }
    }
}