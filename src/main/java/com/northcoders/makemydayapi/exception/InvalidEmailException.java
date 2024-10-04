package com.northcoders.makemydayapi.exception;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException (String message){
        super(message);
    }
}
