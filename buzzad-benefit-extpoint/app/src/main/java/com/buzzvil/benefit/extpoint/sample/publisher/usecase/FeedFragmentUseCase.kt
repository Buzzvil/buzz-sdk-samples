package com.buzzvil.benefit.extpoint.sample.publisher.usecase

import android.content.Context
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig
import com.buzzvil.buzzad.benefit.presentation.feed.FeedHandler
import com.buzzvil.buzzad.benefit.presentation.feed.header.DefaultFeedHeaderViewAdapter

internal class FeedFragmentUseCase {
    operator fun invoke(context: Context, unitId: String) {
        val feedHandler = FeedHandler(context, unitId)
        feedHandler.startFeedActivity(context)
    }
}
