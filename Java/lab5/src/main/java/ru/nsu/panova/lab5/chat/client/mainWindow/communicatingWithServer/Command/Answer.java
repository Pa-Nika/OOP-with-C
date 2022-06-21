package ru.nsu.panova.lab5.chat.client.mainWindow.communicatingWithServer.Command;

import com.google.gson.Gson;
import ru.nsu.panova.lab5.chat.client.mainWindow.ModelMainWindow;

public class Answer extends CommandGetterType {
    private boolean error = false;
    private String errorMassage = null;

    public String getErrorMassage() {
        return errorMassage;
    }

    public void setErrorMassage(String errorMassage) {
        this.errorMassage = errorMassage;
    }

    @Override
    public void runCommand(ModelMainWindow modelMainWindow, String json) {
        Gson gson = new Gson();
        modelMainWindow.readAnswer(gson.fromJson(json, Answer.class));
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
