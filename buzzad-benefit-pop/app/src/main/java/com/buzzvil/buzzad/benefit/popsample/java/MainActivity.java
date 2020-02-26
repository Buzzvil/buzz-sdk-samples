package com.buzzvil.buzzad.benefit.popsample.java;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.pop.BuzzAdPop;
import com.buzzvil.buzzad.benefit.pop.PopOverlayPermissionConfig;
import com.buzzvil.buzzad.benefit.pop.message.MessagePreviewOption;
import com.buzzvil.buzzad.benefit.popsample.R;
import io.mattcarroll.hover.overlay.OverlayPermission;

import static com.buzzvil.lib.buzzsettingsmonitor.SettingsMonitor.KEY_SETTINGS_REQUEST_CODE;
import static com.buzzvil.lib.buzzsettingsmonitor.SettingsMonitor.KEY_SETTINGS_RESULT;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_SHOW_POP = 100;

    private Button popShowButton;
    private Button popUnregisterButton;
    private Button popSetMessagePreview;
    private Button popSetMessagePreviewWithIcon;
    private Button popRemoveMessagePreview;

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

        popSetMessagePreview = findViewById(R.id.pop_set_message_preview);
        popSetMessagePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buzzAdPop.setMessagePreview(
                        new MessagePreviewOption(
                                "말풍선 메세지 프리뷰",
                                System.currentTimeMillis(),
                                System.currentTimeMillis() + 48 * 60 * 60 * 1000,
                                10 * 1000,
                                1
                        )
                );
                Toast.makeText(MainActivity.this, "Message Preview Set", Toast.LENGTH_SHORT).show();
            }
        });

        popSetMessagePreviewWithIcon = findViewById(R.id.pop_set_message_preview_with_icon);
        popSetMessagePreviewWithIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buzzAdPop.setMessagePreview(
                        new MessagePreviewOption(
                                "말풍선 메세지 프리뷰",
                                R.drawable.bz_default_pop_icon,
                                System.currentTimeMillis(),
                                System.currentTimeMillis() + 48 * 60 * 60 * 1000,
                                10 * 1000,
                                1
                        )
                );
                Toast.makeText(MainActivity.this, "Message Preview with Icon Set", Toast.LENGTH_SHORT).show();
            }
        });

        popRemoveMessagePreview = findViewById(R.id.pop_remove_message_preview);
        popRemoveMessagePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buzzAdPop.removeMessagePreview();
                Toast.makeText(MainActivity.this, "Message Preview Removed", Toast.LENGTH_SHORT).show();
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
        buzzAdPop.preload(new BuzzAdPop.PopPreloadListener() {
            @Override
            public void onPreloaded() {
                buzzAdPop.showPop(MainActivity.this, true);
            }

            @Override
            public void onError(AdError error) {
                Toast.makeText(MainActivity.this, "Failed to load Pop Ads: " + error.toString(), Toast.LENGTH_SHORT).show();
                buzzAdPop.showPop(MainActivity.this, false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BuzzAdBenefit.unregisterSessionReadyBroadcastReceiver(this, sessionReadyReceiver);
    }
}
