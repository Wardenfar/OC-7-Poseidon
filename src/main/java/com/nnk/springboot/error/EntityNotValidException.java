package com.nnk.springboot.error;

public class EntityNotValidException extends Exception {

    public EntityNotValidException(String message) {
        super(message);
    }
}
