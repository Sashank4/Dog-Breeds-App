package com.example.dogbreeds.presenter;

import com.example.dogbreeds.model.DogBreed;
import com.example.dogbreeds.view.DogBreedDetailView;

public class DogBreedDetailPresenter {
    private final DogBreedDetailView view;

    public DogBreedDetailPresenter(DogBreedDetailView view) {
        this.view = view;
    }

    public void loadDogBreedDetails(DogBreed dogBreed) {
        view.displayDogBreedDetails(dogBreed);
    }
}

