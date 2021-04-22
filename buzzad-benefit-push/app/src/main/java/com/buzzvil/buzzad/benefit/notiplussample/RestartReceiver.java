package com.buzzvil.buzzad.benefit.notiplussample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.buzzvil.buzzad.benefit.presentation.notification.BuzzAdPush;

public class RestartReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())
                || Intent.ACTION_MY_PACKAGE_REPLACED.equals(intent.getAction())) {
            final BuzzAdPush buzzAdPush = new BuzzAdPush(App.UNIT_ID_FEED, CustomNotificationWorker.class, App.getPushDialogConfig());
            buzzAdPush.registerWorkerIfNeeded(context);
        }
    }
}
