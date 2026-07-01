package kh.edu.rupp.myapplication.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieDetailsDto {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("genres")
    private List<GenreDto> genres;

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getPosterPath() { return posterPath; }
    public String getBackdropPath() { return backdropPath; }
    public String getReleaseDate() { return releaseDate; }
    public double getVoteAverage() { return voteAverage; }
    public String getOverview() { return overview; }
    public int getRuntime() { return runtime; }
    public List<GenreDto> getGenres() { return genres; }
}
