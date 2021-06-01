package com.example.tugas6.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDataDAO {
    @Insert
    Long insertData(DataData dataData);

    @Query("Select * from data_db")
    List<DataData> getData();

    @Update
    int updateData(DataData item);

    @Delete
    void deleteData(DataData item);

}
