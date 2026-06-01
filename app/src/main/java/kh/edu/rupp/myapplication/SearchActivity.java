package kh.edu.rupp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import kh.edu.rupp.myapplication.adapters.SearchAdapter;
import kh.edu.rupp.myapplication.databinding.ActivitySearchBinding;
import kh.edu.rupp.myapplication.models.Movie;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnSearchItemClickListener {

    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupInteractions();
        setupRecentSearches();
        setupPopularSearches();
    }

    private void setupInteractions() {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.tvCancel.setOnClickListener(v -> finish());
    }

    private void setupRecentSearches() {
        String[] recent = {"Inception", "The Dark Knight", "Interstellar"};
        for (String term : recent) {
            Chip chip = new Chip(this);
            chip.setText(term);
            chip.setChipBackgroundColorResource(R.color.cine_surface);
            chip.setTextColor(getResources().getColor(R.color.white));
            binding.chipGroupRecent.addView(chip);
        }
    }

    private void setupPopularSearches() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Dune: Part Two", "2024", "2h 46m", "8.6", "PG-13", "Epic sci-fi.", 0));
        movies.add(new Movie("The Batman", "2022", "2h 56m", "8.1", "PG-13", "Dark detective story.", 0));
        movies.add(new Movie("Oppenheimer", "2023", "3h 0m", "8.4", "R", "Historical drama.", 0));
        movies.add(new Movie("Spider-Man: No Way Home", "2021", "2h 28m", "8.2", "PG-13", "Multiverse action.", 0));

        SearchAdapter adapter = new SearchAdapter(movies, this);
        binding.rvPopularSearches.setAdapter(adapter);
    }

    @Override
    public void onSearchItemClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
