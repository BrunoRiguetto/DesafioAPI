package com.gft.veterinariagft.domain.dogapi;

import java.io.Serializable;

public class Breed implements Serializable{
    public Weight weight;
    public Height height;
    public int id;
    public String name;
    public String bred_for;
    public String breed_group;
    public String life_span;
    public String temperament;
    public String origin;
    public String reference_image_id;
    public Image image;
    public String country_code;
    public String description;
    public String history;

    public Breed() {}

    public Breed(Weight weight, Height height, int id, String name, String bred_for, String breed_group,
            String life_span, String temperament, String origin, String reference_image_id, Image image, String country_code, String description, String history) {
        this.weight = weight;
        this.height = height;
        this.id = id;
        this.name = name;
        this.bred_for = bred_for;
        this.breed_group = breed_group;
        this.life_span = life_span;
        this.temperament = temperament;
        this.origin = origin;
        this.reference_image_id = reference_image_id;
        this.image = image;
        this.country_code = country_code;
        this.description = description;
        this.history = history;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBred_for() {
        return bred_for;
    }

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }

    public String getBreed_group() {
        return breed_group;
    }

    public void setBreed_group(String breed_group) {
        this.breed_group = breed_group;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getReference_image_id() {
        return reference_image_id;
    }

    public void setReference_image_id(String reference_image_id) {
        this.reference_image_id = reference_image_id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}