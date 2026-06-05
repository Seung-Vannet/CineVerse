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
import kh.edu.rupp.myapplication.MovieDetailsActivity;
import kh.edu.rupp.myapplication.SearchActivity;
import kh.edu.rupp.myapplication.databinding.FragmentBrowseBinding;
import kh.edu.rupp.myapplication.models.Movie;

public class BrowseFragment extends Fragment {

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
        binding.browseNewDune.setOnClickListener(v -> openMovieDetails(new Movie("Dune: Part Two", "2024", "2h 46m", "8.6", "PG-13", "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.", 0)));
        binding.browseNewAvatar.setOnClickListener(v -> openMovieDetails(new Movie("Avatar 2", "2022", "3h 12m", "7.6", "PG-13", "Jake Sully lives with his newfound family formed on the extrasolar moon Pandora.", 0)));
        binding.browseNewSpiderman.setOnClickListener(v -> openMovieDetails(new Movie("Spider-Man", "2023", "2h 20m", "8.2", "PG-13", "Miles Morales catapulted across the Multiverse, where he encounters a team of Spider-People charged with protecting its very existence.", 0)));

        // Top Rated Manual Blocks
        binding.browseTopJohnwick.setOnClickListener(v -> openMovieDetails(new Movie("John Wick 4", "2023", "2h 49m", "8.1", "R", "John Wick uncovers a path to defeating The High Table.", 0)));
        binding.browseTopInterstellar.setOnClickListener(v -> openMovieDetails(new Movie("Interstellar", "2014", "2h 49m", "8.7", "PG-13", "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.", 0)));
        binding.browseTopBatman.setOnClickListener(v -> openMovieDetails(new Movie("The Batman", "2022", "2h 56m", "8.1", "PG-13", "Batman ventures into Gotham City's underworld when a sadistic killer leaves behind a trail of cryptic clues.", 0)));
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
