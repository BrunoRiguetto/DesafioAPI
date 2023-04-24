package com.gft.veterinariagft.exceptions;

public class DogAPIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DogAPIException(String message) {
        super(message);
    }
}
