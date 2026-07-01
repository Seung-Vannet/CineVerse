package kh.edu.rupp.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;
import kh.edu.rupp.myapplication.data.MovieRepository;

public class BrowseViewModel extends ViewModel {
    private final MovieRepository repository;

    public BrowseViewModel(MovieRepository repository) {
        this.repository = repository;
    }

    public MovieRepository getRepository() {
        return repository;
    }
}
