package com.example.chatbasicpullfx.Client;

import com.example.chatbasicpullfx.Shared.Message;
import com.example.chatbasicpullfx.Shared.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerMsg implements Initializable {

    private messageThread messageT;
    private userThread userT;
    @FXML private VBox messageArea = new VBox();
    @FXML private TableColumn<User,String> usersColumn = new TableColumn<User,String>();
    @FXML private TableView<User> tvContacts = new TableView<User>();
    @FXML private TextField tfMsg = new TextField();
    @FXML
    protected void sendMessage() throws RemoteException, MalformedURLException {
        model.getInstance().sendMessage(tfMsg.getText());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usersColumn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        usersColumn.setCellFactory(tc -> {
            TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String userName = cell.getItem();
                    model.getInstance().setAdressee(new User(userName));
                }
            });
            return cell ;
        });
    }

    @FXML
    public void refreshUser(){
        ObservableList<User> usersOL = FXCollections.observableArrayList();
        ArrayList<User> users = model.getInstance().getUserList();
        users.forEach(user -> usersOL.add(user));
        tvContacts.setItems(usersOL);
    }

    public void refreshMessage() throws RemoteException {
        messageArea.getChildren().clear();
        ArrayList<Message> Messages = model.getInstance().getMessages();
        Messages.forEach(msg ->{
            messageArea.getChildren().add(new Label(msg.getFrom() + " : " + msg.getMsg()));
        });
    }
}
