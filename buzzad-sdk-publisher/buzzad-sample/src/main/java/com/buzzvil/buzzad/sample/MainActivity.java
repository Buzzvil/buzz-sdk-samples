package com.buzzvil.buzzad.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.buzzvil.buzzad.sdk.BuzzAd;
import com.buzzvil.buzzad.sdk.UserProfile;

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

        /**
         * Set User's profile(Optional)
         * BuzzAd.getUserProfile have to be called after BuzzAd.init is called.
         */
        UserProfile userProfile = BuzzAd.getUserProfile();

        userProfile.setBirthYear(1993);
        userProfile.setGender(UserProfile.USER_GENDER_FEMALE);
        
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

        findViewById(R.id.open_inquiry_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Show inquiry page.
                 * MainActivity.this : Current activity
                 * Get Points : Header title on offer wall
                 * publisher_user_id : unique user id for publisher
                 */
                BuzzAd.showInquiryPage(MainActivity.this, "publisher_user_id");
            }
        });

        findViewById(R.id.open_custom_offerwall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OfferActivity.class);
                intent.putExtra("title", "Get Points (Custom)");
                intent.putExtra("userId", "publisher_user_id");
                startActivity(intent);
            }
        });
    }
}
