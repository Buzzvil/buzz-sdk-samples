package com.buzzvil.benefit.extpoint.sample.publisher.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.benefit.extpoint.sample.publisher.R
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig
import com.buzzvil.buzzad.benefit.presentation.feed.FeedFragment
import com.buzzvil.buzzad.benefit.presentation.feed.header.DefaultFeedHeaderViewAdapter

class CustomFeedActivity : AppCompatActivity() {
    companion object {
        private val KEY_UNIT_ID = "KEY_UNIT_ID"

        fun getIntent(context: Context, unitId: String): Intent {
            val intent = Intent(context, CustomFeedActivity::class.java)
            intent.putExtra(KEY_UNIT_ID, unitId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        intent.getStringExtra(KEY_UNIT_ID)?.let {
            initFeed(it)
        }
    }

    private fun initFeed(unitId: String) {
        val feedConfig = FeedConfig.Builder(this, unitId)
            .feedHeaderViewAdapterClass(DefaultFeedHeaderViewAdapter::class.java)
            .articlesEnabled(true)
            .build()
        val feedFragment =
            supportFragmentManager.findFragmentById(R.id.feedFragment) as FeedFragment
        feedFragment.init(feedConfig)
    }
}
