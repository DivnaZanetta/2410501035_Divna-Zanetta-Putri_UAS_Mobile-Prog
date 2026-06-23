package com.example.endemikdb;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.endemikdb.databinding.ActivityTentangBinding;

public class TentangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTentangBinding binding = ActivityTentangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }
}