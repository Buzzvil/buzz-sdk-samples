package com.buzzvil.sample.webfeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.sample.webfeed.App.Companion.WEBFEED_SERVER_URL
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webFeedServerUrl.text = WEBFEED_SERVER_URL

        webFeedButton.setOnClickListener {
            startActivity(WebFeedActivity.getIntent(this, appIdView.text.toString()))
        }
    }
}