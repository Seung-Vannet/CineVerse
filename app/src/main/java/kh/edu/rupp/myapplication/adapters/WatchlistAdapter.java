package kh.edu.rupp.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import kh.edu.rupp.myapplication.databinding.ItemMovieWatchlistBinding;
import kh.edu.rupp.myapplication.models.Movie;
import java.util.List;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.ViewHolder> {

    private final List<Movie> movies;

    public WatchlistAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieWatchlistBinding binding = ItemMovieWatchlistBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.binding.tvTitle.setText(movie.getTitle());
        holder.binding.tvInfo.setText(movie.getYear() + " • " + movie.getDuration());
        // For actual implementation, use Glide/Picasso to load images
        // holder.binding.ivPoster.setImageResource(movie.getPosterResId());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemMovieWatchlistBinding binding;

        public ViewHolder(ItemMovieWatchlistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
