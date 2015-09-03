package com.buzzvil.buzzad.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.buzzvil.buzzad.sdk.BuzzAd;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /**
         * Initialize BuzzAd.
         * BuzzAd.init have to be called prior to other methods.
         * app_key : Unique key value for publisher. Please find it on your BuzzAd dashboard.
         * this : Context
         */
        BuzzAd.init("app_key", this);
        
        findViewById(R.id.open_offerwall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	/**
            	 * Show offer wall.
            	 * MainActivity.this : Current activity
            	 * Get Points : Header title on offer wall
            	 * publisher_user_id : unique user id for publisher
            	 */
            	BuzzAd.showOfferWall(MainActivity.this, "Get Points", "publisher_user_id");
            }
        });
    }
}
