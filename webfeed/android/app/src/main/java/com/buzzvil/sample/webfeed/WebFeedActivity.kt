package com.buzzvil.sample.webfeed

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_webfeed.*

class WebFeedActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webfeed)

        webView.settings.javaScriptEnabled = true
        // Optional: Enable remote debug
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        // Enable Local Storage
        webView.settings.domStorageEnabled = true

        // To preserve data < KITKAT
        webView.settings.databaseEnabled = true
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.settings.databasePath =
                applicationContext.getDir("databases", Context.MODE_PRIVATE).path
        }

        webView.loadUrl(App.WEBFEED_URL)
    }
}