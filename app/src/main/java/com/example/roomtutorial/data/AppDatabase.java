package com.example.roomtutorial.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomtutorial.data.dao.BarangDao;
import com.example.roomtutorial.data.entity.Barang;

@Database(entities = {Barang.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BarangDao barangDao();
}
