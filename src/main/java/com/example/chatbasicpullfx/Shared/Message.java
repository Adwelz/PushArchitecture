package com.example.chatbasicpullfx.Shared;

import java.io.Serializable;

public class Message implements Serializable {
    private final String from;
    private final String to;
    private final String msg;

    public Message(String from, String to, String msg) {
        this.from = from;
        this.to = to;
        this.msg = msg;
    }

    public String getFrom() { return from; }

    public String getTo() {
        return to;
    }

    public String getMsg() {
        return msg;
    }
}