package com.example.chatbasicpullfx.Server;

import com.example.chatbasicpullfx.Shared.Message;
import com.example.chatbasicpullfx.Shared.User;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class ReceiverImpl extends UnicastRemoteObject implements Receiver{

    private User user;
    HashMap<String, ArrayList<Message>> bDDMessageReceiver = new HashMap<>();
    private ArrayList<User> clients;

    public ReceiverImpl(User user) throws RemoteException {
        this.user=user;
    }

    @Override
    public ArrayList<Message> getMessages(User from) throws RemoteException {
        String key = from.getName();
        return bDDMessageReceiver.containsKey(key) ? bDDMessageReceiver.get(key) : new ArrayList<Message>();
    }

    @Override
    public ArrayList<User> getClients() throws RemoteException{
        return clients;
    }

    @Override
    public void receive(User from, String message) throws RemoteException, MalformedURLException {
        String key = from.getName();
        Message msg = new Message(user.getName(), from.getName() , message);
        if(bDDMessageReceiver.containsKey(key)){
            bDDMessageReceiver.get(key).add(msg);
        }else{
            ArrayList<Message> arrayMsg = new ArrayList<Message>();
            arrayMsg.add(msg);
            bDDMessageReceiver.put(key, arrayMsg);
        }
    }

    @Override
    public void initClients(ArrayList<User> users) throws RemoteException {
        this.clients=users;
    }

    @Override
    public void addClient(User user) throws RemoteException{
        this.clients.add(user);
    }

    @Override
    public void remClient(User user) throws RemoteException {
        this.clients.remove(user);
    }
}
