package kh.edu.rupp.myapplication.data;

import androidx.lifecycle.LiveData;
import kh.edu.rupp.myapplication.BuildConfig;
import kh.edu.rupp.myapplication.api.ApiService;
import kh.edu.rupp.myapplication.api.CreditsResponse;
import kh.edu.rupp.myapplication.api.MovieDetailsDto;
import kh.edu.rupp.myapplication.api.MovieResponse;
import kh.edu.rupp.myapplication.api.VideosResponse;
import kh.edu.rupp.myapplication.api.RetrofitClient;
import kh.edu.rupp.myapplication.db.WatchlistDao;
import kh.edu.rupp.myapplication.db.MovieEntity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private final ApiService apiService;
    private final WatchlistDao watchlistDao;
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;

    public MovieRepository(WatchlistDao watchlistDao) {
        this.apiService = RetrofitClient.getService();
        this.watchlistDao = watchlistDao;
    }

    public void getPopularMovies(Callback<MovieResponse> callback) {
        apiService.getPopularMovies(API_KEY).enqueue(callback);
    }

    public void getTopRatedMovies(Callback<MovieResponse> callback) {
        apiService.getTopRatedMovies(API_KEY).enqueue(callback);
    }

    public void searchMovies(String query, Callback<MovieResponse> callback) {
        apiService.searchMovies(API_KEY, query).enqueue(callback);
    }

    public void getMovieDetails(int movieId, Callback<MovieDetailsDto> callback) {
        apiService.getMovieDetails(movieId, API_KEY).enqueue(callback);
    }

    public void getMovieCredits(int movieId, Callback<CreditsResponse> callback) {
        apiService.getMovieCredits(movieId, API_KEY).enqueue(callback);
    }

    public void getMovieVideos(int movieId, Callback<VideosResponse> callback) {
        apiService.getMovieVideos(movieId, API_KEY).enqueue(callback);
    }

    public void discoverMoviesByGenre(int genreId, Callback<MovieResponse> callback) {
        apiService.discoverMoviesByGenre(API_KEY, genreId, "popularity.desc").enqueue(callback);
    }

    // Local Storage
    public LiveData<List<MovieEntity>> getWatchlist() {
        return watchlistDao.getAllMovies();
    }

    public void addToWatchlist(MovieEntity movie) {
        new Thread(() -> watchlistDao.insertMovie(movie)).start();
    }

    public void removeFromWatchlist(MovieEntity movie) {
        new Thread(() -> watchlistDao.deleteMovie(movie)).start();
    }

    public void removeFromWatchlistById(int movieId) {
        new Thread(() -> watchlistDao.deleteMovieById(movieId)).start();
    }
    
    public LiveData<Boolean> isFavorite(int id) {
        return watchlistDao.isFavorite(id);
    }
}
