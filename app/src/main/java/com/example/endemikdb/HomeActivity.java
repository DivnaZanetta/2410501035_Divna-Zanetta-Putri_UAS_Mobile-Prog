package com.example.endemikdb;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.endemikdb.databinding.ActivityHomeBinding;
import com.example.endemikdb.ui.HewanFragment;
import com.example.endemikdb.ui.TumbuhanFragment;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private boolean isHewan = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.inflateMenu(R.menu.menu_toolbar);

        showFragment(true);

        binding.bottomAppBar.setNavigationOnClickListener(v -> {
            isHewan = !isHewan;
            showFragment(isHewan);
        });

        binding.bottomAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.nav_pencarian) {
                startActivity(new Intent(this, PencarianActivity.class));
                return true;
            } else if (item.getItemId() == R.id.nav_favorit) {
                startActivity(new Intent(this, FavoritActivity.class));
                return true;
            }
            return false;
        });

        binding.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_toggle_theme) {
                int current = AppCompatDelegate.getDefaultNightMode();
                if (current == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                return true;
            } else if (item.getItemId() == R.id.menu_tentang) {
                startActivity(new Intent(this, TentangActivity.class));
                return true;
            }
            return false;
        });
    }

    private void showFragment(boolean hewan) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer,
                        hewan ? new HewanFragment() : new TumbuhanFragment())
                .commit();
        binding.toolbar.setTitle(hewan ? "Hewan Endemik" : "Tumbuhan Endemik");
    }
}