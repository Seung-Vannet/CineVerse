package kh.edu.rupp.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import kh.edu.rupp.myapplication.MovieDetailsActivity;
import kh.edu.rupp.myapplication.SearchActivity;
import kh.edu.rupp.myapplication.adapters.GenreAdapter;
import kh.edu.rupp.myapplication.adapters.MovieAdapter;
import kh.edu.rupp.myapplication.databinding.FragmentBrowseBinding;
import kh.edu.rupp.myapplication.models.Genre;
import kh.edu.rupp.myapplication.models.Movie;
import java.util.ArrayList;
import java.util.List;

public class BrowseFragment extends Fragment implements MovieAdapter.OnMovieClickListener {

    private FragmentBrowseBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBrowseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupInteractions();
        setupGenres();
        setupNewReleases();
        setupTopRated();
    }

    private void setupInteractions() {
        binding.ivSearch.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });
    }

    private void setupGenres() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre("Action", android.R.drawable.ic_menu_gallery));
        genres.add(new Genre("Adventure", android.R.drawable.ic_menu_compass));
        genres.add(new Genre("Sci-Fi", android.R.drawable.ic_menu_share));
        genres.add(new Genre("Thriller", android.R.drawable.ic_menu_camera));
        genres.add(new Genre("Drama", android.R.drawable.ic_menu_view));

        GenreAdapter adapter = new GenreAdapter(genres);
        binding.rvGenres.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvGenres.setAdapter(adapter);
    }

    private void setupNewReleases() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Dune Part Two", "2024", "2h 46m", "8.6", "PG-13", "Epic sci-fi action.", 0));
        movies.add(new Movie("Godzilla Minus One", "2023", "2h 4m", "7.8", "PG-13", "Giant monster movie.", 0));
        movies.add(new Movie("Mission Impossible", "2023", "2h 43m", "7.6", "PG-13", "Spy action.", 0));

        MovieAdapter adapter = new MovieAdapter(movies, false, this);
        binding.rvNewReleases.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvNewReleases.setAdapter(adapter);
    }

    private void setupTopRated() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Interstellar", "2014", "2h 49m", "8.7", "PG-13", "Space exploration.", 0));
        movies.add(new Movie("John Wick 4", "2023", "2h 49m", "8.1", "R", "Action thriller.", 0));
        movies.add(new Movie("The Dark Knight", "2008", "2h 32m", "9.0", "PG-13", "Batman movie.", 0));

        MovieAdapter adapter = new MovieAdapter(movies, false, this);
        binding.rvTopRated.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvTopRated.setAdapter(adapter);
    }

    @Override
    public void onMovieClick(Movie movie) {
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
