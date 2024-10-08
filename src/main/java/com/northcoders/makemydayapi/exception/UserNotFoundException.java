package com.northcoders.makemydayapi.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found with this id: " + id);
    }
}
