package kh.edu.rupp.myapplication.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kh.edu.rupp.myapplication.R;
import kh.edu.rupp.myapplication.models.Movie;

public final class MovieCatalog {
    private static final Movie DUNE = new Movie(1, "Dune: Part Two", "2024", "2h 46m", "8.6", "PG-13",
            "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
            R.drawable.dunetwo, null);
    private static final Movie OPPENHEIMER = new Movie(2, "Oppenheimer", "2023", "3h 0m", "8.4", "R",
            "The story of American scientist J. Robert Oppenheimer and the Manhattan Project.",
            R.drawable.oppenheimer, null);
    private static final Movie BATMAN = new Movie(3, "The Batman", "2022", "2h 56m", "8.1", "PG-13",
            "Batman ventures into Gotham City's underworld when a sadistic killer leaves cryptic clues.",
            R.drawable.thebatman, null);
    private static final Movie AVATAR = new Movie(4, "Avatar: The Way of Water", "2022", "3h 12m", "7.6", "PG-13",
            "Jake Sully lives with his family on Pandora and faces a renewed threat.",
            R.drawable.avatartwo, null);
    private static final Movie SPIDER_MAN = new Movie(5, "Spider-Man: Across the Spider-Verse", "2023", "2h 20m", "8.2", "PG",
            "Miles Morales travels across the Multiverse and meets a team of Spider-People.",
            R.drawable.spiderman, null);
    private static final Movie JOHN_WICK = new Movie(6, "John Wick: Chapter 4", "2023", "2h 49m", "8.1", "R",
            "John Wick uncovers a path to defeating The High Table.",
            R.drawable.johnwickfour, null);
    private static final Movie INTERSTELLAR = new Movie(7, "Interstellar", "2014", "2h 49m", "8.7", "PG-13",
            "A team of explorers travel through a wormhole to ensure humanity's survival.",
            R.drawable.interstellar, null);
    private static final Movie INCEPTION = new Movie(8, "Inception", "2010", "2h 28m", "8.8", "PG-13",
            "A thief who steals secrets through dream-sharing is given a chance to erase his past.",
            R.drawable.inception, null);
    private static final Movie MATRIX = new Movie(9, "The Matrix", "1999", "2h 16m", "8.7", "R",
            "A hacker discovers the hidden truth about his reality.",
            R.drawable.thematrix, null);

    private MovieCatalog() {
    }

    public static Movie heroMovie() {
        return DUNE;
    }

    public static List<Movie> continueWatching() {
        return immutableList(OPPENHEIMER, BATMAN, AVATAR);
    }

    public static List<Movie> trending() {
        return immutableList(SPIDER_MAN, JOHN_WICK, INTERSTELLAR);
    }

    public static List<Movie> newReleases() {
        return immutableList(DUNE, AVATAR, SPIDER_MAN);
    }

    public static List<Movie> topRated() {
        return immutableList(JOHN_WICK, INTERSTELLAR, BATMAN);
    }

    public static List<Movie> watchlistSeed() {
        return immutableList(INCEPTION, MATRIX, INTERSTELLAR);
    }

    public static List<Movie> searchResults() {
        return immutableList(DUNE, BATMAN, OPPENHEIMER, JOHN_WICK);
    }

    public static List<Movie> similarMovies() {
        return immutableList(BATMAN, INTERSTELLAR);
    }

    public static List<Movie> actionMovies() {
        return immutableList(JOHN_WICK, SPIDER_MAN, BATMAN);
    }

    public static List<Movie> comedyMovies() {
        return immutableList(SPIDER_MAN, AVATAR, DUNE);
    }

    public static List<Movie> horrorMovies() {
        return immutableList(INTERSTELLAR, MATRIX, BATMAN);
    }

    public static List<Movie> romanceMovies() {
        return immutableList(AVATAR, INCEPTION, DUNE);
    }

    public static List<Movie> sciFiMovies() {
        return immutableList(DUNE, INTERSTELLAR, MATRIX);
    }

    private static List<Movie> immutableList(Movie... movies) {
        List<Movie> list = new ArrayList<>();
        Collections.addAll(list, movies);
        return Collections.unmodifiableList(list);
    }
}
