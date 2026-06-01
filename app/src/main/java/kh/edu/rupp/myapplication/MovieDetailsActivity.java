package kh.edu.rupp.myapplication;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.chip.Chip;
import kh.edu.rupp.myapplication.adapters.MovieAdapter;
import kh.edu.rupp.myapplication.databinding.ActivityMovieDetailsBinding;
import kh.edu.rupp.myapplication.models.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    private ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        if (movie != null) {
            populateDetails(movie);
        }

        setupInteractions();
        setupSimilarMovies();
    }

    private void populateDetails(Movie movie) {
        binding.tvMovieTitle.setText(movie.getTitle());
        binding.tvMetadata.setText(movie.getYear() + " • " + movie.getDuration() + " • " + movie.getAgeRating() + " • HD");
        binding.tvRating.setText(movie.getRating() + "/10  250K+ ratings");
        binding.tvDescription.setText(movie.getDescription());

        // Add dummy genres
        String[] genres = {"Action", "Adventure", "Sci-Fi"};
        for (String genre : genres) {
            Chip chip = new Chip(this);
            chip.setText(genre);
            chip.setChipBackgroundColorResource(R.color.cine_surface);
            chip.setTextColor(getResources().getColor(R.color.white));
            binding.chipGroupGenres.addView(chip);
        }
    }

    private void setupInteractions() {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.ivShare.setOnClickListener(v -> Toast.makeText(this, "Sharing...", Toast.LENGTH_SHORT).show());
        binding.btnPlay.setOnClickListener(v -> Toast.makeText(this, "Playing...", Toast.LENGTH_SHORT).show());
        binding.fabPlay.setOnClickListener(v -> Toast.makeText(this, "Playing Trailer...", Toast.LENGTH_SHORT).show());
        binding.btnMyList.setOnClickListener(v -> Toast.makeText(this, "Added to Watchlist", Toast.LENGTH_SHORT).show());
    }

    private void setupSimilarMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Joker", "2019", "2h 2m", "8.4", "R", "Origin story of Joker.", 0));
        movies.add(new Movie("The Dark Knight", "2008", "2h 32m", "9.0", "PG-13", "Batman vs Joker.", 0));
        movies.add(new Movie("Se7en", "1995", "2h 7m", "8.6", "R", "Detective thriller.", 0));

        MovieAdapter adapter = new MovieAdapter(movies, false, this);
        binding.rvSimilarMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvSimilarMovies.setAdapter(adapter);
    }

    @Override
    public void onMovieClick(Movie movie) {
        // Reload details for the clicked similar movie
        populateDetails(movie);
        binding.chipGroupGenres.removeAllViews(); // Reset genres for new movie
        populateDetails(movie);
        Toast.makeText(this, "Opening: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
