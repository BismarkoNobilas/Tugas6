package com.example.tugas6.view;

import android.view.View;

import com.example.tugas6.entity.AppDatabase;
import com.example.tugas6.entity.DataData;

import java.util.List;

public interface MainContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataData> list);
        void editData(DataData item);
        void deleteData(DataData item);
    }

    interface presenter{
        void insertData(String tanggal, int kotor, int pengeluaran, int bersih, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String tanggal, int kotor, int pengeluaran, int bersih, AppDatabase database);
        void deleteData(DataData dataData, AppDatabase database);
    }
}
