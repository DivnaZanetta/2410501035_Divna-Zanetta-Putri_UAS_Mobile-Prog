package com.example.endemikdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.endemikdb.DetailActivity;
import com.example.endemikdb.adapter.FavoritAdapter;
import com.example.endemikdb.database.AppDatabase;
import com.example.endemikdb.databinding.ActivityFavoritBinding;

public class FavoritActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFavoritBinding binding = ActivityFavoritBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        FavoritAdapter adapter = new FavoritAdapter(item -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("ID", item.getId());
            startActivity(intent);
        });

        binding.rvFavorit.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvFavorit.setAdapter(adapter);

        AppDatabase.getInstance(this).endemikDao()
                .getAllFavorit()
                .observe(this, list -> {
                    adapter.updateData(list);
                    binding.tvEmpty.setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);
                });
    }
}