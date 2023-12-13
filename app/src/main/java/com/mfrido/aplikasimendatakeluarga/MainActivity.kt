package com.mfrido.aplikasimendatakeluarga

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var informasiPemilu: Button
    private lateinit var dataPemilih: Button
    private lateinit var formEntry: Button
    private lateinit var exitNgab: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        informasiPemilu = findViewById(R.id.buttonInformasi)
        dataPemilih = findViewById(R.id.buttonLihatData)
        formEntry = findViewById(R.id.buttonForm)
        exitNgab = findViewById(R.id.buttonKeluar)
        informasiPemilu.setOnClickListener(this)
        dataPemilih.setOnClickListener(this)
        formEntry.setOnClickListener(this)
        exitNgab.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonInformasi -> {
                val informasi = Intent(this@MainActivity, InformasiPemilu::class.java)
                startActivity(informasi)
            }

            R.id.buttonForm -> {
                val isiForm = Intent(this@MainActivity, FormEntry::class.java)
                startActivity(isiForm)
            }

            R.id.buttonLihatData -> {
                val lihatData = Intent(this@MainActivity, DataPemilih::class.java)
                startActivity(lihatData)
            }

            R.id.buttonKeluar -> finish()
        }
    }
}