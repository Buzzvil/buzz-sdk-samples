package com.buzzvil.benefit.extpoint.sample.publisher

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig
import com.buzzvil.buzzad.benefit.presentation.feed.FeedHandler
import com.buzzvil.buzzad.benefit.presentation.feed.header.DefaultFeedHeaderViewAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            //Do something after 100ms
            initFeed()
        }, 5000)
    }

    private fun initFeed() {
        Log.d("MainActivity", "initFeed()")
        val feedConfig = FeedConfig.Builder(this, BuildConfig.BUZZ_FEED_UNIT_ID)
            .feedHeaderViewAdapterClass(DefaultFeedHeaderViewAdapter::class.java)
            .autoLoadingEnabled(true)
            .build()
        val feedHandler = FeedHandler(feedConfig)
        feedHandler.startFeedActivity(this@MainActivity)
    }
}
