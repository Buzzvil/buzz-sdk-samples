package com.buzzvil.buzzad.benefit.notiplussample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import com.buzzvil.buzzad.benefit.presentation.feed.FeedActivity;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;
import com.buzzvil.buzzad.benefit.presentation.notification.NotificationConfig;
import com.buzzvil.buzzad.benefit.presentation.notification.NotificationWorker;

public class CustomNotificationWorker extends NotificationWorker {
    public CustomNotificationWorker(@NonNull Context context,
                                    @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    @NonNull
    public NotificationConfig getNotificationConfig() {
        final FeedConfig feedConfig = new FeedConfig.Builder(App.UNIT_ID_FEED).build();
        return new NotificationConfig.Builder(App.UNIT_ID_FEED)
                .putExtra(FeedActivity.EXTRA_CONFIG, feedConfig)
                .build();
    }
}
