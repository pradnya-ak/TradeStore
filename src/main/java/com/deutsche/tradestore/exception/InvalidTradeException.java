package com.deutsche.tradestore.exception;

public class InvalidTradeException extends Exception{

    private String message;

    public InvalidTradeException(){}

    public InvalidTradeException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String toString(){
        return ("Exception Message : "+message) ;
    }
}

