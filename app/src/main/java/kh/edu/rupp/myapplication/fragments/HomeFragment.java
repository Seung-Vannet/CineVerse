package kh.edu.rupp.myapplication.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import kh.edu.rupp.myapplication.MovieDetailsActivity;
import kh.edu.rupp.myapplication.SearchActivity;
import kh.edu.rupp.myapplication.databinding.FragmentHomeBinding;
import kh.edu.rupp.myapplication.models.Movie;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupInteractions();
        setupScrollEffects();
    }

    private void setupInteractions() {
        // Hero Section Click
        binding.heroSection.setOnClickListener(v -> {
            Movie dune = new Movie("Dune Part Two", "2024", "2h 46m", "8.9", "PG-13", 
                "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.", 0);
            openMovieDetails(dune);
        });
        
        // Buttons
        binding.btnPlayNow.setOnClickListener(v -> Toast.makeText(getContext(), "Starting Movie...", Toast.LENGTH_SHORT).show());
        binding.btnMyList.setOnClickListener(v -> Toast.makeText(getContext(), "Added to Watchlist", Toast.LENGTH_SHORT).show());
        
        // Header Icons
        binding.notificationContainer.setOnClickListener(v -> Toast.makeText(getContext(), "Notifications Clicked", Toast.LENGTH_SHORT).show());
        binding.ivCast.setOnClickListener(v -> Toast.makeText(getContext(), "Casting...", Toast.LENGTH_SHORT).show());
        binding.profileImage.setOnClickListener(v -> Toast.makeText(getContext(), "Opening Profile...", Toast.LENGTH_SHORT).show());
        
        // Search & Filter
        binding.searchBarCard.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });
        binding.ivFilter.setOnClickListener(v -> Toast.makeText(getContext(), "Opening Filters...", Toast.LENGTH_SHORT).show());

        // Individual Movie Card Clicks
        binding.cardOppenheimer.setOnClickListener(v -> openMovieDetails(new Movie("Oppenheimer", "2023", "3h 0m", "8.4", "R", "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.", 0)));
        binding.cardThebatman.setOnClickListener(v -> openMovieDetails(new Movie("The Batman", "2022", "2h 56m", "8.1", "PG-13", "Batman ventures into Gotham City's underworld when a sadistic killer leaves behind a trail of cryptic clues.", 0)));
        binding.cardAvatartwo.setOnClickListener(v -> openMovieDetails(new Movie("Avatar 2", "2022", "3h 12m", "7.6", "PG-13", "Jake Sully lives with his newfound family formed on the extrasolar moon Pandora.", 0)));
        binding.cardSpiderman.setOnClickListener(v -> openMovieDetails(new Movie("Spider-Man", "2023", "2h 20m", "8.2", "PG-13", "Miles Morales catapults across the Multiverse, where he encounters a team of Spider-People.", 0)));
        binding.cardJohnwickfour.setOnClickListener(v -> openMovieDetails(new Movie("John Wick 4", "2023", "2h 49m", "8.1", "R", "John Wick uncovers a path to defeating The High Table.", 0)));
        binding.cardInterstellar.setOnClickListener(v -> openMovieDetails(new Movie("Interstellar", "2014", "2h 49m", "8.7", "PG-13", "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.", 0)));
    }

    private void setupScrollEffects() {
        // Dynamic Header Transparency and Hero Fade
        binding.homeScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            // Header alpha transitions from 0 to 1 over 300dp scroll
            float headerAlpha = Math.min(1f, (float) scrollY / 300);
            binding.topHeader.setBackgroundColor(Color.argb((int) (headerAlpha * 255), 0, 0, 0));
            
            // Hero section fade: fades out completely by 500dp scroll
            float heroAlpha = Math.max(0f, 1f - (float) scrollY / 500);
            binding.heroSection.setAlpha(heroAlpha);
            
            // Subtle parallax for hero section
            binding.heroSection.setTranslationY(scrollY * 0.4f);
        });
    }

    private void openMovieDetails(Movie movie) {
        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
