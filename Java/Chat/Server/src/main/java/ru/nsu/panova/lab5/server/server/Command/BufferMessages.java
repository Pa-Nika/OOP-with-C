package ru.nsu.panova.lab5.server.server.Command;

import ru.nsu.panova.lab5.server.server.CommandExecutor;
import ru.nsu.panova.lab5.server.server.Constants;

import java.util.List;

public class BufferMessages extends CommandGetterType {
    private List<Message> firstMessages;

    public BufferMessages() {

    }

    public void setBufferMessages(List<Message> messages) {
        setTypeCommand(Constants.COMMAND_FIRST_MESSAGES);
        firstMessages = messages;
    }

    public List<Message> getFirstMessages() {
        return firstMessages;
    }

    @Override
    public void runCommand(CommandExecutor commandExecutor, String json) {
        commandExecutor.sendFirstMessages();
    }
}

