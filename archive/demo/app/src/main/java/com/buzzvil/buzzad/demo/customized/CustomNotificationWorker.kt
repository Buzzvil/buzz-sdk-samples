package com.buzzvil.buzzad.demo.customized

import android.content.Context
import androidx.work.WorkerParameters
import com.buzzvil.buzzad.benefit.presentation.notification.NotificationConfig
import com.buzzvil.buzzad.benefit.presentation.notification.NotificationWorker

class CustomNotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : NotificationWorker(context, workerParams) {

    override val notificationConfig: NotificationConfig
        get() = NotificationConfig.Builder()
            .notificationId(1234)
            .build()
}
