package com.buzzvil.sample.webfeed

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webFeedButton.setOnClickListener {
            val intent = Intent(this, WebFeedActivity::class.java)
            startActivity(intent)
        }
    }
}