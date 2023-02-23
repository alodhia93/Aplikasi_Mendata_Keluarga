package com.mfrido.aplikasimendatakeluarga;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import com.mfrido.aplikasimendatakeluarga.helper.DbHelper;
import com.mfrido.aplikasimendatakeluarga.model.Data;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button informasiPemilu, dataPemilih, formEntry, exitNgab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        informasiPemilu = findViewById(R.id.buttonInformasi);
        dataPemilih = findViewById(R.id.buttonLihatData);
        formEntry = findViewById(R.id.buttonForm);
        exitNgab = findViewById(R.id.buttonKeluar);

        informasiPemilu.setOnClickListener(this);
        dataPemilih.setOnClickListener(this);
        formEntry.setOnClickListener(this);
        exitNgab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonInformasi:
                Intent informasi = new Intent(MainActivity.this, InformasiPemilu.class);
                startActivity(informasi);
                break;
            case R.id.buttonForm:
                Intent isiForm = new Intent(MainActivity.this, FormEntry.class);
                startActivity(isiForm);
                break;
            case R.id.buttonLihatData:
                Intent lihatData = new Intent(MainActivity.this, DataPemilih.class);
                startActivity(lihatData);
                break;
            case R.id.buttonKeluar:
                finish();
                break;
        }
    }
}