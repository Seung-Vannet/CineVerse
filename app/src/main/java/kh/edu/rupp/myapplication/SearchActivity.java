package kh.edu.rupp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import kh.edu.rupp.myapplication.databinding.ActivitySearchBinding;
import kh.edu.rupp.myapplication.models.Movie;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupInteractions();
        setupRecentSearches();
    }

    private void setupInteractions() {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.tvCancel.setOnClickListener(v -> finish());

        // Setup manual clicks for each search result item
        binding.searchItemDune.setOnClickListener(v -> openMovieDetails(new Movie("Dune: Part Two", "2024", "2h 46m", "8.6", "PG-13", "Epic sci-fi.", 0)));
        binding.searchItemBatman.setOnClickListener(v -> openMovieDetails(new Movie("The Batman", "2022", "2h 56m", "8.1", "PG-13", "Dark detective story.", 0)));
        binding.searchItemOppenheimer.setOnClickListener(v -> openMovieDetails(new Movie("Oppenheimer", "2023", "3h 0m", "8.4", "R", "Historical drama.", 0)));
        binding.searchItemSpiderman.setOnClickListener(v -> openMovieDetails(new Movie("Spider-Man: No Way Home", "2021", "2h 28m", "8.2", "PG-13", "Multiverse action.", 0)));
        binding.searchItemJohnwick.setOnClickListener(v -> openMovieDetails(new Movie("John Wick 4", "2023", "2h 49m", "8.1", "R", "Action thriller.", 0)));
        binding.searchItemInterstellar.setOnClickListener(v -> openMovieDetails(new Movie("Interstellar", "2014", "2h 49m", "8.7", "PG-13", "Space journey.", 0)));
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

    private void openMovieDetails(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
