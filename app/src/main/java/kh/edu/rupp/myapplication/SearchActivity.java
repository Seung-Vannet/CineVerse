package kh.edu.rupp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.chip.Chip;
import java.util.List;
import kh.edu.rupp.myapplication.adapters.SearchAdapter;
import kh.edu.rupp.myapplication.api.MovieResponse;
import kh.edu.rupp.myapplication.data.MovieCatalog;
import kh.edu.rupp.myapplication.data.MovieMapper;
import kh.edu.rupp.myapplication.data.MovieRepository;
import kh.edu.rupp.myapplication.db.AppDatabase;
import kh.edu.rupp.myapplication.databinding.ActivitySearchBinding;
import kh.edu.rupp.myapplication.models.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnSearchItemClickListener {

    private ActivitySearchBinding binding;
    private MovieRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = new MovieRepository(AppDatabase.getInstance(this).watchlistDao());

        setupInteractions();
        setupRecentSearches();
        loadImages();
    }

    private void loadImages() {
        // Force loading images to bypass XML rendering bugs
        binding.ivDune.setImageResource(R.drawable.dunetwo);
        binding.ivBatman.setImageResource(R.drawable.thebatman);
        binding.ivOppenheimer.setImageResource(R.drawable.oppenheimer);
        binding.ivJohnwick.setImageResource(R.drawable.johnwickfour);
    }

    private void setupInteractions() {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.tvCancel.setOnClickListener(v -> finish());

        List<Movie> results = MovieCatalog.searchResults();
        binding.searchItemDune.setOnClickListener(v -> openMovieDetails(results.get(0)));
        binding.searchItemBatman.setOnClickListener(v -> openMovieDetails(results.get(1)));
        binding.searchItemOppenheimer.setOnClickListener(v -> openMovieDetails(results.get(2)));
        binding.searchItemJohnwick.setOnClickListener(v -> openMovieDetails(results.get(3)));

        binding.rvSearchResults.setLayoutManager(new LinearLayoutManager(this));
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                if (query.length() >= 2) {
                    searchMovies(query);
                } else {
                    binding.rvSearchResults.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void searchMovies(String query) {
        repository.searchMovies(query, new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    return;
                }
                binding.rvSearchResults.setVisibility(View.VISIBLE);
                binding.rvSearchResults.setAdapter(new SearchAdapter(MovieMapper.fromDtoList(response.body().getResults()), SearchActivity.this));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                binding.rvSearchResults.setVisibility(View.GONE);
            }
        });
    }

    private void setupRecentSearches() {
        String[] recent = {"Inception", "The Dark Knight", "Interstellar"};
        // Use the correct ID 'recentChipsGroup' from activity_search.xml
        if (binding.recentChipsGroup != null) {
            binding.recentChipsGroup.removeAllViews();
            for (String term : recent) {
                Chip chip = new Chip(this);
                chip.setText(term);
                chip.setChipBackgroundColorResource(R.color.cine_surface);
                chip.setTextColor(getResources().getColor(R.color.white));
                binding.recentChipsGroup.addView(chip);
            }
        }
    }

    private void openMovieDetails(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }

    @Override
    public void onSearchItemClick(Movie movie) {
        openMovieDetails(movie);
    }
}
