package com.example.chatbasicpullfx.Server;

import com.example.chatbasicpullfx.Shared.User;

import java.rmi.RemoteException;

public interface Connection {
    Emitter connect(User user, Receiver rcv) throws RemoteException;
    void disconnect(User user) throws RemoteException;
}
