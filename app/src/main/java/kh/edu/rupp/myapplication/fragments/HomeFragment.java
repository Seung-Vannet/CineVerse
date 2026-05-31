package kh.edu.rupp.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import kh.edu.rupp.myapplication.adapters.MovieAdapter;
import kh.edu.rupp.myapplication.databinding.FragmentHomeBinding;
import kh.edu.rupp.myapplication.models.Movie;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MovieAdapter.OnMovieClickListener {

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
        setupContinueWatching();
        setupTrending();
    }

    private void setupInteractions() {
        // Hero Banner Click
        binding.heroBanner.setOnClickListener(v -> showDetails("Dune Part Two"));
        
        // Buttons
        binding.btnPlayNow.setOnClickListener(v -> Toast.makeText(getContext(), "Starting Movie...", Toast.LENGTH_SHORT).show());
        binding.btnMyList.setOnClickListener(v -> Toast.makeText(getContext(), "Added to My List", Toast.LENGTH_SHORT).show());
        
        // Header Icons
        binding.notificationContainer.setOnClickListener(v -> Toast.makeText(getContext(), "Notifications Clicked", Toast.LENGTH_SHORT).show());
        binding.ivCast.setOnClickListener(v -> Toast.makeText(getContext(), "Casting...", Toast.LENGTH_SHORT).show());
        
        // Search & Filter
        binding.searchBarCard.setOnClickListener(v -> Toast.makeText(getContext(), "Opening Search...", Toast.LENGTH_SHORT).show());
        binding.ivFilter.setOnClickListener(v -> Toast.makeText(getContext(), "Opening Filters...", Toast.LENGTH_SHORT).show());
    }

    private void setupContinueWatching() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Oppenheimer", "2023", "3h 0m", 0));
        movies.add(new Movie("The Batman", "2022", "2h 56m", 0));
        movies.add(new Movie("Avatar 2", "2022", "3h 12m", 0));

        MovieAdapter adapter = new MovieAdapter(movies, true, this);
        binding.rvContinueWatching.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvContinueWatching.setAdapter(adapter);
    }

    private void setupTrending() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Spider-Man", "2023", "2h 20m", 0));
        movies.add(new Movie("John Wick 4", "2023", "2h 49m", 0));
        movies.add(new Movie("Interstellar", "2014", "2h 49m", 0));
        movies.add(new Movie("The Dark Knight", "2008", "2h 32m", 0));

        MovieAdapter adapter = new MovieAdapter(movies, false, this);
        binding.rvTrending.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvTrending.setAdapter(adapter);
    }

    @Override
    public void onMovieClick(Movie movie) {
        showDetails(movie.getTitle());
    }

    private void showDetails(String title) {
        Toast.makeText(getContext(), "Opening Details for: " + title, Toast.LENGTH_SHORT).show();
        // Here you would navigate to MovieDetailActivity/Fragment
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
