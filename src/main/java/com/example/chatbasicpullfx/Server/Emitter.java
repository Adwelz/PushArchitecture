package com.example.chatbasicpullfx.Server;

import com.example.chatbasicpullfx.Shared.User;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Emitter extends Remote {
    void sendMessage(User to, String message) throws RemoteException, MalformedURLException;
}
