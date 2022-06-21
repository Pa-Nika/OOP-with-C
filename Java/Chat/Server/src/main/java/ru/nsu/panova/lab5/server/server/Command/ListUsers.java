package ru.nsu.panova.lab5.server.server.Command;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.server.server.CommandExecutor;
import ru.nsu.panova.lab5.server.server.Constants;

import java.util.List;

public class ListUsers extends CommandGetterType {
    private List<String> nameUsers;

    public ListUsers() {

    }

    public void setListUsers(List<String> name) {
        setTypeCommand(Constants.COMMAND_LIST_USERS);
        nameUsers = name;
    }

    public List<String> getNameUsers() {
        return nameUsers;
    }

    @Override
    public void runCommand(CommandExecutor commandExecutor, String json) {
        Gson gson = new Gson();
        commandExecutor.sendListUsers();
    }
}
