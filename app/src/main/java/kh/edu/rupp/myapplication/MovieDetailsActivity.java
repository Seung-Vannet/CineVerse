package kh.edu.rupp.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.chip.Chip;
import java.util.List;
import kh.edu.rupp.myapplication.adapters.CastAdapter;
import kh.edu.rupp.myapplication.adapters.MovieAdapter;
import kh.edu.rupp.myapplication.api.CreditsResponse;
import kh.edu.rupp.myapplication.api.MovieDetailsDto;
import kh.edu.rupp.myapplication.api.VideoDto;
import kh.edu.rupp.myapplication.api.VideosResponse;
import kh.edu.rupp.myapplication.data.MovieCatalog;
import kh.edu.rupp.myapplication.data.MovieMapper;
import kh.edu.rupp.myapplication.data.MovieRepository;
import kh.edu.rupp.myapplication.db.AppDatabase;
import kh.edu.rupp.myapplication.databinding.ActivityMovieDetailsBinding;
import kh.edu.rupp.myapplication.models.CastMember;
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
    private boolean currentMovieInWatchlist;
    private MovieRepository repository;
    private String trailerUrl;
    private LiveData<Boolean> watchlistState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = new MovieRepository(AppDatabase.getInstance(this).watchlistDao());
        viewModel = new ViewModelFactory(repository).create(MovieDetailViewModel.class);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        if (movie != null) {
            populateDetails(movie);
            loadFullDetails(movie);
            loadCredits(movie);
            loadTrailer(movie);
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
        observeWatchlistState(movie);
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

    private void loadCredits(Movie movie) {
        if (movie.getId() <= 0) {
            binding.rvCast.setVisibility(View.GONE);
            binding.castHeader.setVisibility(View.GONE);
            return;
        }
        repository.getMovieCredits(movie.getId(), new Callback<CreditsResponse>() {
            @Override
            public void onResponse(Call<CreditsResponse> call, Response<CreditsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CastMember> castMembers = MovieMapper.fromCastDtoList(response.body().getCast());
                    if (castMembers.isEmpty()) {
                        binding.rvCast.setVisibility(View.GONE);
                        binding.castHeader.setVisibility(View.GONE);
                        return;
                    }
                    binding.castHeader.setVisibility(View.VISIBLE);
                    binding.rvCast.setVisibility(View.VISIBLE);
                    binding.rvCast.setLayoutManager(new LinearLayoutManager(MovieDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    binding.rvCast.setAdapter(new CastAdapter(castMembers));
                }
            }

            @Override
            public void onFailure(Call<CreditsResponse> call, Throwable t) {
                binding.rvCast.setVisibility(View.GONE);
                binding.castHeader.setVisibility(View.GONE);
            }
        });
    }

    private void loadTrailer(Movie movie) {
        trailerUrl = null;
        binding.tvTrailerState.setVisibility(View.GONE);
        if (movie.getId() <= 0) {
            return;
        }
        repository.getMovieVideos(movie.getId(), new Callback<VideosResponse>() {
            @Override
            public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
                if (!response.isSuccessful() || response.body() == null || response.body().getResults() == null) {
                    showTrailerUnavailable();
                    return;
                }
                for (VideoDto video : response.body().getResults()) {
                    if ("YouTube".equalsIgnoreCase(video.getSite())
                            && ("Trailer".equalsIgnoreCase(video.getType()) || "Teaser".equalsIgnoreCase(video.getType()))
                            && video.getKey() != null) {
                        trailerUrl = "https://www.youtube.com/watch?v=" + video.getKey();
                        binding.tvTrailerState.setVisibility(View.GONE);
                        return;
                    }
                }
                showTrailerUnavailable();
            }

            @Override
            public void onFailure(Call<VideosResponse> call, Throwable t) {
                showTrailerUnavailable();
            }
        });
    }

    private void showTrailerUnavailable() {
        trailerUrl = null;
        binding.tvTrailerState.setVisibility(View.VISIBLE);
    }

    private void setupInteractions() {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.btnPlay.setOnClickListener(v -> openTrailer());
        binding.fabPlay.setOnClickListener(v -> openTrailer());
        binding.ivShare.setOnClickListener(v -> shareMovie());
        binding.btnDownload.setOnClickListener(v -> Toast.makeText(this, "Download queued for offline viewing", Toast.LENGTH_SHORT).show());
        binding.btnMyList.setOnClickListener(v -> {
            if (currentMovie != null) {
                if (currentMovieInWatchlist) {
                    viewModel.removeFromWatchlist(MovieMapper.toEntity(currentMovie));
                    Toast.makeText(this, "Removed from Watchlist", Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.addToWatchlist(MovieMapper.toEntity(currentMovie));
                    Toast.makeText(this, "Added to Watchlist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openTrailer() {
        if (trailerUrl == null) {
            Toast.makeText(this, "Trailer is not available yet", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl));
        startActivity(intent);
    }

    private void shareMovie() {
        if (currentMovie == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Watch " + currentMovie.getTitle() + " on Cineverse");
        startActivity(Intent.createChooser(intent, "Share movie"));
    }

    private void observeWatchlistState(Movie movie) {
        if (movie.getId() <= 0) {
            currentMovieInWatchlist = false;
            binding.btnMyList.setText("+ My List");
            return;
        }
        if (watchlistState != null) {
            watchlistState.removeObservers(this);
        }
        watchlistState = viewModel.isFavorite(movie.getId());
        watchlistState.observe(this, isFavorite -> {
            if (currentMovie != null && currentMovie.getId() == movie.getId()) {
                currentMovieInWatchlist = Boolean.TRUE.equals(isFavorite);
                binding.btnMyList.setText(currentMovieInWatchlist ? "Remove" : "+ My List");
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
        loadFullDetails(movie);
        loadCredits(movie);
        loadTrailer(movie);
    }
}
