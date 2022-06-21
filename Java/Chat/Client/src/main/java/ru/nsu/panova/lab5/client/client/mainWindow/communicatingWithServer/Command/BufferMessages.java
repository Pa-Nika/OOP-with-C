package ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.Command;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.client.client.mainWindow.ModelMainWindow;

import java.util.List;

public class BufferMessages extends Message {
    private List<Message> FirstMessages;

    public BufferMessages() {

    }

    public void setMessageBuffer(String typeCommand, List<Message> messages) {
        setTypeCommand(typeCommand);
        FirstMessages = messages;
    }

    public List<Message> getMessageList() {
        return FirstMessages;
    }


    @Override
    public void runCommand(ModelMainWindow modelMainWindow, String json) {
        Gson gson = new Gson();
        modelMainWindow.loadFirstMessagesToChat(gson.fromJson(json, BufferMessages.class));
    }
}
