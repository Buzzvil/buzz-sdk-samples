package com.buzzvil.buzzad.benefit.sample.publisher.feed;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;

import com.buzzvil.buzzad.benefit.presentation.feed.toolbar.DefaultFeedToolbarHolder;
import com.buzzvil.buzzad.benefit.presentation.feed.toolbar.FeedActivityToolbar;

public class CustomFeedToolbarHolder extends DefaultFeedToolbarHolder {
    private FeedActivityToolbar toolbar;

    @Override
    public View getView(final Activity activity, @NonNull String unitId) {
        this.toolbar = new FeedActivityToolbar(activity, unitId);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
        toolbar.setTitle("BuzzAdBenefit Feed");
        toolbar.setBackgroundColor(Color.parseColor("#1290FF"));
        return toolbar;
    }

    @Override
    public void onTotalRewardUpdated(int totalReward) {}
}

