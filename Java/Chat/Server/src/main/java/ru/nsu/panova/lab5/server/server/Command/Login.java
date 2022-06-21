package ru.nsu.panova.lab5.server.server.Command;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.server.server.CommandExecutor;
import ru.nsu.panova.lab5.server.server.Constants;

public class Login extends CommandGetterType {
    private String userName;

    public void setLogin(String userName) {
        setTypeCommand(Constants.COMMAND_LOGIN);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void runCommand(CommandExecutor commandExecutor, String json) {
        Gson gson = new Gson();
        commandExecutor.clientConnect(gson.fromJson(json, Login.class));
    }
}
