package kh.edu.rupp.myapplication.utils;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import kh.edu.rupp.myapplication.R;
import kh.edu.rupp.myapplication.models.Movie;

public final class MovieImageLoader {
    private MovieImageLoader() {
    }

    public static void load(ImageView imageView, Movie movie) {
        if (movie.getPosterUrl() != null && !movie.getPosterUrl().isEmpty()) {
            Glide.with(imageView)
                    .load(movie.getPosterUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .centerCrop()
                    .into(imageView);
        } else if (movie.getPosterResId() != 0) {
            imageView.setImageResource(movie.getPosterResId());
        } else {
            imageView.setImageResource(R.drawable.ic_launcher_background);
        }
    }
}
