package com.example.dogbreeds;

public class DogBreedDetailPresenter {
    private final DogBreedDetailView view;

    public DogBreedDetailPresenter(DogBreedDetailView view) {
        this.view = view;
    }

    public void loadDogBreedDetails(DogBreed dogBreed) {
        view.displayDogBreedDetails(dogBreed);
    }
}

