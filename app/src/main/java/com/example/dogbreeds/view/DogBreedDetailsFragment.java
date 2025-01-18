package com.example.dogbreeds.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.dogbreeds.presenter.DogBreedDetailPresenter;
import com.example.dogbreeds.R;
import com.example.dogbreeds.model.DogBreed;

public class DogBreedDetailsFragment extends Fragment implements DogBreedDetailView {
    private ImageView imageView;
    private TextView nameTextView, breedGroupTextView, temparmentTextView,lifeSpanTextView, breedForTextView;
    private DogBreedDetailPresenter presenter;


    // To receive the DogBreed object
    private static final String DOG_BREED_KEY = "dogBreed";

    public DogBreedDetailsFragment() {
        // Required empty public constructor
    }

    public static DogBreedDetailsFragment newInstance(DogBreed dogBreed) {
        DogBreedDetailsFragment fragment = new DogBreedDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(DOG_BREED_KEY, dogBreed);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dog_breed_details, container, false);

        imageView = view.findViewById(R.id.imageViewDog);
        nameTextView = view.findViewById(R.id.textViewName);
        breedGroupTextView = view.findViewById(R.id.textViewBreedGroup);
        temparmentTextView = view.findViewById(R.id.textViewTemperament);
        lifeSpanTextView = view.findViewById(R.id.textViewLifeSpan);
        breedForTextView = view.findViewById(R.id.textViewBreedFor);

        Toolbar toolbar = view.findViewById(R.id.dog_breed_details_toolbar);
        toolbar.setTitle(getString(R.string.breed_description_screen_title));
        toolbar.setNavigationOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack(); // Just navigate back
        });

        // Get the DogBreed object passed via arguments
        if (getArguments() != null) {
            DogBreed dogBreed = (DogBreed) getArguments().getSerializable(DOG_BREED_KEY);
            presenter = new DogBreedDetailPresenter(this);
            presenter.loadDogBreedDetails(dogBreed);
        }
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void displayDogBreedDetails(DogBreed dogBreed) {
        // Display the dog breed details
        nameTextView.setText(dogBreed.getName());
        breedGroupTextView.setText("Related to " + dogBreed.getBreedGroup() + " Breed");
        temparmentTextView.setText("Main characteristics are " + dogBreed.getTemperament());
        lifeSpanTextView.setText("Lives in the range of " + dogBreed.getLifeSpan());
        breedForTextView.setText("Best for " + dogBreed.getBreedFor());

        Glide.with(getContext()).load(dogBreed.getImage()).into(imageView);
    }
}
