package kh.edu.rupp.myapplication.models;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String title;
    private String year;
    private String duration;
    private String rating;
    private String ageRating;
    private String description;
    private int posterResId;
    private String posterUrl;

    public Movie(int id, String title, String year, String duration, String rating, String ageRating, String description, int posterResId, String posterUrl) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.rating = rating;
        this.ageRating = ageRating;
        this.description = description;
        this.posterResId = posterResId;
        this.posterUrl = posterUrl;
    }

    // Existing constructor for backwards compatibility with drawable resources
    public Movie(String title, String year, String duration, String rating, String ageRating, String description, int posterResId) {
        this(0, title, year, duration, rating, ageRating, description, posterResId, null);
    }

    public Movie(String title, String year, String duration, int posterResId) {
        this(0, title, year, duration, "8.0", "PG-13", "No description available.", posterResId, null);
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getDuration() { return duration; }
    public String getRating() { return rating; }
    public String getAgeRating() { return ageRating; }
    public String getDescription() { return description; }
    public int getPosterResId() { return posterResId; }
    public String getPosterUrl() { return posterUrl; }
}
