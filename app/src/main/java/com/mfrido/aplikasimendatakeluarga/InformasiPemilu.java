package com.mfrido.aplikasimendatakeluarga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class InformasiPemilu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_pemilu);
        WebView webView = findViewById(R.id.webViewPemilu);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://infopemilu.kpu.go.id/Pemilu/Peserta_pemilu");
    }
}