package com.example.chatbasicpullfx.Server;

import com.example.chatbasicpullfx.Shared.Message;
import com.example.chatbasicpullfx.Shared.User;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Receiver extends Remote {
    void receive(User from, String msg) throws RemoteException, MalformedURLException;
    void initClients(ArrayList<User> users) throws RemoteException;
    void addClient(User user) throws RemoteException;
    void remClient(User user) throws RemoteException;
    ArrayList<Message> getMessages(User from) throws RemoteException;

    ArrayList<User> getClients() throws RemoteException;
}
