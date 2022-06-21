module ru.nsu.panova.lab5.server.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens ru.nsu.panova.lab5.server.server to javafx.fxml;
    exports ru.nsu.panova.lab5.server.server;

    opens ru.nsu.panova.lab5.server.server.Command to com.google.gson;

}