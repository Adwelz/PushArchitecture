package com.example.chatbasicpullfx.Client;

import com.example.chatbasicpullfx.Server.*;
import com.example.chatbasicpullfx.Shared.Message;
import com.example.chatbasicpullfx.Shared.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class model {

    private static model instance;
    Connection connection;
    Receiver rcv;
    Emitter emitter;
    private User currentUser;
    private User adressee;

    private model(){
        try {
            ReceiverImpl ReceiverComponent = new ReceiverImpl(currentUser);

            //publication of component reference in the registry
            Naming.rebind("Rcv"+currentUser.getName(), ReceiverComponent);
            rcv = (Receiver) Naming.lookup("rmi://localhost:1099/Rcv"+currentUser.getName());
            connection = (Connection) Naming.lookup("rmi://localhost:1099/Connection");
            //dialogue = (Dialogue) Naming.lookup("rmi://localhost:1099/Dialoguetoto");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    void sendMessage(String msg) throws RemoteException, MalformedURLException {
        emitter.sendMessage(adressee,msg);
    }

    void disconnectUser() throws RemoteException {
        connection.disconnect(currentUser);
    }

    public boolean connectUser(String userName) throws RemoteException {
        currentUser = new User(userName);
        connection.connect(currentUser,rcv);
        return true;
    }

    public ArrayList<Message> getMessages() throws RemoteException {

        return adressee != null? rcv.getMessages(adressee): new ArrayList<Message>() ;
    }

    public ArrayList<User> getUserList(){
        ArrayList<User> users = new ArrayList<>();
        try{
            users = rcv.getClients();
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public void setAdressee(User adressee){
        this.adressee = adressee;
    }
    public User getAdressee(){
        return adressee;
    }

    public static model getInstance(){
        if(instance == null){
            instance = new model();
        }
        return instance;
    }
}
