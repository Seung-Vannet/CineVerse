package kh.edu.rupp.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import kh.edu.rupp.myapplication.MovieDetailsActivity;
import kh.edu.rupp.myapplication.SearchActivity;
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
        binding.heroBanner.setOnClickListener(v -> {
            Movie dune = new Movie("Dune Part Two", "2024", "2h 46m", "8.6", "PG-13", 
                "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.", 0);
            openMovieDetails(dune);
        });
        
        // Buttons
        binding.btnPlayNow.setOnClickListener(v -> Toast.makeText(getContext(), "Starting Movie...", Toast.LENGTH_SHORT).show());
        binding.btnMyList.setOnClickListener(v -> Toast.makeText(getContext(), "Added to My List", Toast.LENGTH_SHORT).show());
        
        // Header Icons
        binding.notificationContainer.setOnClickListener(v -> Toast.makeText(getContext(), "Notifications Clicked", Toast.LENGTH_SHORT).show());
        binding.ivCast.setOnClickListener(v -> Toast.makeText(getContext(), "Casting...", Toast.LENGTH_SHORT).show());
        
        // Search & Filter
        binding.searchBarCard.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });
        binding.ivFilter.setOnClickListener(v -> Toast.makeText(getContext(), "Opening Filters...", Toast.LENGTH_SHORT).show());
    }

    private void setupContinueWatching() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Oppenheimer", "2023", "3h 0m", "8.4", "R", "Historical drama.", 0));
        movies.add(new Movie("The Batman", "2022", "2h 56m", "8.1", "PG-13", "Detective thriller.", 0));
        movies.add(new Movie("Avatar 2", "2022", "3h 12m", "7.6", "PG-13", "Water world.", 0));

        MovieAdapter adapter = new MovieAdapter(movies, true, this);
        binding.rvContinueWatching.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvContinueWatching.setAdapter(adapter);
    }

    private void setupTrending() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Spider-Man", "2023", "2h 20m", "8.2", "PG-13", "Multiverse action.", 0));
        movies.add(new Movie("John Wick 4", "2023", "2h 49m", "8.1", "R", "Action thriller.", 0));
        movies.add(new Movie("Interstellar", "2014", "2h 49m", "8.7", "PG-13", "Space journey.", 0));

        MovieAdapter adapter = new MovieAdapter(movies, false, this);
        binding.rvTrending.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvTrending.setAdapter(adapter);
    }

    @Override
    public void onMovieClick(Movie movie) {
        openMovieDetails(movie);
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
