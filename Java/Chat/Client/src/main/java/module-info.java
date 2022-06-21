module ru.nsu.panova.lab5.client.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;
    opens ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer to com.google.gson;

    opens ru.nsu.panova.lab5.client.client to javafx.fxml;
    exports ru.nsu.panova.lab5.client.client;

    opens ru.nsu.panova.lab5.client.client.mainWindow to javafx.fxml;
    exports ru.nsu.panova.lab5.client.client.mainWindow;

    opens ru.nsu.panova.lab5.client.client.entranceWindow to javafx.fxml;
    exports ru.nsu.panova.lab5.client.client.entranceWindow;

    opens ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.Command to com.google.gson;
//    opens ru.nsu.panova.lab5.client.client to javafx.fxml;
//    exports ru.nsu.panova.lab5.client.client;
}