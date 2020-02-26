package com.buzzvil.buzzad.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.buzzvil.buzzad.sdk.BuzzAd;
import com.buzzvil.buzzad.sdk.OfferwallView;

public class OfferActivity extends Activity {
    String userId;
    String title;
    OfferwallView offerwallView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userId = getIntent().getStringExtra("userId");
        title = getIntent().getStringExtra("title");

        setContentView(R.layout.activity_offer);

        ((TextView) findViewById(R.id.titleText)).setText(title);

        /**
         * Get Offerwall view.
         * this : Current activity
         * userId : unique user id for publisher
         */
        offerwallView = BuzzAd.getOfferwallView(this, userId);
        ((FrameLayout)findViewById(R.id.offerwall)).addView(offerwallView);

        findViewById(R.id.closeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.inquiryButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuzzAd.showInquiryPage(OfferActivity.this, userId);
            }
        });
    }

    @Override
    public void onBackPressed() {
        /**
         * Handle backPressed.
         * return: handled or not
         */
        if (offerwallView != null && offerwallView.handleBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
