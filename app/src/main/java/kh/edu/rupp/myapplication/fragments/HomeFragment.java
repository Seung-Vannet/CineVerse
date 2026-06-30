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
import androidx.recyclerview.widget.RecyclerView;
import kh.edu.rupp.myapplication.MovieDetailsActivity;
import kh.edu.rupp.myapplication.SearchActivity;
import kh.edu.rupp.myapplication.adapters.MovieAdapter;
import kh.edu.rupp.myapplication.api.MovieResponse;
import kh.edu.rupp.myapplication.data.MovieCatalog;
import kh.edu.rupp.myapplication.data.MovieMapper;
import kh.edu.rupp.myapplication.data.MovieRepository;
import kh.edu.rupp.myapplication.db.AppDatabase;
import kh.edu.rupp.myapplication.databinding.FragmentHomeBinding;
import kh.edu.rupp.myapplication.models.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements MovieAdapter.OnMovieClickListener {

    private FragmentHomeBinding binding;
    private MovieRepository repository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository = new MovieRepository(AppDatabase.getInstance(requireContext()).watchlistDao());
        setupInteractions();
        loadPopularMovies();
    }

    private void setupInteractions() {
        binding.heroBanner.setOnClickListener(v -> openMovieDetails(MovieCatalog.heroMovie()));

        binding.btnPlayNow.setOnClickListener(v -> Toast.makeText(getContext(), "Streaming Dune...", Toast.LENGTH_SHORT).show());
        binding.btnMyList.setOnClickListener(v -> Toast.makeText(getContext(), "Added to Watchlist", Toast.LENGTH_SHORT).show());

        binding.searchBarCard.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });

        binding.rvContinueWatching.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvContinueWatching.setAdapter(new MovieAdapter(MovieCatalog.continueWatching(), true, this));

        binding.rvTrending.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvTrending.setAdapter(new MovieAdapter(MovieCatalog.trending(), false, this));

        setupGenreRow(binding.rvAction, MovieCatalog.actionMovies());
        setupGenreRow(binding.rvComedy, MovieCatalog.comedyMovies());
        setupGenreRow(binding.rvHorror, MovieCatalog.horrorMovies());
        setupGenreRow(binding.rvRomance, MovieCatalog.romanceMovies());
        setupGenreRow(binding.rvSciFi, MovieCatalog.sciFiMovies());
    }

    private void setupGenreRow(RecyclerView recyclerView, java.util.List<Movie> fallbackMovies) {
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new MovieAdapter(fallbackMovies, false, this));
    }

    private void loadPopularMovies() {
        repository.getPopularMovies(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (binding == null || !response.isSuccessful() || response.body() == null) {
                    return;
                }
                binding.rvTrending.setAdapter(new MovieAdapter(MovieMapper.fromDtoList(response.body().getResults()), false, HomeFragment.this));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Using offline movie data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loadGenreMovies(28, binding.rvAction);
        loadGenreMovies(35, binding.rvComedy);
        loadGenreMovies(27, binding.rvHorror);
        loadGenreMovies(10749, binding.rvRomance);
        loadGenreMovies(878, binding.rvSciFi);
    }

    private void loadGenreMovies(int genreId, RecyclerView recyclerView) {
        repository.discoverMoviesByGenre(genreId, new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (binding == null || !response.isSuccessful() || response.body() == null) {
                    return;
                }
                recyclerView.setAdapter(new MovieAdapter(MovieMapper.fromDtoList(response.body().getResults()), false, HomeFragment.this));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
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
