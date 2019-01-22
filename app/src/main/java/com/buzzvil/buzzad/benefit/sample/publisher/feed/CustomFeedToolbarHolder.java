package com.buzzvil.buzzad.benefit.sample.publisher.feed;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.buzzvil.buzzad.benefit.presentation.feed.toolbar.FeedActivityToolbar;
import com.buzzvil.buzzad.benefit.presentation.feed.toolbar.FeedToolbarHolder;

import java.util.Locale;

public class CustomFeedToolbarHolder implements FeedToolbarHolder {
    private FeedActivityToolbar toolbar;

    @Override
    public View getView(final Activity activity) {
        this.toolbar = new FeedActivityToolbar(activity);
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
    public void onTotalRewardUpdated(int totalReward) {
        if (totalReward > 0) {
            toolbar.setReward(String.format(Locale.US, "+%d", totalReward));
            toolbar.setRewardImageVisibility(View.VISIBLE);
        } else {
            toolbar.setReward(null);
            toolbar.setRewardImageVisibility(View.GONE);
        }
    }
}

