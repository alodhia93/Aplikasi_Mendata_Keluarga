package com.mfrido.aplikasimendatakeluarga

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class InformasiPemilu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informasi_pemilu)
        val webView = findViewById<WebView>(R.id.webViewPemilu)
        webView.loadUrl("put link here")
    }
}