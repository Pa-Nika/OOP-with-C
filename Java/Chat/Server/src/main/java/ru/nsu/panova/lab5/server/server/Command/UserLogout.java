package ru.nsu.panova.lab5.server.server.Command;


import com.google.gson.Gson;
import ru.nsu.panova.lab5.server.server.CommandExecutor;
import ru.nsu.panova.lab5.server.server.Constants;

public class UserLogout extends CommandGetterType {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserLogout(String userName) {
        setTypeCommand(Constants.COMMAND_USER_LOGOUT);
        this.userName = userName;
    }

    @Override
    public void runCommand(CommandExecutor commandExecutor, String json) {
        Gson gson = new Gson();
        commandExecutor.otherClientDisconnect(gson.fromJson(json, UserLogout.class));
    }
}
