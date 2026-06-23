package com.example.endemikdb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import com.example.endemikdb.api.ApiClient;
import com.example.endemikdb.database.AppDatabase;
import com.example.endemikdb.database.EndemikDao;
import com.example.endemikdb.model.Endemik;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            EndemikDao dao = db.endemikDao();
            int count = dao.count();

            if (count == 0) {
                fetchFromApi(dao);
            } else {
                goToHomeDelayed();
            }
        }).start();
    }

    private void fetchFromApi(EndemikDao dao) {
        ApiClient.getInstance().getEndemik().enqueue(new Callback<List<Endemik>>() {
            @Override
            public void onResponse(Call<List<Endemik>> call, Response<List<Endemik>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    new Thread(() -> {
                        dao.insertAll(response.body());
                        goToHomeDelayed();
                    }).start();
                } else {
                    goToHomeDelayed();
                }
            }

            @Override
            public void onFailure(Call<List<Endemik>> call, Throwable t) {
                goToHomeDelayed();
            }
        });
    }

    private void goToHomeDelayed() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();
        }, 2000);
    }
}