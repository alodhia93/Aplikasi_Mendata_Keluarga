package com.mfrido.aplikasimendatakeluarga.helper

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val sqlCreateTable = "CREATE TABLE " +
                TABLE_SQLite + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NIK + " TEXT NOT NULL, " +
                COLUMN_NAMA + " TEXT NOT NULL, " +
                COLUMN_NOHP + " TEXT NOT NULL, " +
                COLUMN_JENIS_KELAMIN + " TEXT NOT NULL, " +
                COLUMN_TANGGAL + " TEXT NOT NULL, " +
                COLUMN_ALAMAT + " TEXT NOT NULL" +
                " )"
        sqLiteDatabase.execSQL(sqlCreateTable)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $TABLE_SQLite")
        onCreate(sqLiteDatabase)
    }

    val allData: ArrayList<HashMap<String, String>>
        @SuppressLint("Recycle")
        get() {
            val wordList: ArrayList<HashMap<String, String>> = ArrayList()
            val selectQuery = "SELECT * FROM $TABLE_SQLite"
            val database = this.writableDatabase
            val cursor = database.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val map = HashMap<String, String>()
                    map[COLUMN_ID] = cursor.getString(0)
                    map[COLUMN_NIK] = cursor.getString(1)
                    map[COLUMN_NAMA] = cursor.getString(2)
                    map[COLUMN_NOHP] = cursor.getString(3)
                    map[COLUMN_JENIS_KELAMIN] = cursor.getString(4)
                    map[COLUMN_TANGGAL] = cursor.getString(5)
                    map[COLUMN_ALAMAT] = cursor.getString(6)
                    wordList.add(map)
                } while (cursor.moveToNext())
            }
            Log.e("Pilih Data yang akan dilihat", "" + wordList)
            database.close()
            return wordList
        }

    fun insert(
        nik: String,
        nama: String,
        nohp: String,
        jenisKelamin: String,
        tglSensus: String,
        alamat: String
    ) {
        val database = this.writableDatabase
        val queryValues = "INSERT INTO " + TABLE_SQLite +
                " (nik, nama, nohp, jenis_kelamin, tgl_sensus, alamat) " +
                "VALUES ('" + nik + "', '" + nama +
                "', '" + nohp + "', '" + jenisKelamin +
                "', '" + tglSensus + "', '" + alamat + "')"
        Log.e("Masukkan Data", "" + queryValues)
        database.execSQL(queryValues)
        database.close()
    }

    companion object {
        private const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "datakeluargabaru2.db"
        const val TABLE_SQLite = "sqlite"
        const val COLUMN_ID = "id"
        const val COLUMN_NIK = "nik"
        const val COLUMN_NAMA = "nama"
        const val COLUMN_NOHP = "nohp"
        const val COLUMN_JENIS_KELAMIN = "jenis_kelamin"
        const val COLUMN_TANGGAL = "tgl_sensus"
        const val COLUMN_ALAMAT = "alamat"
    }
}
