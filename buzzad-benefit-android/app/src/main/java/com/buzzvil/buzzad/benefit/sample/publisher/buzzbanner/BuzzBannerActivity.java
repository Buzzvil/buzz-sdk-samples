package com.buzzvil.buzzad.benefit.sample.publisher.buzzbanner;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.buzzvil.buzzad.benefit.sample.publisher.R;
import com.buzzvil.buzzbanner.AdError;
import com.buzzvil.buzzbanner.BuzzBanner;
import com.buzzvil.buzzbanner.BuzzBannerConfig;
import com.buzzvil.buzzbanner.BuzzBannerView;
import com.buzzvil.buzzbanner.BuzzBannerViewListener;

public class BuzzBannerActivity extends AppCompatActivity {
    private static final String TAG = "BuzzBannerActivity";
    private static final String BANNER_PLACEMENT_ID = "YOUR_BANNER_PLACEMENT_ID";

    private BuzzBannerView buzzBannerViewWithXml;
    private BuzzBannerView buzzBannerViewWithCode;

    private TextView buzzBannerViewWithXmlState;
    private TextView buzzBannerViewWithCodeState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzz_banner);

        buzzBannerViewWithXml = findViewById(R.id.buzzBannerViewWithXml);
        buzzBannerViewWithCode = findViewById(R.id.buzzBannerViewWithCode);

        buzzBannerViewWithXmlState = findViewById(R.id.buzzBannerViewWithXmlState);
        buzzBannerViewWithCodeState = findViewById(R.id.buzzBannerViewWithCodeState);

        setBuzzBannerConfig();
        setBuzzBannerListener();
    }

    private void setBuzzBannerConfig() {
        BuzzBannerConfig buzzBannerConfig = new BuzzBannerConfig.Builder()
                .bannerSize(BuzzBanner.BannerSize.W320XH50) // BuzzBanner.BannerSize.W320XH50 or BuzzBanner.BannerSize.W320XH100
                .placementId(BANNER_PLACEMENT_ID)
                .build();

        buzzBannerViewWithCode.setBuzzBannerConfig(buzzBannerConfig);
    }

    private void setBuzzBannerListener() {
        buzzBannerViewWithXml.setBuzzBannerViewListener(new BuzzBannerViewListener() {
            @Override
            public void onClicked() {
                Log.d(TAG, "buzzBannerViewWithXml state: onClicked");
                buzzBannerViewWithXmlState.setText("buzzBannerViewWithXml state: onClicked");
            }

            @Override
            public void onFailed(@NonNull AdError adError) {
                Log.d(TAG, "buzzBannerViewWithXml state: onFailed, error: " + adError);
                buzzBannerViewWithXmlState.setText("buzzBannerViewWithXml state: onFailed, error: " + adError);
            }

            @Override
            public void onLoaded() {
                Log.d(TAG, "buzzBannerViewWithXml state: onLoaded");
                buzzBannerViewWithXmlState.setText("buzzBannerViewWithXml state: onLoaded");
            }
        });

        buzzBannerViewWithCode.setBuzzBannerViewListener(new BuzzBannerViewListener() {
            @Override
            public void onClicked() {
                Log.d(TAG, "buzzBannerViewWithCode state: onClicked");
                buzzBannerViewWithCodeState.setText("buzzBannerViewWithCode state: onClicked");
            }

            @Override
            public void onFailed(@NonNull AdError adError) {
                Log.d(TAG, "buzzBannerViewWithCode state: onFailed, error: " + adError);
                buzzBannerViewWithCodeState.setText("buzzBannerViewWithCode state: onFailed, error: " + adError);
            }

            @Override
            public void onLoaded() {
                Log.d(TAG, "buzzBannerViewWithCode state: onLoaded");
                buzzBannerViewWithCodeState.setText("buzzBannerViewWithCode state: onLoaded");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        buzzBannerViewWithXml.onResume(BuzzBannerActivity.this);
        buzzBannerViewWithCode.onResume(BuzzBannerActivity.this);
    }

    @Override
    protected void onPause() {
        buzzBannerViewWithXml.onPause();
        buzzBannerViewWithCode.onPause();

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        buzzBannerViewWithXml.onDestroy();
        buzzBannerViewWithCode.onDestroy();

        super.onDestroy();
    }
}
