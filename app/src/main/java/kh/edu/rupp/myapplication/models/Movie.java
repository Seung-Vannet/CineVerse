package kh.edu.rupp.myapplication.models;

public class Movie {
    private String title;
    private String year;
    private String duration;
    private int posterResId;

    public Movie(String title, String year, String duration, int posterResId) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.posterResId = posterResId;
    }

    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getDuration() { return duration; }
    public int getPosterResId() { return posterResId; }
}
