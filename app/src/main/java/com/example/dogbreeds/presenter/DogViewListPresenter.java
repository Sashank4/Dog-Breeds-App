package com.example.dogbreeds.presenter;

import android.annotation.SuppressLint;

import com.example.dogbreeds.model.DogBreed;
import com.example.dogbreeds.model.DogBreedRepository;
import com.example.dogbreeds.view.DogBreedListView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogViewListPresenter {
    private final DogBreedRepository model;
    private final DogBreedListView view;

    public DogViewListPresenter(DogBreedRepository model, DogBreedListView view) {
        this.model = model;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    public void loadDogBreeds() {
        view.showLoading();
        model.fetchDogBreeds()
                .subscribe(
                        dogBreeds -> {
                            view.hideLoading();
                            view.displayDogBreeds(dogBreeds);
                        },
                        throwable -> {
                            view.hideLoading();
                            view.showError(throwable.getMessage());
                        }
                );

    }
}

