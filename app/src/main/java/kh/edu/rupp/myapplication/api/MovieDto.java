package kh.edu.rupp.myapplication.api;

import com.google.gson.annotations.SerializedName;

public class MovieDto {
    @SerializedName("id")
    private int id;
    
    @SerializedName("title")
    private String title;
    
    @SerializedName("poster_path")
    private String posterPath;
    
    @SerializedName("release_date")
    private String releaseDate;
    
    @SerializedName("vote_average")
    private double voteAverage;
    
    @SerializedName("overview")
    private String overview;

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getPosterPath() { return posterPath; }
    public String getReleaseDate() { return releaseDate; }
    public double getVoteAverage() { return voteAverage; }
    public String getOverview() { return overview; }
}
