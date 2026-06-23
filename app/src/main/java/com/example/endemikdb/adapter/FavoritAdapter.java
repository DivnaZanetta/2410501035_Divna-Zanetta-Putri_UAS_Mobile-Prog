package com.example.endemikdb.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.endemikdb.R;
import com.example.endemikdb.model.Favorit;
import java.util.ArrayList;
import java.util.List;

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onClick(Favorit item);
    }

    private List<Favorit> list = new ArrayList<>();
    private final OnItemClickListener listener;

    public FavoritAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoto;
        TextView tvNama, tvAsal, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto  = itemView.findViewById(R.id.imgFoto);
            tvNama   = itemView.findViewById(R.id.tvNama);
            tvAsal   = itemView.findViewById(R.id.tvAsal);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_endemik, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Favorit item = list.get(position);
        holder.tvNama.setText(item.getNama());
        holder.tvAsal.setText(item.getAsal());
        holder.tvStatus.setText(item.getStatus());
        Glide.with(holder.itemView.getContext())
                .load(item.getFoto())
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.imgFoto);
        holder.itemView.setOnClickListener(v -> listener.onClick(item));
    }

    @Override
    public int getItemCount() { return list.size(); }

    public void updateData(List<Favorit> newList) {
        list = newList;
        notifyDataSetChanged();
    }
}