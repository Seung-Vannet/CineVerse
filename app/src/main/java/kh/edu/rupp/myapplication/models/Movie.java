package kh.edu.rupp.myapplication.models;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String year;
    private String duration;
    private String rating;
    private String ageRating;
    private String description;
    private int posterResId;

    public Movie(String title, String year, String duration, String rating, String ageRating, String description, int posterResId) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.rating = rating;
        this.ageRating = ageRating;
        this.description = description;
        this.posterResId = posterResId;
    }

    // Constructor for backwards compatibility
    public Movie(String title, String year, String duration, int posterResId) {
        this(title, year, duration, "8.0", "PG-13", "No description available.", posterResId);
    }

    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getDuration() { return duration; }
    public String getRating() { return rating; }
    public String getAgeRating() { return ageRating; }
    public String getDescription() { return description; }
    public int getPosterResId() { return posterResId; }
}
