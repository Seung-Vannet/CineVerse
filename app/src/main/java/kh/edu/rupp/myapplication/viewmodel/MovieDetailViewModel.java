package kh.edu.rupp.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import kh.edu.rupp.myapplication.data.MovieRepository;
import kh.edu.rupp.myapplication.db.MovieEntity;

public class MovieDetailViewModel extends ViewModel {
    private final MovieRepository repository;

    public MovieDetailViewModel(MovieRepository repository) {
        this.repository = repository;
    }

    public void addToWatchlist(MovieEntity movie) {
        repository.addToWatchlist(movie);
    }

    public void removeFromWatchlist(MovieEntity movie) {
        repository.removeFromWatchlist(movie);
    }

    public LiveData<Boolean> isFavorite(int id) {
        return repository.isFavorite(id);
    }
}
