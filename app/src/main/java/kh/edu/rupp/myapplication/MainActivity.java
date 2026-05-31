package kh.edu.rupp.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import kh.edu.rupp.myapplication.databinding.ActivityMainBinding;
import kh.edu.rupp.myapplication.fragments.BrowseFragment;
import kh.edu.rupp.myapplication.fragments.HomeFragment;
import kh.edu.rupp.myapplication.fragments.ProfileFragment;
import kh.edu.rupp.myapplication.fragments.WatchlistFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Default to Home Page and highlight the Home icon
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            binding.bottomNavigation.setSelectedItemId(R.id.nav_home);
        }

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int itemId = item.getItemId();
            
            if (itemId == R.id.nav_home) {
                fragment = new HomeFragment();
            } else if (itemId == R.id.nav_browse) {
                fragment = new BrowseFragment();
            } else if (itemId == R.id.nav_watchlist) {
                fragment = new WatchlistFragment();
            } else if (itemId == R.id.nav_profile) {
                fragment = new ProfileFragment();
            }
            
            return loadFragment(fragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
