package com.example.roomtutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;

import com.example.roomtutorial.data.AppDatabase;
import com.example.roomtutorial.data.entity.Barang;
import com.example.roomtutorial.utility.CreateDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    /**
     * ExecutorService diperlukan untuk melakukan transaksi ke proses yang berat.
     * Pada case ini, yaitu melakukan transaksi ke Database supaya UI tidak ngehank.
     */
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    /**
     * Handler diperlukan untuk menampilkan data ke Layout
     */
    Handler handler = new Handler(Looper.getMainLooper());
    private AppDatabase appDatabase;

    private final BarangAdapter barangAdapter = new BarangAdapter();

    private RecyclerView rvBarangs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase = CreateDatabase.createDatabase(this);

        Button btnAddBarang = findViewById(R.id.btnAddBarang);
        rvBarangs = findViewById(R.id.rvBarangs);

        btnAddBarang.setOnClickListener(v -> {
            Intent addBarangIntent = new Intent(this, AddBarangActivity.class);
            startActivity(addBarangIntent);
        });

        setupRecyclerView();
        getAllBarang();
    }

    // setup
    private void setupRecyclerView() {
        rvBarangs.setLayoutManager(new LinearLayoutManager(this));
        rvBarangs.setAdapter(barangAdapter);
    }

    private void getAllBarang() {
        executorService.execute(() -> {
            // mendapatkan data ke Database. Proses ini jalan di executor (background)
            List<Barang> allBarang = appDatabase.barangDao().getAllBarangs();

            // tampilkan data ke RecyclerView. Proses ini jalan di main thread.
            handler.post(() -> barangAdapter.setBarangs(allBarang));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllBarang();
    }
}