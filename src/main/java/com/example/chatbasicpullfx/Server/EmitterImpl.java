package com.example.chatbasicpullfx.Server;

import com.example.chatbasicpullfx.Shared.Message;
import com.example.chatbasicpullfx.Shared.User;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class EmitterImpl extends UnicastRemoteObject implements Emitter
{
    private User user;
    HashMap<String, ArrayList<Message>> bDDMessageEmitter = new HashMap<>();
    Receiver rcv;


    protected EmitterImpl(User user,Receiver rcv) throws RemoteException {
        this.user=user;
        this.rcv=rcv;
    }

    @Override
    public void sendMessage(User to, String message) throws RemoteException, MalformedURLException {
        String key = to.getName();
        Message msg = new Message(user.getName(), to.getName() , message);
        if(bDDMessageEmitter.containsKey(key)){
            bDDMessageEmitter.get(key).add(msg);
        }else{
            ArrayList<Message> arrayMsg = new ArrayList<Message>();
            arrayMsg.add(msg);
            bDDMessageEmitter.put(key, arrayMsg);
        }
        rcv.receive(to, message);
    }
}
