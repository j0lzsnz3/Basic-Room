package com.example.roomtutorial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomtutorial.data.entity.Barang;

import java.util.ArrayList;
import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder> {

    private List<Barang> barangs = new ArrayList<>();

    void setBarangs(List<Barang> barangs) {
        this.barangs = barangs;
        notifyDataSetChanged();
    }

    void clearData() {
        barangs.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_barang, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        holder.setView(barangs.get(position));
    }

    @Override
    public int getItemCount() {
        return barangs.size();
    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {

        private final TextView nama;
        private final TextView harga;

        public BarangViewHolder(View view) {
            super(view);
            nama = view.findViewById(R.id.lblNama);
            harga = view.findViewById(R.id.lblHarga);
        }

        void setView(Barang barang) {
            nama.setText(barang.getNama());
            harga.setText("Rp. " + barang.getHarga());
        }
    }

}
