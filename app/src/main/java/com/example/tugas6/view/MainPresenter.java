package com.example.tugas6.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tugas6.entity.AppDatabase;
import com.example.tugas6.entity.DataData;

import java.util.List;

public class MainPresenter implements MainContact.presenter {
    private MainContact.view view;

    public MainPresenter(MainContact.view view){
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataData dataData;

        public InsertData(AppDatabase appDatabase, DataData dataData){
            this.appDatabase = appDatabase;
            this.dataData = dataData;
        }

        @Override
        protected Long doInBackground(Void... voids){
            return appDatabase.dao().insertData(dataData);
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();

        }
    }
    @Override
    public void insertData(String tanggal, int kotor, int pengeluaran, int bersih, AppDatabase database) {
        final  DataData dataData = new DataData();
        dataData.setTanggal(tanggal);
        dataData.setKotor(kotor);
        dataData.setPengeluaran(pengeluaran);
        dataData.setBersih(bersih);
        new InsertData(database,dataData).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataData> list;
        list = database.dao().getData();
        view.getData(list);
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase appDatabase;
        private DataData dataData;

        public EditData(AppDatabase appDatabase, DataData dataData) {
            this.appDatabase = appDatabase;
            this.dataData = dataData;
        }

        @Override
        protected Integer doInBackground(Void... voids){
            return appDatabase.dao().updateData(dataData);
        }

        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute : "+integer);
            view.successAdd();

        }
    }
    @Override
    public void editData(String tanggal, int kotor, int pengeluaran, int bersih, AppDatabase database) {
        final  DataData dataData = new DataData();
        dataData.setTanggal(tanggal);
        dataData.setKotor(kotor);
        dataData.setPengeluaran(pengeluaran);
        dataData.setBersih(bersih);
        new EditData(database,dataData).execute();
    }

    class DeleteData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataData dataData;

        public DeleteData(AppDatabase appDatabase, DataData dataData) {
            this.appDatabase = appDatabase;
            this.dataData = dataData;
        }

        @Override
        protected Long doInBackground(Void... voids){
            appDatabase.dao().deleteData(dataData);
            return null;
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successDelete();
        }
    }
    @Override
    public void deleteData(DataData dataData, AppDatabase database) {
        new DeleteData(database,dataData).execute();
    }
}
