package kh.edu.rupp.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import kh.edu.rupp.myapplication.databinding.ItemGenreBinding;
import kh.edu.rupp.myapplication.models.Genre;
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private final List<Genre> genres;

    public GenreAdapter(List<Genre> genres) {
        this.genres = genres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGenreBinding binding = ItemGenreBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre genre = genres.get(position);
        holder.binding.tvGenreName.setText(genre.getName());
        holder.binding.ivGenreIcon.setImageResource(genre.getIconResId());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemGenreBinding binding;

        public ViewHolder(ItemGenreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
