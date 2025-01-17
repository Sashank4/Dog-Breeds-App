package com.example.dogbreeds.presenter;

import com.example.dogbreeds.model.DogBreed;
import com.example.dogbreeds.model.DogBreedModel;
import com.example.dogbreeds.view.DogBreedListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogViewListPresenter {
    private final DogBreedModel model;
    private final DogBreedListView view;

    public DogViewListPresenter(DogBreedModel model, DogBreedListView view) {
        this.model = model;
        this.view = view;
    }

    public void loadDogBreeds() {
        view.showLoading();
        model.fetchDogBreeds(new Callback<List<DogBreed>>() {
            @Override
            public void onResponse(Call<List<DogBreed>> call, Response<List<DogBreed>> response) {
                view.hideLoading();
                if (response.isSuccessful()) {
                    view.displayDogBreeds(response.body());
                } else {
                    view.showError("Failed to load data");
                }
            }

            @Override
            public void onFailure(Call<List<DogBreed>> call, Throwable t) {
                view.hideLoading();
                view.showError(t.getMessage());
            }
        });
    }
}

