package com.example.chatbasicpullfx.Client;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerConnection {

    @FXML
    private TextField userNameField;

    @FXML
    protected void connectUser() throws IOException {
        String userName = userNameField.getText();
        Scene scene = userNameField.getScene();
        Stage window = (Stage) scene.getWindow();
        window.setTitle("Connected with " + userName);


        boolean connectionSuccessful = model.getInstance().connectUser(userName);
        System.out.println(connectionSuccessful);
        if(connectionSuccessful){
            View.getInstance().openUserInterface(scene);
        }
    }
}