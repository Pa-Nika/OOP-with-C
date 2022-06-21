package ru.nsu.panova.lab5.server.server.Command;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.server.server.CommandExecutor;
import ru.nsu.panova.lab5.server.server.Constants;

public class Logout extends CommandGetterType {
    private String userName;

    public void setLogout(String name) {
        setTypeCommand(Constants.COMMAND_USER_LOGOUT);
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void runCommand(CommandExecutor commandExecutor, String json) {
        Gson gson = new Gson();
        commandExecutor.clientLogout(gson.fromJson(json, Logout.class));
    }
}
