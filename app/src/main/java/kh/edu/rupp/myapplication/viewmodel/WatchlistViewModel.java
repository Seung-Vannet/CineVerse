package kh.edu.rupp.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import kh.edu.rupp.myapplication.data.MovieRepository;
import kh.edu.rupp.myapplication.db.MovieEntity;

public class WatchlistViewModel extends ViewModel {
    private final MovieRepository repository;

    public WatchlistViewModel(MovieRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<MovieEntity>> getWatchlist() {
        return repository.getWatchlist();
    }
}
