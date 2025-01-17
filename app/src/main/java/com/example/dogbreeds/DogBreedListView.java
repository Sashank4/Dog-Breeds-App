package com.example.dogbreeds;

import java.util.List;

public interface DogBreedListView {
    void showLoading();
    void hideLoading();
    void displayDogBreeds(List<DogBreed> dogBreeds);
    void showError(String message);
}
