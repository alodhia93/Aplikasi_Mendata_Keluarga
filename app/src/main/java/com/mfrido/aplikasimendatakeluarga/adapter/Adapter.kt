package com.mfrido.aplikasimendatakeluarga.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfrido.aplikasimendatakeluarga.R;
import com.mfrido.aplikasimendatakeluarga.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private final Activity activity;
    private LayoutInflater inflater;
    private final List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_data_pemilih, null);
        TextView id = convertView.findViewById(R.id.id_akun);
        TextView nik = convertView.findViewById(R.id.nik);
        TextView nama = convertView.findViewById(R.id.nama);
        TextView nohp = convertView.findViewById(R.id.nohp);
        TextView jenis_kelamin = convertView.findViewById(R.id.jk);
        TextView tanggal_sensus = convertView.findViewById(R.id.tgl_data);
        TextView alamat = convertView.findViewById(R.id.alamat);
        Data data = items.get(position);
        id.setText(data.getId());
        nik.setText(data.getNik());
        nama.setText(data.getNama());
        nohp.setText(data.getNohp());
        jenis_kelamin.setText(data.getJenis_kelamin());
        tanggal_sensus.setText(data.getTgl_sensus());
        alamat.setText(data.getAlamat());
        return convertView;
    }
}
