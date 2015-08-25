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
         */
        BuzzAd.init("43", this);
        
        findViewById(R.id.open_offerwall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	/**
            	 * Show offer wall.
            	 * MainActivity.this : current activity, "my title", 
            	 * "Get Points" : header title on offer wall
            	 * "media_user_id" : unique user id in publisher
            	 */
                BuzzAd.showOfferWall(MainActivity.this, "Get Points", "media_user_id");
            }
        });
    }
}
