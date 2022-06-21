package ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.Command;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.client.client.mainWindow.ModelMainWindow;

public class UserLogin extends CommandGetterType {
    private String userName;

    @Override
    public void runCommand(ModelMainWindow modelMainWindow, String json) {
        Gson gson = new Gson();
        modelMainWindow.addNewMemberToChat(gson.fromJson(json, UserLogin.class));
    }

    public String getUserName() {
        return userName;
    }


}
