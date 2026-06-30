package kh.edu.rupp.myapplication.fragments;

import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import kh.edu.rupp.myapplication.MovieDetailsActivity;
import kh.edu.rupp.myapplication.adapters.WatchlistAdapter;
import kh.edu.rupp.myapplication.data.MovieCatalog;
import kh.edu.rupp.myapplication.data.MovieMapper;
import kh.edu.rupp.myapplication.data.MovieRepository;
import kh.edu.rupp.myapplication.db.AppDatabase;
import kh.edu.rupp.myapplication.db.MovieEntity;
import kh.edu.rupp.myapplication.databinding.FragmentWatchlistBinding;
import kh.edu.rupp.myapplication.models.Movie;
import kh.edu.rupp.myapplication.viewmodel.ViewModelFactory;
import kh.edu.rupp.myapplication.viewmodel.WatchlistViewModel;

public class WatchlistFragment extends Fragment implements WatchlistAdapter.OnWatchlistItemClickListener {

    private FragmentWatchlistBinding binding;
    private WatchlistViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWatchlistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MovieRepository repository = new MovieRepository(AppDatabase.getInstance(requireContext()).watchlistDao());
        viewModel = new ViewModelFactory(repository).create(WatchlistViewModel.class);
        observeWatchlist();
    }

    private void observeWatchlist() {
        viewModel.getWatchlist().observe(getViewLifecycleOwner(), entities -> {
            List<Movie> movies = toMovies(entities);
            if (movies.isEmpty()) {
                movies = MovieCatalog.watchlistSeed();
            }
            renderWatchlist(movies);
        });
    }

    private List<Movie> toMovies(List<MovieEntity> entities) {
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity entity : entities) {
            movies.add(MovieMapper.toMovie(entity));
        }
        return movies;
    }

    private void renderWatchlist(List<Movie> movies) {
        if (movies.isEmpty()) {
            binding.emptyState.setVisibility(View.VISIBLE);
            binding.rvWatchlist.setVisibility(View.GONE);
            binding.staticWatchlistContainer.setVisibility(View.GONE);
            binding.cardMovieOne.setVisibility(View.GONE);
            binding.cardMovieTwo.setVisibility(View.GONE);
            binding.cardMovieThree.setVisibility(View.GONE);
            return;
        } else {
            binding.emptyState.setVisibility(View.GONE);
        }

        binding.staticWatchlistContainer.setVisibility(View.GONE);
        binding.rvWatchlist.setVisibility(View.VISIBLE);
        binding.rvWatchlist.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvWatchlist.setAdapter(new WatchlistAdapter(movies, this));

        if (movies.size() > 0) {
            Movie movie1 = movies.get(0);
            binding.tvTitleOne.setText(movie1.getTitle());
            binding.tvInfoOne.setText(movie1.getYear() + " • " + movie1.getDuration());
            binding.ivPosterOne.setImageResource(movie1.getPosterResId());
            binding.cardMovieOne.setVisibility(View.VISIBLE);
        } else {
            binding.cardMovieOne.setVisibility(View.GONE);
        }

        if (movies.size() > 1) {
            Movie movie2 = movies.get(1);
            binding.tvTitleTwo.setText(movie2.getTitle());
            binding.tvInfoTwo.setText(movie2.getYear() + " • " + movie2.getDuration());
            binding.ivPosterTwo.setImageResource(movie2.getPosterResId());
            binding.cardMovieTwo.setVisibility(View.VISIBLE);
        } else {
            binding.cardMovieTwo.setVisibility(View.GONE);
        }

        if (movies.size() > 2) {
            Movie movie3 = movies.get(2);
            binding.tvTitleThree.setText(movie3.getTitle());
            binding.tvInfoThree.setText(movie3.getYear() + " • " + movie3.getDuration());
            binding.ivPosterThree.setImageResource(movie3.getPosterResId());
            binding.cardMovieThree.setVisibility(View.VISIBLE);
        } else {
            binding.cardMovieThree.setVisibility(View.GONE);
        }
    }

    @Override
    public void onWatchlistItemClick(Movie movie) {
        Intent intent = new Intent(requireContext(), MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
