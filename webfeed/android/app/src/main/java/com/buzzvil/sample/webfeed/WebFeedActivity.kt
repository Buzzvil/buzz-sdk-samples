package com.buzzvil.sample.webfeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_webfeed.*

class WebFeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webfeed)
        webView.loadUrl(App.WEBFEED_URL)
    }
}