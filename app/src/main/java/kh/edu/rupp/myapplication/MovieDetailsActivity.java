package kh.edu.rupp.myapplication;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.chip.Chip;
import kh.edu.rupp.myapplication.adapters.MovieAdapter;
import kh.edu.rupp.myapplication.api.MovieDetailsDto;
import kh.edu.rupp.myapplication.data.MovieCatalog;
import kh.edu.rupp.myapplication.data.MovieMapper;
import kh.edu.rupp.myapplication.data.MovieRepository;
import kh.edu.rupp.myapplication.db.AppDatabase;
import kh.edu.rupp.myapplication.databinding.ActivityMovieDetailsBinding;
import kh.edu.rupp.myapplication.models.Movie;
import kh.edu.rupp.myapplication.utils.MovieImageLoader;
import kh.edu.rupp.myapplication.viewmodel.MovieDetailViewModel;
import kh.edu.rupp.myapplication.viewmodel.ViewModelFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    private ActivityMovieDetailsBinding binding;
    private Movie currentMovie;
    private MovieDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MovieRepository repository = new MovieRepository(AppDatabase.getInstance(this).watchlistDao());
        viewModel = new ViewModelFactory(repository).create(MovieDetailViewModel.class);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        if (movie != null) {
            populateDetails(movie);
            loadFullDetails(movie);
        }

        setupInteractions();
        setupSimilarMovies();
    }

    private void populateDetails(Movie movie) {
        currentMovie = movie;
        binding.tvMovieTitle.setText(movie.getTitle());
        binding.tvMetadata.setText(movie.getYear() + " • " + movie.getDuration() + " • " + movie.getAgeRating() + " • HD");
        binding.tvRating.setText(movie.getRating() + "/10  250K+ ratings");
        binding.tvDescription.setText(movie.getDescription());
        
        MovieImageLoader.load(binding.ivBackdrop, movie);

        renderGenres(movie.getAgeRating());
    }

    private void renderGenres(String genreText) {
        binding.chipGroupGenres.removeAllViews();
        String[] genres = genreText.split(",");
        for (String genre : genres) {
            String trimmedGenre = genre.trim();
            if (trimmedGenre.isEmpty()) {
                continue;
            }
            Chip chip = new Chip(this);
            chip.setText(trimmedGenre);
            chip.setChipBackgroundColorResource(R.color.cine_surface);
            chip.setTextColor(getResources().getColor(R.color.white));
            binding.chipGroupGenres.addView(chip);
        }
    }

    private void loadFullDetails(Movie movie) {
        if (movie.getId() <= 0) {
            return;
        }
        MovieRepository repository = new MovieRepository(AppDatabase.getInstance(this).watchlistDao());
        repository.getMovieDetails(movie.getId(), new Callback<MovieDetailsDto>() {
            @Override
            public void onResponse(Call<MovieDetailsDto> call, Response<MovieDetailsDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    populateDetails(MovieMapper.fromDetailsDto(response.body()));
                }
            }

            @Override
            public void onFailure(Call<MovieDetailsDto> call, Throwable t) {
                Toast.makeText(MovieDetailsActivity.this, "Showing basic movie details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupInteractions() {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.btnPlay.setOnClickListener(v -> Toast.makeText(this, "Starting Movie...", Toast.LENGTH_SHORT).show());
        binding.btnMyList.setOnClickListener(v -> {
            if (currentMovie != null) {
                viewModel.addToWatchlist(MovieMapper.toEntity(currentMovie));
                Toast.makeText(this, "Added to Watchlist", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSimilarMovies() {
        MovieAdapter adapter = new MovieAdapter(MovieCatalog.similarMovies(), false, this);
        binding.rvSimilarMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvSimilarMovies.setAdapter(adapter);
    }

    @Override
    public void onMovieClick(Movie movie) {
        populateDetails(movie);
    }
}
