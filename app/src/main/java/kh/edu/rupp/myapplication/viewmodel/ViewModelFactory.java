package kh.edu.rupp.myapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kh.edu.rupp.myapplication.data.MovieRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final MovieRepository repository;

    public ViewModelFactory(MovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(repository);
        } else if (modelClass.isAssignableFrom(BrowseViewModel.class)) {
            return (T) new BrowseViewModel(repository);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(repository);
        } else if (modelClass.isAssignableFrom(WatchlistViewModel.class)) {
            return (T) new WatchlistViewModel(repository);
        } else if (modelClass.isAssignableFrom(MovieDetailViewModel.class)) {
            return (T) new MovieDetailViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
