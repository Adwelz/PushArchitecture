package com.example.chatbasicpullfx.Client;

import com.example.chatbasicpullfx.Server.Dialogue;
import com.example.chatbasicpullfx.Shared.Message;
import com.example.chatbasicpullfx.Shared.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public class model {

    private static model instance;
    Dialogue dialogue;
    private User currentUser;
    private User adressee;

    private model(){
        try {
            dialogue = (Dialogue) Naming.lookup("rmi://localhost:1099/Dialogue");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    void sendMessage(String msg) throws RemoteException {
        dialogue.sendMessage(currentUser,adressee,msg);
    }

    void disconnectUser() throws RemoteException {
        dialogue.disconnect(currentUser);
    }

    public boolean connectUser(String userName) {
        try{
            currentUser = new User(userName);
            if(dialogue.connect(currentUser)){
                return true;
            }

        }catch (RemoteException e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Message> getMessages() throws RemoteException {

        return adressee != null? dialogue.getMessages(currentUser, adressee): new ArrayList<Message>() ;
    }

    public ArrayList<User> getUserList(){
        ArrayList<User> users = new ArrayList<>();
        try{
            users = dialogue.getClients();
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
