package kh.edu.rupp.myapplication.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface WatchlistDao {
    @Query("SELECT * FROM watchlist")
    LiveData<List<MovieEntity>> getAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieEntity movie);

    @Delete
    void deleteMovie(MovieEntity movie);

    @Query("SELECT EXISTS(SELECT * FROM watchlist WHERE id = :id)")
    LiveData<Boolean> isFavorite(int id);
}
