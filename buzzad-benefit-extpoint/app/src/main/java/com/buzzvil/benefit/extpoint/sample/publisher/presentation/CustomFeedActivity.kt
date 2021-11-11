package com.buzzvil.benefit.extpoint.sample.publisher.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.benefit.extpoint.sample.publisher.R
import com.buzzvil.buzzad.benefit.presentation.feed.BuzzAdFeed

class CustomFeedActivity : AppCompatActivity() {
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, CustomFeedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        initFeed()
    }

    private fun initFeed() {
        // 광고를 새로 받기 위해 필요한 부분입니다.
        val buzzAdFeed = BuzzAdFeed.Builder().build()

        val feedFragment = buzzAdFeed.getFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, feedFragment)
            .commitAllowingStateLoss()
    }
}
