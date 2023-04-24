package com.gft.veterinariagft.DTOs.vote;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VoteSuccess implements Serializable {
    private String message;
    private String id;

    public VoteSuccess() {
    }

    public VoteSuccess(String message, String id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}