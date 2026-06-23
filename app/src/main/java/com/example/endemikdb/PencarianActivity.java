package com.example.endemikdb;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.endemikdb.DetailActivity;
import com.example.endemikdb.adapter.EndemikAdapter;
import com.example.endemikdb.database.AppDatabase;
import com.example.endemikdb.databinding.ActivityPencarianBinding;

public class PencarianActivity extends AppCompatActivity {

    private ActivityPencarianBinding binding;
    private EndemikAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPencarianBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        adapter = new EndemikAdapter(item -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("ID", item.getId());
            startActivity(intent);
        });

        binding.rvHasil.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvHasil.setAdapter(adapter);

        AppDatabase.getInstance(this).endemikDao()
                .search("").observe(this, list -> adapter.updateData(list));

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString().trim();
                AppDatabase.getInstance(PencarianActivity.this).endemikDao()
                        .search(keyword).observe(PencarianActivity.this,
                                list -> adapter.updateData(list));
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}