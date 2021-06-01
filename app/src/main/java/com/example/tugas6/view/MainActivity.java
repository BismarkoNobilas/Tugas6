package com.example.tugas6.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas6.R;
import com.example.tugas6.entity.AppDatabase;
import com.example.tugas6.entity.DataData;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view{
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button btnOK;
    private RecyclerView recyclerView;
    private EditText etTanggal, etKotor, etPengeluaran;
    private int id = 0;
    private int etBersih;
    boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOK = findViewById(R.id.btn_submit);
        etTanggal = findViewById(R.id.et_tanggal);
        etKotor = findViewById(R.id.et_kotor);
        etPengeluaran = findViewById(R.id.et_pengeluaran);
        recyclerView = findViewById(R.id.rc_main);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {

        Toast.makeText(this, "Berhasil Menghapus Data", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etTanggal.setText("");
        etKotor.setText("");
        etPengeluaran.setText("");
        btnOK.setText("SUBMIT");
    }

    @Override
    public void getData(List<DataData> list) {
        mainAdapter = new MainAdapter(this,list,this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataData item) {
        etTanggal.setText(item.getTanggal());
        etKotor.setText(item.getKotor());
        etPengeluaran.setText(item.getPengeluaran());
        id = item.getId();
        edit = true;
        btnOK.setText("Edit Data");
    }

    @Override
    public void deleteData(DataData item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }else{
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        mainPresenter.deleteData(item,appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }

    @Override
    public void onClick(View v) {
        if(v==btnOK){
            if(etTanggal.getText().toString().equals("")||etKotor.getText().toString().equals("")||
                    etPengeluaran.getText().toString().equals("")){
                Toast.makeText(this, "Harap Semua Diisi", Toast.LENGTH_LONG).show();
            }else {
                if (!edit){
                    etBersih = Integer.parseInt(etKotor.getText().toString()) - Integer.parseInt(etPengeluaran.getText().toString());
                    mainPresenter.insertData(etTanggal.getText().toString(),Integer.parseInt(etKotor.getText().toString()),
                           Integer.parseInt(etPengeluaran.getText().toString()),etBersih,appDatabase);
                }else {
                    etBersih = Integer.parseInt(etKotor.getText().toString()) - Integer.parseInt(etPengeluaran.getText().toString());
                    mainPresenter.editData(etTanggal.getText().toString(),Integer.parseInt(etKotor.getText().toString()),
                            Integer.parseInt(etPengeluaran.getText().toString()),etBersih,appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }

    }
}