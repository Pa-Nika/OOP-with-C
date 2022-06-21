package ru.nsu.panova.lab5.server.server.Command;

public abstract class CommandGetterType implements CommandInterface {
    private String typeCommand;

    public String getTypeCommand() {
        return typeCommand;
    }

    public void setTypeCommand(String typeCommand) {
        this.typeCommand = typeCommand;
    }
}
