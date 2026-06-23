package com.example.endemikdb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.endemikdb.DetailActivity;
import com.example.endemikdb.R;
import com.example.endemikdb.adapter.EndemikAdapter;
import com.example.endemikdb.database.AppDatabase;

public class HewanFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hewan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.rvEndemik);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        rv.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        EndemikAdapter adapter = new EndemikAdapter(item -> {
            Intent intent = new Intent(requireContext(), DetailActivity.class);
            intent.putExtra("ID", item.getId());
            startActivity(intent);
        });

        rv.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);

        AppDatabase.getInstance(requireContext())
                .endemikDao()
                .getHewan()
                .observe(getViewLifecycleOwner(), list -> {
                    progressBar.setVisibility(View.GONE);
                    adapter.updateData(list);
                });
    }
}