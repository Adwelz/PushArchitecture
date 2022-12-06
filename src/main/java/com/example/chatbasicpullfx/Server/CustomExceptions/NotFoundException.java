package com.example.chatbasicpullfx.Server.CustomExceptions;

public class NotFoundException extends Exception{

    private final String exceptionMsg;

    public NotFoundException(String exceptionMsg) {
        super();
        this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg(){
        return exceptionMsg;
    }
}
