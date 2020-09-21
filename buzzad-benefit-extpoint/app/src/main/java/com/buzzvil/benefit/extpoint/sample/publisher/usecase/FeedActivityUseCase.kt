package com.buzzvil.benefit.extpoint.sample.publisher.usecase

import android.content.Context
import com.buzzvil.benefit.extpoint.sample.publisher.presentation.CustomFeedActivity

internal class FeedActivityUseCase {
    operator fun invoke(context: Context, unitId: String) {
        val intent = CustomFeedActivity.getIntent(context, unitId)
        context.startActivity(intent)
    }
}
