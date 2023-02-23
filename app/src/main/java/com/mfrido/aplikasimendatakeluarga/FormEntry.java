package com.mfrido.aplikasimendatakeluarga;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mfrido.aplikasimendatakeluarga.adapter.Adapter;
import com.mfrido.aplikasimendatakeluarga.helper.DbHelper;
import com.mfrido.aplikasimendatakeluarga.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormEntry extends AppCompatActivity {

    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    DbHelper SQLite = new DbHelper(this);
    Button btn_submit, btn_cancel;

    EditText txt_id, txt_nik, txt_nama, txt_nohp, txt_jk, txt_tgl, txt_alamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_entry);
        SQLite = new DbHelper(getApplicationContext());

        adapter = new Adapter(FormEntry.this, itemList);
        txt_id = findViewById(R.id.txt_id);
        txt_nik = findViewById(R.id.txt_NIK);
        txt_nama = findViewById(R.id.txt_nama);
        txt_nohp = findViewById(R.id.txt_nohp);
        txt_jk = findViewById(R.id.jenis_kelamin);
        txt_tgl = findViewById(R.id.tgl_sensus);
        txt_alamat = findViewById(R.id.alamat_tinggal);
        btn_submit = findViewById(R.id.buttonSubmit);
        btn_cancel = findViewById(R.id.buttonCancel);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {  @Override
        public void onClick(View v) {
            blank();
            finish();
        }
        });
    }

    private void blank() {
        txt_nama.requestFocus();
        txt_nik.requestFocus();
        txt_id.setText(null);
        txt_nohp.setText(null);
        txt_jk.setText(null);
        txt_tgl.setText(null);
        txt_alamat.setText(null);
    }
    private void save() {
        if (String.valueOf(txt_nik.getText()).equals(null)
                || String.valueOf(txt_nik.getText()).equals("")
                ||  String.valueOf(txt_nama.getText()).equals(null)
                || String.valueOf(txt_nama.getText()).equals("")
                ||  String.valueOf(txt_nohp.getText()).equals(null)
                || String.valueOf(txt_nohp.getText()).equals("")
                ||  String.valueOf(txt_jk.getText()).equals(null)
                || String.valueOf(txt_jk.getText()).equals("")
                || String.valueOf(txt_tgl.getText()).equals(null)
                || String.valueOf(txt_tgl.getText()).equals("")
                || String.valueOf(txt_alamat.getText()).equals(null)
                || String.valueOf(txt_alamat.getText()).equals(""))
        {  Toast.makeText(getApplicationContext(),
                "Silahkan lengkapi data yang kosong", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_nik.getText().toString().trim(),
                    txt_nama.getText().toString().trim(),
                    txt_nohp.getText().toString().trim(),
                    txt_jk.getText().toString().trim(),
                    txt_tgl.getText().toString().trim(),
                    txt_alamat.getText().toString().trim());
            blank();
            finish();
        }
    }

}