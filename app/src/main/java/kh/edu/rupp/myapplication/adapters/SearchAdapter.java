package kh.edu.rupp.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import kh.edu.rupp.myapplication.databinding.ItemSearchMovieBinding;
import kh.edu.rupp.myapplication.models.Movie;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final List<Movie> movies;
    private final OnSearchItemClickListener listener;

    public interface OnSearchItemClickListener {
        void onSearchItemClick(Movie movie);
    }

    public SearchAdapter(List<Movie> movies, OnSearchItemClickListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSearchMovieBinding binding = ItemSearchMovieBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.binding.tvTitle.setText(movie.getTitle());
        holder.binding.tvYear.setText(movie.getYear());
        holder.binding.ivPoster.setImageResource(android.R.color.darker_gray); // Placeholder

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSearchItemClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemSearchMovieBinding binding;

        public ViewHolder(ItemSearchMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
