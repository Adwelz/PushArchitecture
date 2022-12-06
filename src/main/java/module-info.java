module com.example.chatbasicpullfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.desktop;

    exports com.example.chatbasicpullfx.Client;
    exports com.example.chatbasicpullfx.Server;
    exports com.example.chatbasicpullfx.Shared;
    opens com.example.chatbasicpullfx.Client to javafx.fxml;
}