package com.example.dogbreeds.view;

import com.example.dogbreeds.model.DogBreed;

import java.util.List;

public interface DogBreedListView {
    void showLoading();
    void hideLoading();
    void displayDogBreeds(List<DogBreed> dogBreeds);
    void showError(String message);
}
