package kh.edu.rupp.myapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieResponse> searchMovies(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("movie/{movie_id}")
    Call<MovieDetailsDto> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MovieResponse> discoverMoviesByGenre(
            @Query("api_key") String apiKey,
            @Query("with_genres") int genreId,
            @Query("sort_by") String sortBy
    );
}
