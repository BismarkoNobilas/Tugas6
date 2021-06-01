package com.example.tugas6.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "data_db")
public class DataData {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "tanggal")
    private String tanggal;
    @ColumnInfo(name = "kotor")
    private int kotor;
    @ColumnInfo(name = "pengeluaran")
    private int pengeluaran;
    @ColumnInfo(name = "bersih")
    private int bersih;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getKotor() {
        return kotor;
    }

    public void setKotor(int kotor) {
        this.kotor = kotor;
    }

    public int getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(int pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public int getBersih() {
        return bersih;
    }

    public void setBersih(int bersih) {
        this.bersih = bersih;
    }
}
