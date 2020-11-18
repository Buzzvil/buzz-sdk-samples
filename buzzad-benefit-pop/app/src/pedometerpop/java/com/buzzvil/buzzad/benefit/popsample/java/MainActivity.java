package com.buzzvil.buzzad.benefit.popsample.java;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.pop.BuzzAdPop;
import com.buzzvil.buzzad.benefit.pop.PopOverlayPermissionConfig;
import com.buzzvil.buzzad.benefit.popsample.BuildConfig;
import com.buzzvil.buzzad.benefit.popsample.R;
import com.buzzvil.lib.buzzsettingsmonitor.OverlaySettingsMonitor;
import com.buzzvil.lib.buzzsettingsmonitor.SettingsMonitorManager;

import io.mattcarroll.hover.overlay.OverlayPermission;

import static com.buzzvil.lib.buzzsettingsmonitor.SettingsMonitor.KEY_SETTINGS_REQUEST_CODE;
import static com.buzzvil.lib.buzzsettingsmonitor.SettingsMonitor.KEY_SETTINGS_RESULT;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private final static int REQUEST_CODE_SHOW_POP = 100;

    private TextView textAppVersion;
    private TextView textSdkSdkVersion;
    private Button popShowButton;
    private Button popUnregisterButton;
    private Button popShowWithCustomPermissoinDialogButton;

    private BuzzAdPop buzzAdPop;
    private BroadcastReceiver sessionReadyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("SessionKey", "Session is Ready. Ads can be loaded now.");
            Toast.makeText(MainActivity.this, "Session is Ready. Ads can be loaded now.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVersionText();

        BuzzAdBenefit.registerSessionReadyBroadcastReceiver(MainActivity.this, sessionReadyReceiver);

        this.buzzAdPop = new BuzzAdPop(this, App.UNIT_ID_POP);
        this.popShowButton = findViewById(R.id.pop_show_button);
        popShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopOrRequestPermissionWithDialog();
            }
        });

        this.popShowWithCustomPermissoinDialogButton = findViewById(R.id.pop_show_button_with_custom_permission_dialog_button);
        popShowWithCustomPermissoinDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopOrShowOverlayPermissionDialog();
            }
        });

        this.popUnregisterButton = findViewById(R.id.pop_unregister_button);
        popUnregisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buzzAdPop.removePop(MainActivity.this);
            }
        });

        if (getIntent().getBooleanExtra(KEY_SETTINGS_RESULT, false)
                && getIntent().getIntExtra(KEY_SETTINGS_REQUEST_CODE, 0) == REQUEST_CODE_SHOW_POP) {
            if (BuzzAdPop.hasPermission(this)) {
                buzzAdPop.showTutorialPopup(this);
                // overlay permission granted
                // collect event here if necessary
            }
        }
    }

    private void setVersionText() {
        textAppVersion = findViewById(R.id.text_app_version);
        String strAppVersion = BuildConfig.VERSION_NAME;
        textAppVersion.setText(strAppVersion);
        textSdkSdkVersion = findViewById(R.id.text_sdk_version);
        String strBenefitSdkVersion = BuzzAdBenefit.getSdkVersion();
        textSdkSdkVersion.setText(strBenefitSdkVersion);
    }

    private void showPopOrRequestPermissionWithDialog() {
        if (BuzzAdPop.hasPermission(MainActivity.this) || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            showPop();
        } else {
            BuzzAdPop.requestPermissionWithDialog(MainActivity.this,
                    new PopOverlayPermissionConfig.Builder(R.string.pop_name)
                            .settingsIntent(OverlayPermission.createIntentToRequestOverlayPermission(MainActivity.this))
                            .requestCode(REQUEST_CODE_SHOW_POP)
                            .build()
            );
        }
    }

    private void showPop() {
        buzzAdPop.preloadAndShowPop(MainActivity.this);

        // Use this instead of preloadAndShowPop if need to show pop tutorial dialog
        // buzzAdPop.showTutorialPopup(MainActivity.this);
    }

    /*
     * (Optional: CUSTOM PERMISSION DIALOG)
     * Use this method when collecting event for overlay permission granted, otherwise see showPopOrRequestPermissionWithDialog
     */
    private void showPopOrShowOverlayPermissionDialog() {
        if (BuzzAdPop.hasPermission(MainActivity.this) || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            showPop();
        } else {
            showPopOverlayPermissionDialog(MainActivity.this, new PopOverlayPermissionConfig.Builder(R.string.pop_name)
                    .settingsIntent(OverlayPermission.createIntentToRequestOverlayPermission(MainActivity.this))
                    .requestCode(REQUEST_CODE_SHOW_POP)
                    .build());

        }
    }

    /*
     * (Optional: CUSTOM PERMISSION DIALOG)
     * Use this method when collecting event for overlay permission granted, otherwise see showPopOrRequestPermissionWithDialog
     */
    private void showPopOverlayPermissionDialog(@NonNull final Activity activity, @NonNull final PopOverlayPermissionConfig permissionSettings) {
        final AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle(permissionSettings.getTitle())
                .setPositiveButton(permissionSettings.getPositiveButtonText(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            new SettingsMonitorManager().openSettingsActivity(
                                    new OverlaySettingsMonitor(activity, activity.getClass(), permissionSettings.getRequestCode()),
                                    activity,
                                    permissionSettings.getSettingsIntent());
                            dialog.dismiss();
                        } else {
                            Log.e(TAG, "Unnecessary to show showPopOverlayPermissionDialog for this android os version");
                        }
                    }
                })
                .setNegativeButton(permissionSettings.getNegativeButtonText(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(true)
                .create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BuzzAdBenefit.unregisterSessionReadyBroadcastReceiver(this, sessionReadyReceiver);
    }
}
