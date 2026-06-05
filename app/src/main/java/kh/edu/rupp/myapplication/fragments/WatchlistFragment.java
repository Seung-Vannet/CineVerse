package kh.edu.rupp.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import kh.edu.rupp.myapplication.R;
import kh.edu.rupp.myapplication.databinding.FragmentWatchlistBinding;
import kh.edu.rupp.myapplication.models.Movie;
import java.util.ArrayList;
import java.util.List;

public class WatchlistFragment extends Fragment {

    private FragmentWatchlistBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWatchlistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupStaticWatchlist();
    }

    private void setupStaticWatchlist() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Inception", "2010", "2h 28m", R.drawable.inception));
        movies.add(new Movie("The Matrix", "1999", "2h 16m", R.drawable.thematrix));
        movies.add(new Movie("Interstellar", "2014", "2h 49m", R.drawable.interstellar));


        if (movies.isEmpty()) {
            binding.emptyState.setVisibility(View.VISIBLE);
            binding.cardMovieOne.setVisibility(View.GONE);
            binding.cardMovieTwo.setVisibility(View.GONE);
            binding.cardMovieThree.setVisibility(View.GONE);
            return;
        } else {
            binding.emptyState.setVisibility(View.GONE);
        }

        if (movies.size() > 0) {
            Movie movie1 = movies.get(0);
            binding.tvTitleOne.setText(movie1.getTitle());
            binding.tvInfoOne.setText(movie1.getYear() + " • " + movie1.getDuration());
            binding.ivPosterOne.setImageResource(movie1.getPosterResId());
        } else {
            binding.cardMovieOne.setVisibility(View.GONE);
        }

        if (movies.size() > 1) {
            Movie movie2 = movies.get(1);
            binding.tvTitleTwo.setText(movie2.getTitle());
            binding.tvInfoTwo.setText(movie2.getYear() + " • " + movie2.getDuration());
            binding.ivPosterTwo.setImageResource(movie2.getPosterResId());
        } else {
            binding.cardMovieTwo.setVisibility(View.GONE);
        }

        if (movies.size() > 2) {
            Movie movie3 = movies.get(2);
            binding.tvTitleThree.setText(movie3.getTitle());
            binding.tvInfoThree.setText(movie3.getYear() + " • " + movie3.getDuration());
            binding.ivPosterThree.setImageResource(movie3.getPosterResId());
        } else {
            binding.cardMovieThree.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}