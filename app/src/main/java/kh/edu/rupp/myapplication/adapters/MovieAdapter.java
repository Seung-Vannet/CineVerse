package kh.edu.rupp.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import kh.edu.rupp.myapplication.databinding.ItemMovieHorizontalBinding;
import kh.edu.rupp.myapplication.models.Movie;
import kh.edu.rupp.myapplication.utils.MovieImageLoader;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final List<Movie> movies;
    private final boolean showProgress;
    private OnMovieClickListener listener;

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    public MovieAdapter(List<Movie> movies, boolean showProgress, OnMovieClickListener listener) {
        this.movies = movies;
        this.showProgress = showProgress;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieHorizontalBinding binding = ItemMovieHorizontalBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.binding.tvTitle.setText(movie.getTitle());
        holder.binding.tvSubtitle.setText(movie.getYear() + " • " + movie.getDuration());
        
        if (showProgress) {
            holder.binding.progressIndicator.setVisibility(View.VISIBLE);
            holder.binding.progressIndicator.setProgress(70);
        } else {
            holder.binding.progressIndicator.setVisibility(View.GONE);
        }
        
        MovieImageLoader.load(holder.binding.ivPoster, movie);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMovieClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemMovieHorizontalBinding binding;

        public ViewHolder(ItemMovieHorizontalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
