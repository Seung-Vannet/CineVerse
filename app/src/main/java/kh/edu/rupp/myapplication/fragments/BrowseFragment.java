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
import kh.edu.rupp.myapplication.api.MovieResponse;
import kh.edu.rupp.myapplication.data.MovieCatalog;
import kh.edu.rupp.myapplication.data.MovieMapper;
import kh.edu.rupp.myapplication.data.MovieRepository;
import kh.edu.rupp.myapplication.db.AppDatabase;
import kh.edu.rupp.myapplication.databinding.FragmentBrowseBinding;
import kh.edu.rupp.myapplication.models.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseFragment extends Fragment implements MovieAdapter.OnMovieClickListener {

    private FragmentBrowseBinding binding;
    private MovieRepository repository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBrowseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository = new MovieRepository(AppDatabase.getInstance(requireContext()).watchlistDao());
        setupInteractions();
        loadTopRatedMovies();
    }

    private void setupInteractions() {
        // Search
        binding.ivSearch.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });

        // Category Toggles
        binding.btnMovies.setOnClickListener(v -> Toast.makeText(getContext(), "Movies Selected", Toast.LENGTH_SHORT).show());
        binding.btnTvShows.setOnClickListener(v -> Toast.makeText(getContext(), "Shows Selected", Toast.LENGTH_SHORT).show());
        binding.btnGenres.setOnClickListener(v -> Toast.makeText(getContext(), "Genres Selected", Toast.LENGTH_SHORT).show());

        // Genre Manual Item Clicks
        binding.genreAction.setOnClickListener(v -> Toast.makeText(getContext(), "Action Genre", Toast.LENGTH_SHORT).show());
        binding.genreAdventure.setOnClickListener(v -> Toast.makeText(getContext(), "Adventure Genre", Toast.LENGTH_SHORT).show());
        binding.genreSciFi.setOnClickListener(v -> Toast.makeText(getContext(), "Sci-Fi Genre", Toast.LENGTH_SHORT).show());
        binding.genreThriller.setOnClickListener(v -> Toast.makeText(getContext(), "Thriller Genre", Toast.LENGTH_SHORT).show());

        // New Releases Manual Blocks
        binding.browseNewDune.setOnClickListener(v -> openMovieDetails(MovieCatalog.newReleases().get(0)));
        binding.browseNewAvatar.setOnClickListener(v -> openMovieDetails(MovieCatalog.newReleases().get(1)));
        binding.browseNewSpiderman.setOnClickListener(v -> openMovieDetails(MovieCatalog.newReleases().get(2)));

        // Top Rated Manual Blocks
        binding.browseTopJohnwick.setOnClickListener(v -> openMovieDetails(MovieCatalog.topRated().get(0)));
        binding.browseTopInterstellar.setOnClickListener(v -> openMovieDetails(MovieCatalog.topRated().get(1)));
        binding.browseTopBatman.setOnClickListener(v -> openMovieDetails(MovieCatalog.topRated().get(2)));
    }

    private void loadTopRatedMovies() {
        binding.rvLiveTopRated.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvLiveTopRated.setAdapter(new MovieAdapter(MovieCatalog.topRated(), false, this));

        repository.getTopRatedMovies(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (binding == null || !response.isSuccessful() || response.body() == null) {
                    return;
                }
                binding.rvLiveTopRated.setAdapter(new MovieAdapter(MovieMapper.fromDtoList(response.body().getResults()), false, BrowseFragment.this));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Using offline top rated movies", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openMovieDetails(Movie movie) {
        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }

    @Override
    public void onMovieClick(Movie movie) {
        openMovieDetails(movie);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
