package com.example.dogbreeds.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface DogBreedAPI {
    @GET("dogs.json")
    Single<List<DogBreed>> getDogBreeds();
}
