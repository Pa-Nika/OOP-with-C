package ru.nsu.panova.lab5.server.server;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.server.server.Command.CommandInterface;

import java.io.*;
import java.net.Socket;

public class СommunicatorForClients extends Thread {
    private final BufferedReader in;
    private final BufferedWriter out;
    private final CommandExecutor commandExecutor;
    private boolean active = true;
    private String userName;


    public void setActiveFalse() {
        this.active = false;
    }


    public СommunicatorForClients(Socket socket, CommandExecutor commandExecutor) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        socket.getInputStream().available();
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.commandExecutor = commandExecutor;
        commandExecutor.setCommunicator(this);
        start();
    }

    ////////////////////////////////////////////////////////////

    @Override
    public void run() {
        String command;
        try {
            while (active) {
                command = in.readLine();
                if(active)
                    commandExecutor.jsonAdapter(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void send(String msg) {
        try {
            System.out.println(msg);
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void sendAll(CommandInterface command) {
        Gson gson = new Gson();
        String json = gson.toJson(command);

        for (СommunicatorForClients vr : Server.serverList) {
            vr.send(json);

        }
    }

    public void sendSpecificClient(CommandInterface command) {
        Gson gson = new Gson();
        String json = gson.toJson(command);
        send(json);
    }

    public void sendEveryoneExceptMyself (CommandInterface command) {
        Gson gson = new Gson();
        String json = gson.toJson(command);
        for (СommunicatorForClients vr : Server.serverList) {
            if (!vr.getUserName().equals(this.userName))
                vr.send(json);
        }
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        userName = name;
    }

}
