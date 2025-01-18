package com.example.dogbreeds.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DogBreed implements Serializable {


    @SerializedName("life_span")
    private String lifeSpan;
    @SerializedName("breed_group")
    private String breedGroup;
    @SerializedName("bred_for")
    private String breedFor;

    @SerializedName("name")
    private String name;
    @SerializedName("temperament")
    private String temperament;
    @SerializedName("url")
    private String image;
    public String getName() {
        return name;
    }
    public String getTemperament(){
        return temperament;
    }
    public String getImage() {
        return image;

    }
    public String getLifeSpan() {
        return lifeSpan;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public String getBreedFor() {
        return breedFor;
    }
}
