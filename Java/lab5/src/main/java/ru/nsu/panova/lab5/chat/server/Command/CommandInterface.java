package ru.nsu.panova.lab5.chat.server.Command;


import ru.nsu.panova.lab5.chat.server.CommandExecutor;

public interface CommandInterface {
    void runCommand(CommandExecutor commandExecutor, String json);
}
