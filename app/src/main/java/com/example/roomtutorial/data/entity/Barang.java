package com.example.roomtutorial.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "barang")
public class Barang {
    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name = "nama")
    String nama;

    @ColumnInfo(name = "harga")
    double harga;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
