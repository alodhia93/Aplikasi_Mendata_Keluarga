@file:Suppress("DEPRECATION")

package com.mfrido.aplikasimendatakeluarga

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mfrido.aplikasimendatakeluarga.adapter.Adapter
import com.mfrido.aplikasimendatakeluarga.helper.DbHelper
import com.mfrido.aplikasimendatakeluarga.model.Data
import java.util.Locale

class FormEntry : AppCompatActivity(), LocationListener {
    private var itemList: List<Data> = ArrayList()
    private var adapter: Adapter? = null
    private var sqlite = DbHelper(this)
    private lateinit var buttonSubmit: Button
    private lateinit var buttonCancel: Button
    private lateinit var buttonAlamat: Button
    private lateinit var id: EditText
    private var nik: EditText? = null
    private var nama: EditText? = null
    private lateinit var noHandphone: EditText
    private lateinit var jenisKelamin: EditText
    private lateinit var textTanggal: EditText
    private lateinit var textAlamat: TextView
    private var locationManager: LocationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_entry)
        sqlite = DbHelper(applicationContext)
        adapter = Adapter(this@FormEntry, itemList)
        id = findViewById(R.id.txt_id)
        nik = findViewById(R.id.txt_NIK)
        nama = findViewById(R.id.txt_nama)
        noHandphone = findViewById(R.id.txt_nohp)
        jenisKelamin = findViewById(R.id.jenis_kelamin)
        textTanggal = findViewById(R.id.tgl_sensus)
        textAlamat = findViewById(R.id.alamat_tinggal)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        buttonCancel = findViewById(R.id.buttonCancel)
        buttonAlamat = findViewById(R.id.buttonAlamat)
        if (ContextCompat.checkSelfPermission(
                this@FormEntry,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@FormEntry, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION
                ), 100
            )
        }
        buttonAlamat.setOnClickListener { location }
        buttonSubmit.setOnClickListener {
            try {
                if (id.text.toString() == "Data Saved") {
                    save()
                }
            } catch (e: Exception) {
                Log.e("Submit", e.toString())
            }
        }
        buttonCancel.setOnClickListener {
            blank()
            finish()
        }
    }

    @get:SuppressLint("MissingPermission")
    private val location: Unit
        get() {
            try {
                locationManager =
                    applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager
                locationManager!!.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    5f,
                    this@FormEntry
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    private fun blank() {
        nama!!.requestFocus()
        nik!!.requestFocus()
        id.text = null
        noHandphone.text = null
        jenisKelamin.text = null
        textTanggal.text = null
        textAlamat.text = null
    }

    private fun save() {
        if (nik!!.text.toString() == "" || nama!!.text.toString() == "" || noHandphone.text.toString() == "" || jenisKelamin.text.toString() == "" || textTanggal.text.toString() == "" || textAlamat.text.toString() == "") {
            Toast.makeText(
                applicationContext,
                "Silahkan lengkapi data yang kosong", Toast.LENGTH_SHORT
            ).show()
        } else {
            sqlite.insert(
                nik!!.text.toString().trim { it <= ' ' },
                nama!!.text.toString().trim { it <= ' ' },
                noHandphone.text.toString().trim { it <= ' ' },
                jenisKelamin.text.toString().trim { it <= ' ' },
                textTanggal.text.toString().trim { it <= ' ' },
                textAlamat.text.toString().trim { it <= ' ' })
            blank()
            finish()
        }
    }

    override fun onLocationChanged(location: Location) {
        Toast.makeText(this, "" + location.latitude + ", " + location.longitude, Toast.LENGTH_SHORT)
            .show()
        try {
            val geocoder = Geocoder(this@FormEntry, Locale.getDefault())
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            val address = addresses!![0].getAddressLine(0)
            textAlamat.text = address
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onStatusChanged(provider, status, extras)",
        "android.location.LocationListener"
    )
    )
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        super.onStatusChanged(provider, status, extras)
    }

}