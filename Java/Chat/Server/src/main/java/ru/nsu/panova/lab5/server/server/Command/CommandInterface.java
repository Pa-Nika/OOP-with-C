package ru.nsu.panova.lab5.server.server.Command;


import ru.nsu.panova.lab5.server.server.CommandExecutor;

public interface CommandInterface {
    void runCommand(CommandExecutor commandExecutor, String json);
}
