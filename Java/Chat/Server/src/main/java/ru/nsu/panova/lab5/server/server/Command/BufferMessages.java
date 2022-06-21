package ru.nsu.panova.lab5.server.server.Command;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.server.server.CommandExecutor;
import ru.nsu.panova.lab5.server.server.Constants;

import java.util.List;

public class BufferMessages extends CommandGetterType {
    private List<Message> FirstMessages;

    public BufferMessages() {

    }

    public void setBufferMessages(List<Message> messages) {
        setTypeCommand(Constants.COMMAND_FIRST_MESSAGES);
        FirstMessages = messages;
    }

    public List<Message> getFirstMessages() {
        return FirstMessages;
    }

    @Override
    public void runCommand(CommandExecutor commandExecutor, String json) {
        commandExecutor.sendFirstMessages();
    }
}

