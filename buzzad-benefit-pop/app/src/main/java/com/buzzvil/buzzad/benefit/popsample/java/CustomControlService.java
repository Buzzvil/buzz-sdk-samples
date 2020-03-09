package com.buzzvil.buzzad.benefit.popsample.java;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.buzzvil.buzzad.benefit.pop.PopControlService;
import com.buzzvil.buzzad.benefit.pop.PopNotificationConfig;
import com.buzzvil.buzzad.benefit.popsample.R;

import static androidx.core.app.NotificationCompat.PRIORITY_LOW;
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
