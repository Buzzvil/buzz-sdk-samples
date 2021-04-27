package com.buzzvil.sample.webfeed

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.sample.webfeed.App.Companion.WEBFEED_SERVER_URL
import kotlinx.android.synthetic.main.activity_webfeed.*

class WebFeedActivity : AppCompatActivity() {
    companion object {
        private const val KEY_APP_ID = "KEY_APP_ID"

        fun getIntent(context: Context, appId: String): Intent {
            return Intent(context, WebFeedActivity::class.java).also {
                it.putExtra(KEY_APP_ID, appId)
            }
        }
    }

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

        val appId = intent.extras?.get(KEY_APP_ID) ?: ""

        webView.loadUrl("${WEBFEED_SERVER_URL}${appId}")
    }
}