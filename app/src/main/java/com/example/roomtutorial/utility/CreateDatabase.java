package com.example.roomtutorial.utility;

import android.content.Context;

import androidx.room.Room;

import com.example.roomtutorial.data.AppDatabase;

public class CreateDatabase {
    public static AppDatabase createDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "db_barang").build();
    }
}
