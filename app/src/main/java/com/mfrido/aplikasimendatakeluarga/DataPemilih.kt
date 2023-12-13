package com.mfrido.aplikasimendatakeluarga;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.mfrido.aplikasimendatakeluarga.adapter.Adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mfrido.aplikasimendatakeluarga.helper.DbHelper;
import com.mfrido.aplikasimendatakeluarga.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataPemilih extends AppCompatActivity {
    ListView listView;
    DbHelper SQLite = new DbHelper(this);
    Adapter adapterView;
    List<Data> itemList = new ArrayList<Data>();
    public static final String TAG_ID = "id";
    public static final String TAG_NIK = "nik";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_NOHP = "nohp";
    public static final String TAG_JENIS_KELAMIN = "jenis_kelamin";
    public static final String TAG_TANGGAL = "tgl_sensus";
    public static final String TAG_ALAMAT = "alamat";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pemilih);
        listView = findViewById(R.id.list_view);
        adapterView = new Adapter(DataPemilih.this, itemList);
        listView.setAdapter(adapterView);
        getAllData();
    }
    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();
        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String nik = row.get(i).get(TAG_NIK);
            String nama = row.get(i).get(TAG_NAMA);
            String nohp = row.get(i).get(TAG_NOHP);
            String jk = row.get(i).get(TAG_JENIS_KELAMIN);
            String tgl = row.get(i).get(TAG_TANGGAL);
            String alamat = row.get(i).get(TAG_ALAMAT);
            Data data = new Data();
            data.setId(id);
            data.setNik(nik);
            data.setNama(nama);
            data.setNohp(nohp);
            data.setJenis_kelamin(jk);
            data.setTgl_sensus(tgl);
            data.setAlamat(alamat);
            itemList.add(data);
        }
        adapterView.notifyDataSetChanged();
    }
}