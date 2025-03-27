package com.buzzvil.sample.buzzvil_sdk_v6_sample.pop

import android.app.Notification
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.buzzvil.buzzbenefit.pop.BuzzPopControlService
import com.buzzvil.buzzbenefit.pop.BuzzPopNotificationConfig
import com.buzzvil.sample.buzzvil_sdk_v6_sample.R

class YourBuzzPopControlService : BuzzPopControlService() {
    override fun buildForegroundNotification(
        unitId: String,
        buzzPopNotificationConfig: BuzzPopNotificationConfig,
    ): Notification {
        // Pop을 표시하는 PendingIntent (원형 아이콘)
        val popPendingIntent = getPopPendingIntent(this)

        // 필요에 따라 notificationChannel을 등록합니다.
        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannelIfNeeded()
            NotificationCompat.Builder(this, POP_NOTIFICATION_CHANNEL_ID)
        } else {
            NotificationCompat.Builder(this)
        }

        // Pop Service Notification에 사용할 View 를 등록합니다.
        val remoteView = RemoteViews(packageName, R.layout.your_buzz_pop_notification)
        builder.setSmallIcon(buzzPopNotificationConfig.smallIconResId)
            .setContent(remoteView)
            .setContentIntent(popPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setShowWhen(false)
        builder.foregroundServiceBehavior = NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE
        if (buzzPopNotificationConfig.color != null) {
            builder.color = buzzPopNotificationConfig.color!!
        }

        return builder.build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createNotificationChannelIfNeeded() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
        if (notificationManager.getNotificationChannel(POP_NOTIFICATION_CHANNEL_ID) == null) {
            val channel = android.app.NotificationChannel(
                POP_NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                android.app.NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val POP_NOTIFICATION_CHANNEL_ID = "POP_NOTIFICATION_CHANNEL_ID"
        const val POP_NOTIFICATION_ID = 1000
    }
}