package com.buzzvil.buzzad.benefit.popbenefit1samplepublisher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.pop.BuzzAdPop;
import com.buzzvil.buzzad.benefit.pop.PopOverlayPermissionConfig;

import io.mattcarroll.hover.overlay.OverlayPermission;

import static com.buzzvil.lib.buzzsettingsmonitor.SettingsMonitor.KEY_SETTINGS_REQUEST_CODE;
import static com.buzzvil.lib.buzzsettingsmonitor.SettingsMonitor.KEY_SETTINGS_RESULT;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_SHOW_POP = 100;

    private Button popShowButton;
    private Button popUnregisterButton;

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
        BuzzAdBenefit.registerSessionReadyBroadcastReceiver(MainActivity.this, sessionReadyReceiver);

        buzzAdPop = new BuzzAdPop(App.UNIT_ID_POP);
        popShowButton = findViewById(R.id.pop_show_button);
        popShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buzzAdPop.hasPermission(MainActivity.this) || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    showPop();
                } else {
                    buzzAdPop.requestPermissionWithDialog(MainActivity.this,
                            new PopOverlayPermissionConfig.Builder(R.string.pop_name)
                                    .settingsIntent(OverlayPermission.createIntentToRequestOverlayPermission(MainActivity.this))
                                    .requestCode(REQUEST_CODE_SHOW_POP)
                                    .build()
                    );
                }
            }
        });

        popUnregisterButton = findViewById(R.id.pop_unregister_button);
        popUnregisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buzzAdPop.removePop(MainActivity.this);
            }
        });

        if (getIntent().getBooleanExtra(KEY_SETTINGS_RESULT, false)
                && getIntent().getIntExtra(KEY_SETTINGS_REQUEST_CODE, 0) == REQUEST_CODE_SHOW_POP) {
            if (buzzAdPop.hasPermission(this)) {
                showPop();
            }
        }
    }

    private void showPop() {
        Intent i = new Intent(MainActivity.this, PopContentActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BuzzAdBenefit.unregisterSessionReadyBroadcastReceiver(this, sessionReadyReceiver);
    }
}