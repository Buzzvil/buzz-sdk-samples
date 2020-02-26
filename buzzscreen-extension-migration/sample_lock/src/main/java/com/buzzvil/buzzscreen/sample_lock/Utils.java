package com.buzzvil.buzzscreen.sample_lock;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by cos on 11/12/2017.
 */

public class Utils {
    static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    static int getAppVersionCode(Context context, String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }
}
