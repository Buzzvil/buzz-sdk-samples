package com.buzzvil.buzzad.benefit.notiplussample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.buzzvil.buzzad.benefit.presentation.notification.BuzzAdNotiPlus;

public class RestartReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())
                || Intent.ACTION_MY_PACKAGE_REPLACED.equals(intent.getAction())) {
            final BuzzAdNotiPlus buzzAdNoti = new BuzzAdNotiPlus(App.UNIT_ID_FEED, CustomNotificationWorker.class, App.getNotiPlusDialogConfig());
            buzzAdNoti.registerWorkerIfNeeded(context);
        }
    }
}
