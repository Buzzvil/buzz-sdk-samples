package com.buzzvil.buzzad.benefit.notiplussample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import com.buzzvil.buzzad.benefit.presentation.feed.FeedActivity;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;
import com.buzzvil.buzzad.benefit.presentation.notification.NotiPlusCtaActivity;
import com.buzzvil.buzzad.benefit.presentation.notification.NotificationConfig;
import com.buzzvil.buzzad.benefit.presentation.notification.NotificationWorker;
import com.buzzvil.buzzad.benefit.presentation.notification.RewardNotificationConfig;

public class CustomNotificationWorker extends NotificationWorker {
    public CustomNotificationWorker(@NonNull Context context,
                                    @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    @NonNull
    public NotificationConfig getNotificationConfig() {
        final FeedConfig feedConfig = new FeedConfig.Builder(App.UNIT_ID_NOTI_PLUS).build();
        final RewardNotificationConfig rewardNotificationConfig = new RewardNotificationConfig.Builder().build();
        return new NotificationConfig.Builder(App.UNIT_ID_NOTI_PLUS)
                .putExtra(FeedActivity.EXTRA_CONFIG, feedConfig)
                .putExtra(NotiPlusCtaActivity.EXTRA_REWARD_NOTIFICATION_CONFIG, rewardNotificationConfig)
                .build();
    }
}
