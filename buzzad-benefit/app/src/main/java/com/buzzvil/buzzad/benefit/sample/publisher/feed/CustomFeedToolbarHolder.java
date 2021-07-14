package com.buzzvil.buzzad.benefit.sample.publisher.feed;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;

import com.buzzvil.buzzad.benefit.presentation.feed.toolbar.DefaultFeedToolbarHolder;
import com.buzzvil.buzzad.benefit.presentation.feed.toolbar.FeedToolbar;
import com.buzzvil.buzzad.benefit.sample.publisher.R;

public class CustomFeedToolbarHolder extends DefaultFeedToolbarHolder {
    @Override
    public View getView(final Activity activity, @NonNull String unitId) {
        this.toolbar = new FeedToolbar(activity);
        toolbar.setTitle("BuzzAdBenefit Feed");
        toolbar.setBackgroundColor(Color.parseColor("#1290FF"));
        toolbar.setIconResource(R.mipmap.ic_launcher);

        addInquiryMenuItemView(activity);
        return toolbar;
    }

    @Override
    public void onTotalRewardUpdated(int totalReward) {}
}

