package com.example.chatbasicpullfx.Server.CustomExceptions;

public class ConnectionException extends Exception{
    private final String exceptionMsg;

    public ConnectionException(String exceptionMsg) {
        super();
        this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }
}

