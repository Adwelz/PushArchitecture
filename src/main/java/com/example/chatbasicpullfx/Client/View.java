package com.example.chatbasicpullfx.Client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class View {

    private static View instance;

    //Constructeur
    private View(){

    }

    public void openUserInterface(Scene scene) throws IOException {
        URL fxmlLocation = getClass().getResource("Msg.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        scene.setRoot(fxmlLoader.load());
        try {
            userThread userT = new userThread(fxmlLoader.getController());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static View getInstance(){
        if (instance == null){
            instance = new View();
        }
        return instance;
    }
}
