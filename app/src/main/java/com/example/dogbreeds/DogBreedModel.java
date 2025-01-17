package com.example.dogbreeds;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class DogBreedModel {
    private final DogBreedAPI api;

    public DogBreedModel(Retrofit retrofit) {
        this.api = retrofit.create(DogBreedAPI.class);
    }

    public void fetchDogBreeds(Callback<List<DogBreed>> callback) {
        api.getDogBreeds().enqueue(callback);
    }
}
