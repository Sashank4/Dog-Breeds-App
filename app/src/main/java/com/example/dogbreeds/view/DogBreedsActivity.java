package com.example.dogbreeds.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.dogbreeds.R;

public class DogBreedsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_breeds);

        // If the fragment is not already in the layout, add it
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            DogBreedListFragment listFragment = new DogBreedListFragment();
            transaction.replace(R.id.fragment_container, listFragment);
            transaction.commit();
        }
    }
}
