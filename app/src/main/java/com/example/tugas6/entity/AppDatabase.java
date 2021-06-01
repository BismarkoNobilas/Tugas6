package com.example.tugas6.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataDataDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase inidb(Context context){
        if (appDatabase==null)
            appDatabase = Room.databaseBuilder(context,AppDatabase.class,"dbData").allowMainThreadQueries().build();
        return appDatabase;
    }
}
