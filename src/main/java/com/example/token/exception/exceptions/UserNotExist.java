package com.example.token.exception.exceptions;

public class UserNotExist extends RuntimeException {

    public UserNotExist(String email) {

        super("User " + email + " not exist");
    }
}
