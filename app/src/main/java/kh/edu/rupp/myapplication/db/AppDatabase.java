package kh.edu.rupp.myapplication.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract WatchlistDao watchlistDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "cineverse_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
