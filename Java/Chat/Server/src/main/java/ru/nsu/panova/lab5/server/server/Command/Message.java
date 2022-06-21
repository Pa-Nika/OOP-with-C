package ru.nsu.panova.lab5.server.server.Command;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.server.server.CommandExecutor;

public class Message extends CommandGetterType {
    private String userName;
    private long timeSend;
    private String message;

    public Message() {

    }

    public Message(String typeCommand, String name, String msg, long time) {
        setTypeCommand(typeCommand);
        this.message = msg;
        this.timeSend = time;
        this.userName = name;
    }

    @Override
    public void runCommand(CommandExecutor commandExecutor, String json) {
        Gson gson = new Gson();
        commandExecutor.addMassage(gson.fromJson(json, Message.class));
    }
}
