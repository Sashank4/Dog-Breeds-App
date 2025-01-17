package com.example.dogbreeds.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogBreedAPI {
    @GET("dogs.json")
    Call<List<DogBreed>> getDogBreeds();
}
