package com.example.roomtutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomtutorial.data.AppDatabase;
import com.example.roomtutorial.data.entity.Barang;
import com.example.roomtutorial.utility.CreateDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddBarangActivity extends AppCompatActivity {

    private EditText tvNama;
    private EditText tvHarga;
    private Button btnSave;

    private AppDatabase appDatabase;

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barang);

        // untuk menampilkan tombol back di toolbar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appDatabase = CreateDatabase.createDatabase(this);

        tvNama = findViewById(R.id.tvNama);
        tvHarga = findViewById(R.id.tvHarga);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            if (TextUtils.isEmpty(tvNama.getText()) || TextUtils.isEmpty(tvHarga.getText())) {
                Toast.makeText(this, "Nama dan Harga wajib diisi!", Toast.LENGTH_SHORT).show();
            } else {
                saveBarang();
            }
        });
    }

    private void saveBarang() {
        executor.execute(() -> {
            // menyimpan data ke database di executor (background)
            Barang newBarang = new Barang();
            newBarang.setNama(tvNama.getText().toString());
            newBarang.setHarga(Double.parseDouble(tvHarga.getText().toString()));
            appDatabase.barangDao().insertAll(newBarang);

            // menampilkan Toast di main thread.
            handler.post(() -> {
                Toast.makeText(this, "Berhasil simpan data", Toast.LENGTH_SHORT).show();
                tvNama.setText("");
                tvHarga.setText("");
            });
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // untuk menambahkan action kembali ke halaman sebelumnya.
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}