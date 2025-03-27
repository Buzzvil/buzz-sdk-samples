package com.buzzvil.sample.buzzvilsdksample.pop

import android.annotation.TargetApi
import android.app.Notification
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.buzzvil.buzzad.benefit.pop.PopControlService
import com.buzzvil.buzzad.benefit.pop.PopNotificationConfig
import com.buzzvil.sample.buzzvilsdksample.R

class YourPopControlService: PopControlService() {
    companion object {
        const val POP_NOTIFICATION_ID = 1000
    }
    override fun buildForegroundNotification(unitId: String, popNotificationConfig: PopNotificationConfig): Notification {
        // Pop을 표시하는 PendingIntent (원형 아이콘)
        val popPendingIntent = getPopPendingIntent(unitId, this)

        // 필요에 따라 notificationChannel을 등록합니다.
        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannelIfNeeded()
            NotificationCompat.Builder(this, POP_NOTIFICATION_ID.toString())
        } else {
            NotificationCompat.Builder(this)
        }

        // Pop Service Notification에 사용할 View 를 등록합니다.
        val remoteView = RemoteViews(packageName, R.layout.notification_pop)
        builder.setSmallIcon(popNotificationConfig.smallIconResId)
            .setContent(remoteView)
            .setContentIntent(popPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setShowWhen(false)
        builder.foregroundServiceBehavior = NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE
        if (popNotificationConfig.color != null) {
            builder.color = popNotificationConfig.color!!
        }

        return builder.build()
    }


    @TargetApi(Build.VERSION_CODES.O)
    override fun createNotificationChannelIfNeeded() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
        if (notificationManager.getNotificationChannel(POP_NOTIFICATION_ID.toString()) == null) {
            val channel = android.app.NotificationChannel(
                POP_NOTIFICATION_ID.toString(),
                NOTIFICATION_CHANNEL_NAME,
                android.app.NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}
