@file:Suppress("NAME_SHADOWING")

package com.mfrido.aplikasimendatakeluarga.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.mfrido.aplikasimendatakeluarga.R
import com.mfrido.aplikasimendatakeluarga.model.Data

class Adapter(private val activity: Activity, private val items: List<Data>) : BaseAdapter() {
    private var inflater: LayoutInflater? = null
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(location: Int): Any {
        return items[location]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        val convertView = convertView
        if (inflater == null) inflater = activity
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val id = convertView.findViewById<TextView>(R.id.id_akun)
        val nik = convertView.findViewById<TextView>(R.id.nik)
        val nama = convertView.findViewById<TextView>(R.id.nama)
        val nohp = convertView.findViewById<TextView>(R.id.nohp)
        val jenisKelamin = convertView.findViewById<TextView>(R.id.jk)
        val tanggalSensus = convertView.findViewById<TextView>(R.id.tgl_data)
        val alamat = convertView.findViewById<TextView>(R.id.alamat)
        val data = items[position]
        id.text= data.id
        nik.text = data.nik
        nama.text = data.nama
        nohp.text = data.nohp
        jenisKelamin.text = data.jenisKelamin
        tanggalSensus.text = data.tglSensus
        alamat.text = data.alamat
        return convertView
    }
}
