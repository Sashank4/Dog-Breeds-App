package com.example.dogbreeds.model;

import com.example.dogbreeds.presenter.DogBreedResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DogBreedRepository {
    private final DogBreedAPI api;

    public DogBreedRepository(Retrofit retrofit) {
        this.api = retrofit.create(DogBreedAPI.class);
    }

    public Single<List<DogBreed>> fetchDogBreeds(){
        return api.getDogBreeds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
