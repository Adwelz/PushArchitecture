package com.example.chatbasicpullfx.Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;

public class ChatApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL fxmlLocation = getClass().getResource("connection.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Chat pull basic");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws RemoteException {
        model.getInstance().disconnectUser();
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}