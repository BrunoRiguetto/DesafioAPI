package com.gft.veterinariagft.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataIntegrityViolentionException extends RuntimeException{

    public DataIntegrityViolentionException(String message) {
        super(message);
    }

    public DataIntegrityViolentionException(String message, Throwable cause) {
        super(message, cause);
    }
    
}