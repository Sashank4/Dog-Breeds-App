package com.example.dogbreeds.presenter;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class DogBreedResponse {
    @SerializedName("message")
    private Map<String, String[]> breeds;

    public Map<String, String[]> getBreeds() {
        return breeds;
    }
}
