package br.com.kitchenservice.app.exceptions;

public class InvalidParamException extends RuntimeException{

    public InvalidParamException(String message){
        super(message);
    }
}

