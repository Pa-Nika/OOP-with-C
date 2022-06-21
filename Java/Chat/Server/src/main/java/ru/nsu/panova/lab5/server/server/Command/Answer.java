package ru.nsu.panova.lab5.server.server.Command;

import ru.nsu.panova.lab5.server.server.CommandExecutor;
import ru.nsu.panova.lab5.server.server.Constants;



public class Answer extends CommandGetterType {
    private boolean error = false;
    private String errorMassage = null;

    public String getErrorMassage() {
        return errorMassage;
    }

    public void setErrorMassage(String errorMassage) {
        this.errorMassage = errorMassage;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        setTypeCommand(Constants.COMMAND_ANSWER);
        this.error = error;
    }

    @Override
    public void runCommand(CommandExecutor commandExecutor, String json) {

    }
}