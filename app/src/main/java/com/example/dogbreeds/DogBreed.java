package com.example.dogbreeds;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DogBreed implements Serializable {


    private int id;
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
    public int getId() {
        return id;
    }
    public String getImage() {
        return image;
    }
}
