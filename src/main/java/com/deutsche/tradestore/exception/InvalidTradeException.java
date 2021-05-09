package com.deutsche.tradestore.exception;

/*
* Exception to indicate a trade is invalid.
* */
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

