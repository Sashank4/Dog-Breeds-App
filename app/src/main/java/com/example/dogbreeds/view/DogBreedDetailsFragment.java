package com.example.dogbreeds.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.dogbreeds.presenter.DogBreedDetailPresenter;
import com.example.dogbreeds.R;
import com.example.dogbreeds.model.DogBreed;

public class DogBreedDetailsFragment extends Fragment implements DogBreedDetailView {
    private ImageView imageView;
    private TextView nameTextView, descriptionTextView;
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
        descriptionTextView = view.findViewById(R.id.textViewBreedGroup);

        // Get the DogBreed object passed via arguments
        if (getArguments() != null) {
            DogBreed dogBreed = (DogBreed) getArguments().getSerializable(DOG_BREED_KEY);
            presenter = new DogBreedDetailPresenter(this);
            presenter.loadDogBreedDetails(dogBreed);
        }

        return view;
    }

    @Override
    public void displayDogBreedDetails(DogBreed dogBreed) {
        // Display the dog breed details
        nameTextView.setText(dogBreed.getName());
        descriptionTextView.setText(dogBreed.getTemperament());
        Glide.with(getContext()).load(dogBreed.getImage()).into(imageView);
    }
}
