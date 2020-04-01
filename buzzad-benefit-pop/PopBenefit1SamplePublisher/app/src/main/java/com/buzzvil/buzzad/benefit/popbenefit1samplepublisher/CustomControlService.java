package com.buzzvil.buzzad.benefit.popbenefit1samplepublisher;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.RemoteViews;

import com.buzzvil.buzzad.benefit.pop.PopControlService;
import com.buzzvil.buzzad.benefit.pop.PopNotificationConfig;

import static android.support.v4.app.NotificationCompat.PRIORITY_LOW;
import static com.buzzvil.buzzad.benefit.pop.PopNotificationConfig.RESOURCE_NOT_SET;

public class CustomControlService extends PopControlService {

    @Override
    protected Notification buildForegroundNotification(@NonNull String unitId, @NonNull PopNotificationConfig popNotificationConfig) {
        PendingIntent popPendingIntent = getPopPendingIntent(unitId, this);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannelIfNeeded();
            builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(this);
        }

        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.view_custom_notification);
        builder.setSmallIcon(popNotificationConfig.getSmallIconResId())
                .setContent(remoteView)
                .setContentIntent(popPendingIntent)
                .setPriority(PRIORITY_LOW)
                .setShowWhen(false);
        if (popNotificationConfig.getColorResId() != RESOURCE_NOT_SET) {
            builder.setColor(ContextCompat.getColor(this, popNotificationConfig.getColorResId()));
        }
        return builder.build();
    }
}
