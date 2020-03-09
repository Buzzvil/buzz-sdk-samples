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
import com.buzzvil.buzzad.benefit.popsample.R;

import io.mattcarroll.hover.overlay.OverlayPermission;

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
    }

    private void showPop() {
        buzzAdPop.preload(new BuzzAdPop.PopPreloadListener() {
            @Override
            public void onPreloaded(int adsSize, int articlesSize) {
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
