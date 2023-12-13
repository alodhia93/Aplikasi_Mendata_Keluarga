package com.mfrido.aplikasimendatakeluarga;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mfrido.aplikasimendatakeluarga.adapter.Adapter;
import com.mfrido.aplikasimendatakeluarga.helper.DbHelper;
import com.mfrido.aplikasimendatakeluarga.model.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FormEntry extends AppCompatActivity implements LocationListener {

    List<Data> itemList = new ArrayList<>();
    Adapter adapter;
    DbHelper SQLite = new DbHelper(this);
    Button btn_submit, btn_cancel, btn_alamat;

    EditText txt_id, txt_nik, txt_nama, txt_nohp, txt_jk, txt_tgl;
    TextView txt_alamat;
    LocationManager locationManager;
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
        btn_alamat = findViewById(R.id.buttonAlamat);

        if (ContextCompat.checkSelfPermission(FormEntry.this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(FormEntry.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }

        btn_alamat.setOnClickListener(view -> getLocation());

        btn_submit.setOnClickListener(v -> {
            try {
                if (txt_id.getText().toString().equals("Data Saved")) {
                    save();
                }
            } catch (Exception e){
                Log.e("Submit", e.toString());
            }
        });
        btn_cancel.setOnClickListener(v -> {
            blank();
            finish();
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000, 5, FormEntry.this);
        }catch (Exception e){
            e.printStackTrace();
        }
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

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, ""+location.getLatitude()+", "+ location.getLongitude(), Toast.LENGTH_SHORT).show();

        try {

            Geocoder geocoder = new Geocoder(FormEntry.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
            txt_alamat.setText(address);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}