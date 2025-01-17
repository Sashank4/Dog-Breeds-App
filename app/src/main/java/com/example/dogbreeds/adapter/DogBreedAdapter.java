package com.example.dogbreeds.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dogbreeds.R;
import com.example.dogbreeds.model.DogBreed;

import java.util.List;

public class DogBreedAdapter extends RecyclerView.Adapter<DogBreedAdapter.DogBreedViewHolder> {

    private final List<DogBreed> dogBreedList;
    private final OnItemClickListener onItemClickListener;

    // Constructor
    public DogBreedAdapter(List<DogBreed> dogBreedList, OnItemClickListener onItemClickListener) {
        this.dogBreedList = dogBreedList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public DogBreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_breed_card, parent, false);
        return new DogBreedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogBreedViewHolder holder, int position) {
        DogBreed dogBreed = dogBreedList.get(position);
        holder.bind(dogBreed);
    }

    @Override
    public int getItemCount() {
        return dogBreedList.size();
    }

    // ViewHolder class
    class DogBreedViewHolder extends RecyclerView.ViewHolder {
        private final ImageView dogImageView;
        private final TextView dogNameTextView;
        private final TextView dogDescriptionTextView;

        public DogBreedViewHolder(@NonNull View itemView) {
            super(itemView);
            dogImageView = itemView.findViewById(R.id.dogImageView);
            dogNameTextView = itemView.findViewById(R.id.dogNameTextView);
            dogDescriptionTextView = itemView.findViewById(R.id.dogDescriptionTextView);

            // Set click listener for the card
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(dogBreedList.get(position));
                }
            });
        }

        public void bind(DogBreed dogBreed) {
            dogNameTextView.setText(dogBreed.getName());
            dogDescriptionTextView.setText(dogBreed.getTemperament());
            Glide.with(dogImageView.getContext())
                    .load(dogBreed.getImage())
                    .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image
                    .into(dogImageView);
        }
    }

    // Interface for item click
    public interface OnItemClickListener {
        void onItemClick(DogBreed dogBreed);
    }
}
