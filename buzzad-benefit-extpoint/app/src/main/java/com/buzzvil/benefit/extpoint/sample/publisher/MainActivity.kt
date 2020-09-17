package com.buzzvil.benefit.extpoint.sample.publisher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig
import com.buzzvil.buzzad.benefit.presentation.feed.FeedFragment
import com.buzzvil.buzzad.benefit.presentation.feed.FeedHandler
import com.buzzvil.buzzad.benefit.presentation.feed.header.DefaultFeedHeaderViewAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFeed()
    }

    private fun initFeed() {
        val feedConfig = FeedConfig.Builder(this, BuildConfig.BUZZ_FEED_UNIT_ID)
            .feedHeaderViewAdapterClass(DefaultFeedHeaderViewAdapter::class.java)
            .articlesEnabled(true)
            .build()
        val feedFragment =
            supportFragmentManager.findFragmentById(R.id.feedFragment) as FeedFragment
        feedFragment.init(feedConfig)
    }
}
