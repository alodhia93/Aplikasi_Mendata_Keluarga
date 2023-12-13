package com.mfrido.aplikasimendatakeluarga

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.mfrido.aplikasimendatakeluarga.adapter.Adapter
import com.mfrido.aplikasimendatakeluarga.helper.DbHelper
import com.mfrido.aplikasimendatakeluarga.model.Data

class DataPemilih : AppCompatActivity() {
    private lateinit var listView: ListView
    private var sqlite = DbHelper(this)
    private var adapterView: Adapter? = null
    private var itemList: MutableList<Data> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_pemilih)
        listView = findViewById(R.id.list_view)
        adapterView = Adapter(this@DataPemilih, itemList)
        listView.adapter = adapterView
        allData
    }

    private val allData: Unit
        get() {
            val row = sqlite.allData
            for (i in row.indices) {
                val id = row[i][TAG_ID]
                val nik = row[i][TAG_NIK]
                val nama = row[i][TAG_NAMA]
                val nohp = row[i][TAG_NOHP]
                val jk = row[i][TAG_JENIS_KELAMIN]
                val tgl = row[i][TAG_TANGGAL]
                val alamat = row[i][TAG_ALAMAT]
                val data = Data()
                data.id = id
                data.nik = nik
                data.nama = nama
                data.nohp = nohp
                data.jenisKelamin = jk
                data.tglSensus = tgl
                data.alamat = alamat
                itemList.add(data)
            }
            adapterView!!.notifyDataSetChanged()
        }

    companion object {
        const val TAG_ID = "id"
        const val TAG_NIK = "nik"
        const val TAG_NAMA = "nama"
        const val TAG_NOHP = "nohp"
        const val TAG_JENIS_KELAMIN = "jenis_kelamin"
        const val TAG_TANGGAL = "tgl_sensus"
        const val TAG_ALAMAT = "alamat"
    }
}