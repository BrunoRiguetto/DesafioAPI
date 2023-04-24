package com.gft.veterinariagft.DTOs.vote;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RegistroVoteDTO {

    @NotNull
    @Min(0)
    @Max(1)
    private Integer value;

    private String image_id;

    public RegistroVoteDTO(Integer value, String image_id) {
        this.value = value;
        this.image_id = image_id;
    }

    public RegistroVoteDTO() {
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
}