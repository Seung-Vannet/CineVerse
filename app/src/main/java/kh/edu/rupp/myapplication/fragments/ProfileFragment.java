package kh.edu.rupp.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import kh.edu.rupp.myapplication.R;
import kh.edu.rupp.myapplication.databinding.FragmentProfileBinding;
import kh.edu.rupp.myapplication.databinding.ItemProfileMenuBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupMenuItems();
        
        binding.btnLogout.setOnClickListener(v -> 
            Toast.makeText(getContext(), "Logging out...", Toast.LENGTH_SHORT).show()
        );
    }

    private void setupMenuItems() {
        setupMenu(binding.menuMyList.getRoot(), "My List", android.R.drawable.ic_menu_agenda);
        setupMenu(binding.menuDownloads.getRoot(), "Downloads", android.R.drawable.stat_sys_download_done);
        setupMenu(binding.menuContinueWatching.getRoot(), "Continue Watching", android.R.drawable.ic_menu_recent_history);
        setupMenu(binding.menuWatchHistory.getRoot(), "Watch History", android.R.drawable.ic_menu_view);
        setupMenu(binding.menuSettings.getRoot(), "Settings", android.R.drawable.ic_menu_preferences);
        setupMenu(binding.menuHelp.getRoot(), "Help & Support", android.R.drawable.ic_menu_help);
    }

    private void setupMenu(View view, String title, int iconRes) {
        ItemProfileMenuBinding menuBinding = ItemProfileMenuBinding.bind(view);
        menuBinding.tvMenuTitle.setText(title);
        menuBinding.ivMenuIcon.setImageResource(iconRes);
        view.setOnClickListener(v -> 
            Toast.makeText(getContext(), title + " clicked", Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
