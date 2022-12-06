package com.example.chatbasicpullfx.Client;

import com.example.chatbasicpullfx.Shared.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class userThread implements Runnable{
    private Thread t;

    private ControllerMsg control;
    public userThread(ControllerMsg control) throws IOException {
        this.control = control;
        t = new Thread(this, "userThread");
        this.t.start();
    }

    @Override
    public synchronized void run() {
        while(true){
            try {
                this.wait(2000);
                control.refreshUser();
                control.refreshMessage();
            } catch (InterruptedException | RemoteException  e) {
                throw new RuntimeException(e);
            }
        }
    }
}
