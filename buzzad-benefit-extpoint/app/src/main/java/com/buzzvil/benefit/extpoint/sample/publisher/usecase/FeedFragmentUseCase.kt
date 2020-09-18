package com.buzzvil.benefit.extpoint.sample.publisher.usecase

import android.content.Context
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig
import com.buzzvil.buzzad.benefit.presentation.feed.FeedHandler
import com.buzzvil.buzzad.benefit.presentation.feed.header.DefaultFeedHeaderViewAdapter

internal class FeedFragmentUseCase {
    operator fun invoke(context: Context, unitId: String) {
        val feedConfig = FeedConfig.Builder(context, unitId)
            .feedHeaderViewAdapterClass(DefaultFeedHeaderViewAdapter::class.java)
            .articlesEnabled(true)
            .build()
        val feedHandler = FeedHandler(feedConfig)
        feedHandler.startFeedActivity(context)
    }
}
