package kh.edu.rupp.myapplication.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "watchlist")
public class MovieEntity {
    @PrimaryKey
    private int id;
    private String title;
    private String posterPath;
    private String releaseDate;
    private double voteAverage;
    private String overview;
    private String duration;
    private String ageRating;
    private int posterResId;

    @Ignore
    public MovieEntity(int id, String title, String posterPath, String releaseDate, double voteAverage, String overview) {
        this(id, title, posterPath, releaseDate, voteAverage, overview, "", "", 0);
    }

    public MovieEntity(int id, String title, String posterPath, String releaseDate, double voteAverage, String overview,
                       String duration, String ageRating, int posterResId) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.duration = duration;
        this.ageRating = ageRating;
        this.posterResId = posterResId;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getPosterPath() { return posterPath; }
    public String getReleaseDate() { return releaseDate; }
    public double getVoteAverage() { return voteAverage; }
    public String getOverview() { return overview; }
    public String getDuration() { return duration; }
    public String getAgeRating() { return ageRating; }
    public int getPosterResId() { return posterResId; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setPosterPath(String posterPath) { this.posterPath = posterPath; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
    public void setVoteAverage(double voteAverage) { this.voteAverage = voteAverage; }
    public void setOverview(String overview) { this.overview = overview; }
    public void setDuration(String duration) { this.duration = duration; }
    public void setAgeRating(String ageRating) { this.ageRating = ageRating; }
    public void setPosterResId(int posterResId) { this.posterResId = posterResId; }
}
