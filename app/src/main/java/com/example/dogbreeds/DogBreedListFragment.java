package com.example.dogbreeds;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogBreedListFragment extends Fragment implements DogBreedListView {

    private RecyclerView recyclerView;
    private DogViewListPresenter presenter;
    private ProgressBar progressBar;

    public DogBreedListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_dog_breed_list, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Setup Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/DevTides/DogsApi/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instantiate the model and presenter
        DogBreedModel model = new DogBreedModel(retrofit);
        presenter = new DogViewListPresenter(model, this);

        // Load data
        presenter.loadDogBreeds();

        return view;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayDogBreeds(List<DogBreed> dogBreeds) {
        // Set the RecyclerView adapter to display the dog breeds
        recyclerView.setAdapter(new DogBreedAdapter(dogBreeds, this::onDogBreedClick));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    // On dog breed click, navigate to the details screen
    private void onDogBreedClick(DogBreed dogBreed) {
        // Replace the fragment dynamically with DogBreedDetailsFragment
        DogBreedDetailsFragment detailsFragment = DogBreedDetailsFragment.newInstance(dogBreed);

        // Begin a fragment transaction to switch to the details fragment
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack(null) // So the user can go back
                .commit();
    }
}
