package com.buzzvil.benefit.extpoint.sample.publisher.usecase

import android.content.Context
import com.buzzvil.buzzad.benefit.presentation.feed.BuzzAdFeed

internal class FeedActivityUseCase {
    operator fun invoke(context: Context) {
        BuzzAdFeed.Builder().build().show(context)
    }
}
