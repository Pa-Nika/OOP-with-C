package ru.nsu.panova.lab5.client.client;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.nsu.panova.lab5.client.client.entranceWindow.LoaderEntranceWindow;
import ru.nsu.panova.lab5.client.client.loaders.InterfaceLoaders;

public class Client extends Application {
    private static Stage stage;

    @Override
    public void start(Stage mainStage) {
        stage = mainStage;

        setNewLoader(new LoaderEntranceWindow());
    }


    public static void setNewLoader(InterfaceLoaders interfaceLoaders) {
        try {
            interfaceLoaders.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Application.launch();
    }
}

//TODO Сделать буфер и корректное отсоединение пользователя