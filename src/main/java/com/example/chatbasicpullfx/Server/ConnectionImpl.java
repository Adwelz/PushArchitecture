package com.example.chatbasicpullfx.Server;

import com.example.chatbasicpullfx.Server.CustomExceptions.ConnectionException;
import com.example.chatbasicpullfx.Shared.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionImpl extends UnicastRemoteObject implements Connection{

    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    protected ConnectionImpl() throws RemoteException {

    }

    @Override
    public Emitter connect(User user, Receiver rcv) throws RemoteException {
        AtomicBoolean state = new AtomicBoolean(false);
        EmitterImpl emitter = new EmitterImpl(user,rcv);
        try{
            if(users.stream().allMatch(u -> u.getName() != user.getName())){
                users.add(user);
                state.set(true);
                System.out.println(emitter.getRef().remoteToString());
                try {
                    Naming.rebind("Emitter"+user.getName(), emitter);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                rcv.initClients(users);
                System.out.println("User ("+ user.getName() +") connection successful");
            }else{
                throw new ConnectionException("Erreur de connexion");
            }
        }catch(ConnectionException e){
            System.out.println(e.getExceptionMsg());
            e.printStackTrace();
        }
        return emitter;
    }

    @Override
    public void disconnect(User user) throws RemoteException {
        ((ArrayList<User>)users.clone()).forEach(u -> {
            if(Objects.equals(u.getName(), user.getName())){
                System.out.println("User ("+ user.getName() +") disconnection successful");
                users.remove(u);
            }
        });
    }
}
