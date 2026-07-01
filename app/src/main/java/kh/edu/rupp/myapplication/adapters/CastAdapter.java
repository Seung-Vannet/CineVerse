package kh.edu.rupp.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import kh.edu.rupp.myapplication.R;
import kh.edu.rupp.myapplication.databinding.ItemCastBinding;
import kh.edu.rupp.myapplication.models.CastMember;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {
    private final List<CastMember> castMembers;

    public CastAdapter(List<CastMember> castMembers) {
        this.castMembers = castMembers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCastBinding binding = ItemCastBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CastMember castMember = castMembers.get(position);
        holder.binding.tvCastName.setText(castMember.getName());
        Glide.with(holder.binding.ivCastImage)
                .load(castMember.getProfileUrl())
                .placeholder(R.drawable.profile)
                .error(R.drawable.profile)
                .centerCrop()
                .into(holder.binding.ivCastImage);
    }

    @Override
    public int getItemCount() {
        return castMembers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemCastBinding binding;

        public ViewHolder(ItemCastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
