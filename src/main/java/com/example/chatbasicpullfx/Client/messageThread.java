package com.example.chatbasicpullfx.Client;

import com.example.chatbasicpullfx.Shared.Message;
import com.example.chatbasicpullfx.Shared.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class messageThread implements Runnable{
    private Thread t;
    private ControllerMsg control;
    public messageThread(ControllerMsg control) throws IOException {
        this.control = control;
        this.t = new Thread(this,"userThread");
        this.t.start();
    }
    @Override
    public synchronized void run() {
        while(true){
            try {
                this.wait(2000);
                control.refreshMessage();
            } catch (InterruptedException | RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
