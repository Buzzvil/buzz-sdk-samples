package com.buzzvil.buzzad.benefit.popsample.java;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.pop.BuzzAdPop;
import com.buzzvil.buzzad.benefit.pop.PopOverlayPermissionConfig;
import com.buzzvil.buzzad.benefit.popsample.R;

import io.mattcarroll.hover.overlay.OverlayPermission;

import static com.buzzvil.lib.buzzsettingsmonitor.SettingsMonitor.KEY_SETTINGS_REQUEST_CODE;
import static com.buzzvil.lib.buzzsettingsmonitor.SettingsMonitor.KEY_SETTINGS_RESULT;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private final static int REQUEST_CODE_SHOW_POP = 100;

    private Button popInitButton;
    private Button popSetUserProfileButton;
    private Button popShowButton;
    private Button popUnregisterButton;
    private Button popClearButton;
    private BroadcastReceiver sessionReadyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("SessionKey", "Session is Ready. Ads can be loaded now.");
            Toast.makeText(MainActivity.this, "Session is Ready. Ads can be loaded now.", Toast.LENGTH_SHORT).show();
            app.buildBuzzAdPop();
        }
    };

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (App) getApplication();

        app.buildBuzzAdPop();
        this.popInitButton = findViewById(R.id.pop_init_button);
        popInitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.initBuzzAdBenefit();
            }
        });

        this.popSetUserProfileButton = findViewById(R.id.pop_set_user_profile_button);
        popSetUserProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!app.isBuzzAdBenefitInitialized()) {
                    Toast.makeText(MainActivity.this, "buzzAdBenefit is not initialized. unable to set userProfile", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG, "setUserProfile");
                final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                        .userId("SAMPLE_USER_ID")
                        .gender(UserProfile.Gender.FEMALE)
                        .birthYear(1993)
                        .build();
                BuzzAdBenefit.setUserProfile(userProfile);
                BuzzAdBenefit.registerSessionReadyBroadcastReceiver(MainActivity.this, sessionReadyReceiver);
            }
        });

        this.popShowButton = findViewById(R.id.pop_show_button);
        popShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopOrRequestPermissionWithDialog();
            }
        });

        this.popUnregisterButton = findViewById(R.id.pop_unregister_button);
        popUnregisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (app.getBuzzAdPop() == null) {
                    Toast.makeText(MainActivity.this, "buzzAdPop is not ready. unable to unregister pop", Toast.LENGTH_SHORT).show();
                    return;
                }
                app.getBuzzAdPop().removePop(MainActivity.this);
            }
        });

        this.popClearButton = findViewById(R.id.pop_clear_pop);
        popClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (app.getBuzzAdPop() == null) {
                    Toast.makeText(MainActivity.this, "buzzAdPop is not ready. No need to clear", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!app.isBuzzAdBenefitInitialized()) {
                    Log.d(TAG, "BuzzAdPop is not initialized. No need to clear");
                    return;
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        app.clearBuzzAdBenefit();
                    }
                }, 2000);
            }
        });

        if (getIntent().getBooleanExtra(KEY_SETTINGS_RESULT, false)
                && getIntent().getIntExtra(KEY_SETTINGS_REQUEST_CODE, 0) == REQUEST_CODE_SHOW_POP) {
            if (BuzzAdPop.hasPermission(this)) {
                if (app.getBuzzAdPop() != null) {
                    app.getBuzzAdPop().showTutorialPopup(this);
                } else {
                    Toast.makeText(MainActivity.this, "buzzAdPop is not ready. unable to showTutorialPop", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "buzzAdPop is not ready. unable to showTutorialPop");
                }
                // overlay permission granted
                // collect event here if necessary
            }
        }
    }

    private void showPopOrRequestPermissionWithDialog() {
        if (app.getBuzzAdPop() == null) {
            Toast.makeText(MainActivity.this, "buzzAdPop is not ready. unable to showPop", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "buzzAdPop is not ready. unable to showPop");
            return;
        }
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
        if (app.getBuzzAdPop() == null) {
            Toast.makeText(MainActivity.this, "buzzAdPop is not ready. unable to showPop", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "buzzAdPop is not ready. unable to showPop");
            return;
        }
        app.getBuzzAdPop().preloadAndShowPop(MainActivity.this);

        // Use this instead of preloadAndShowPop if need to show pop tutorial dialog
        // buzzAdPop.showTutorialPopup(MainActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BuzzAdBenefit.unregisterSessionReadyBroadcastReceiver(this, sessionReadyReceiver);
    }
}
