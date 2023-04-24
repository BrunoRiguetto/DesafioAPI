package com.gft.veterinariagft.DTOs.vote;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VoteReponseGet implements Serializable {
    private Integer value;
    private String image_id;
    private String sub_id;
    private String created_at;
    private String id;
    private String country_code;

    public VoteReponseGet() {
    }

    public VoteReponseGet(Integer value, String image_id, String sub_id, String created_at, String id,
            String country_code) {
        this.value = value;
        this.image_id = image_id;
        this.sub_id = sub_id;
        this.created_at = created_at;
        this.id = id;
        this.country_code = country_code;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
}
