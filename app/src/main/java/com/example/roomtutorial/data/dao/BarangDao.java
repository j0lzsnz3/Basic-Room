package com.example.roomtutorial.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomtutorial.data.entity.Barang;

import java.util.List;

@Dao
public interface BarangDao {
    @Insert
    void insertAll(Barang... barangs);

    @Query("SELECT * FROM barang")
    List<Barang> getAllBarangs();
}
