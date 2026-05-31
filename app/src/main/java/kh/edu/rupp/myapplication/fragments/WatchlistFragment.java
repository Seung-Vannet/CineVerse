package kh.edu.rupp.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import kh.edu.rupp.myapplication.adapters.WatchlistAdapter;
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
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Inception", "2010", "2h 28m", 0));
        movies.add(new Movie("The Matrix", "1999", "2h 16m", 0));
        movies.add(new Movie("Interstellar", "2014", "2h 49m", 0));
        movies.add(new Movie("The Lord of the Rings", "2003", "3h 21m", 0));

        WatchlistAdapter adapter = new WatchlistAdapter(movies);
        binding.rvWatchlist.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvWatchlist.setAdapter(adapter);

        if (movies.isEmpty()) {
            binding.emptyState.setVisibility(View.VISIBLE);
            binding.rvWatchlist.setVisibility(View.GONE);
        } else {
            binding.emptyState.setVisibility(View.GONE);
            binding.rvWatchlist.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
