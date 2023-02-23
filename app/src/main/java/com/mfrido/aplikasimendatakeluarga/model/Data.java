package com.mfrido.aplikasimendatakeluarga.model;

public class Data {
    private String id, nik, nama, nohp, jenis_kelamin, tgl_sensus, alamat;

    public Data(){}

    public Data(String id, String nik, String nama, String nohp, String jenis_kelamin, String tgl_sensus, String alamat){
        this.id = id;
        this.nik = nik;
        this.nama = nama;
        this.nohp = nohp;
        this.jenis_kelamin = jenis_kelamin;
        this.tgl_sensus = tgl_sensus;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public String getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
    }

    public String getNohp() {
        return nohp;
    }
    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getTgl_sensus() {
        return tgl_sensus;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public void setTgl_sensus(String tgl_sensus) {
        this.tgl_sensus = tgl_sensus;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
