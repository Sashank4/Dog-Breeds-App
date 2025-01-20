package com.example.dogbreeds.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogbreeds.adapter.DogBreedAdapter;
import com.example.dogbreeds.presenter.DogViewListPresenter;
import com.example.dogbreeds.R;
import com.example.dogbreeds.model.DogBreed;
import com.example.dogbreeds.model.DogBreedModel;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogBreedListFragment extends Fragment implements DogBreedListView {

    private RecyclerView recyclerView;
    private DogViewListPresenter presenter;
    private ProgressBar progressBar;
    private ConstraintLayout noInternetLayout;
    private Button tryAgainButton;
    private View overlayView;

    public DogBreedListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_dog_breed_list, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.dogBreedListRecyclerView);
        noInternetLayout = view.findViewById(R.id.noInternetLayout);
        tryAgainButton = view.findViewById(R.id.tryAgainButton);
        overlayView = view.findViewById(R.id.overlay_view); // Initialize overlay view

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Setup toolbar
        Toolbar toolbar = view.findViewById(R.id.show_dog_breeds_toolbar);
        toolbar.setTitle(getString(R.string.dog_breed_screen_title));

        // Setup Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/DevTides/DogsApi/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instantiate the model and presenter
        DogBreedModel model = new DogBreedModel(retrofit);
        presenter = new DogViewListPresenter(model, this);

        // Try again CTA listener
        tryAgainButton.setOnClickListener(v -> {
            noInternetLayout.setVisibility(View.GONE);
            presenter.loadDogBreeds(); // Retry API call
            recyclerView.setVisibility(View.VISIBLE);
        });

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
        noInternetLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    // On dog breed click, navigate to the details screen
    public void onDogBreedClick(DogBreed dogBreed) {
        // Show the overlay view to block interactions
//        if (overlayView != null) {
//            overlayView.setVisibility(View.VISIBLE);
//            overlayView.setOnTouchListener((v, event) -> true); // Intercept all touch events
//        }

        // Add the details fragment
        DogBreedDetailsFragment detailsFragment = DogBreedDetailsFragment.newInstance(dogBreed);
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, detailsFragment)
                .addToBackStack("DogBreedListFragment") // So the user can go back
                .commit();
    }
}
