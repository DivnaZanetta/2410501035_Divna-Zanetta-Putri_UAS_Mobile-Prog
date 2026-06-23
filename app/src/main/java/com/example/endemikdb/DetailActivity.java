package com.example.endemikdb;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.endemikdb.R;
import com.example.endemikdb.database.EndemikDao;
import com.example.endemikdb.model.Endemik;
import com.example.endemikdb.model.Favorit;
import com.google.android.material.button.MaterialButton;
import com.example.endemikdb.database.AppDatabase;
import com.example.endemikdb.database.EndemikDao;
import com.example.endemikdb.model.Endemik;

public class DetailActivity extends AppCompatActivity {

    private EndemikDao dao;
    private String currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        currentId = getIntent().getStringExtra("ID");
        if (currentId == null) { finish(); return; }

        dao = AppDatabase.getInstance(this).endemikDao();

        com.google.android.material.appbar.MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Observe data endemik berdasarkan ID
        dao.getById(currentId).observe(this, item -> {
            if (item == null) return;
            bindData(item);
        });
    }

    private void bindData(Endemik item) {
        ((TextView) findViewById(R.id.tvNamaDetail)).setText(item.getNama());
        ((TextView) findViewById(R.id.tvNamaLatin)).setText(item.getNamaLatin());
        ((TextView) findViewById(R.id.tvStatus)).setText(item.getStatus());
        ((TextView) findViewById(R.id.tvDeskripsi)).setText(item.getDeskripsi());
        ((TextView) findViewById(R.id.tvAsal)).setText(item.getAsal());
        ((TextView) findViewById(R.id.tvSebaran)).setText(item.getSebaran());

        Glide.with(this)
                .load(item.getFoto())
                .placeholder(R.drawable.ic_placeholder)
                .into((ImageView) findViewById(R.id.imgDetail));

        MaterialButton btnFavorit = findViewById(R.id.btnFavorit);

        new Thread(() -> {
            boolean isFav = dao.isFavorit(currentId) > 0;
            runOnUiThread(() -> updateBtnFavorit(btnFavorit, isFav));
        }).start();

        btnFavorit.setOnClickListener(v -> {
            new Thread(() -> {
                boolean isFav = dao.isFavorit(currentId) > 0;
                if (isFav) {
                    dao.deleteFavorit(new Favorit(
                            item.getId(), item.getTipe(), item.getNama(),
                            item.getNamaLatin(), item.getAsal(),
                            item.getFoto(), item.getStatus()));
                    runOnUiThread(() -> updateBtnFavorit(btnFavorit, false));
                } else {
                    dao.insertFavorit(new Favorit(
                            item.getId(), item.getTipe(), item.getNama(),
                            item.getNamaLatin(), item.getAsal(),
                            item.getFoto(), item.getStatus()));
                    runOnUiThread(() -> updateBtnFavorit(btnFavorit, true));
                }
            }).start();
        });
    }

    private void updateBtnFavorit(MaterialButton btn, boolean isFav) {
        btn.setText(isFav ? "♥  Tersimpan di Favorit" : "♡  Simpan ke Favorit");
    }
}