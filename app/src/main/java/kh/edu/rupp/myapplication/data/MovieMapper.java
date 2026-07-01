package kh.edu.rupp.myapplication.data;

import java.util.ArrayList;
import java.util.List;
import kh.edu.rupp.myapplication.api.GenreDto;
import kh.edu.rupp.myapplication.api.MovieDetailsDto;
import kh.edu.rupp.myapplication.api.MovieDto;
import kh.edu.rupp.myapplication.db.MovieEntity;
import kh.edu.rupp.myapplication.models.Movie;

public final class MovieMapper {
    private MovieMapper() {
    }

    public static MovieEntity toEntity(Movie movie) {
        return new MovieEntity(
                movie.getId(),
                movie.getTitle(),
                movie.getPosterUrl(),
                movie.getYear(),
                parseRating(movie.getRating()),
                movie.getDescription(),
                movie.getDuration(),
                movie.getAgeRating(),
                movie.getPosterResId()
        );
    }

    public static Movie toMovie(MovieEntity entity) {
        return new Movie(
                entity.getId(),
                entity.getTitle(),
                entity.getReleaseDate(),
                entity.getDuration(),
                String.valueOf(entity.getVoteAverage()),
                entity.getAgeRating(),
                entity.getOverview(),
                entity.getPosterResId(),
                entity.getPosterPath()
        );
    }

    public static List<Movie> fromDtoList(List<MovieDto> dtos) {
        List<Movie> movies = new ArrayList<>();
        if (dtos == null) {
            return movies;
        }
        for (MovieDto dto : dtos) {
            movies.add(fromDto(dto));
        }
        return movies;
    }

    public static Movie fromDto(MovieDto dto) {
        String releaseDate = valueOrDefault(dto.getReleaseDate(), "Unknown");
        String year = releaseDate.length() >= 4 ? releaseDate.substring(0, 4) : releaseDate;
        return new Movie(
                dto.getId(),
                valueOrDefault(dto.getTitle(), "Untitled"),
                year,
                "N/A",
                String.format("%.1f", dto.getVoteAverage()),
                "NR",
                valueOrDefault(dto.getOverview(), "No description available."),
                0,
                buildPosterUrl(dto.getPosterPath())
        );
    }

    public static Movie fromDetailsDto(MovieDetailsDto dto) {
        String releaseDate = valueOrDefault(dto.getReleaseDate(), "Unknown");
        String year = releaseDate.length() >= 4 ? releaseDate.substring(0, 4) : releaseDate;
        return new Movie(
                dto.getId(),
                valueOrDefault(dto.getTitle(), "Untitled"),
                year,
                formatRuntime(dto.getRuntime()),
                String.format("%.1f", dto.getVoteAverage()),
                formatGenres(dto.getGenres()),
                valueOrDefault(dto.getOverview(), "No description available."),
                0,
                buildPosterUrl(dto.getBackdropPath() != null ? dto.getBackdropPath() : dto.getPosterPath())
        );
    }

    private static double parseRating(String rating) {
        try {
            return Double.parseDouble(rating);
        } catch (NumberFormatException ignored) {
            return 0.0;
        }
    }

    private static String buildPosterUrl(String posterPath) {
        if (posterPath == null || posterPath.isEmpty()) {
            return null;
        }
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }

    private static String formatRuntime(int runtime) {
        if (runtime <= 0) {
            return "N/A";
        }
        int hours = runtime / 60;
        int minutes = runtime % 60;
        return hours > 0 ? hours + "h " + minutes + "m" : minutes + "m";
    }

    private static String formatGenres(List<GenreDto> genres) {
        if (genres == null || genres.isEmpty()) {
            return "NR";
        }
        List<String> names = new ArrayList<>();
        for (int i = 0; i < genres.size() && i < 3; i++) {
            names.add(genres.get(i).getName());
        }
        return String.join(", ", names);
    }

    private static String valueOrDefault(String value, String fallback) {
        return value == null || value.trim().isEmpty() ? fallback : value;
    }
}
