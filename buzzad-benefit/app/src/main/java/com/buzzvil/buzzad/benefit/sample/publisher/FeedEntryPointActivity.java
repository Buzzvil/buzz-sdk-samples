package com.buzzvil.buzzad.benefit.sample.publisher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.presentation.feed.BuzzAdFeed;
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedEntryView;

import java.text.DecimalFormat;

public class FeedEntryPointActivity extends AppCompatActivity {

    private TextView iconWithMessageImageView;
    private Button button;
    private BuzzAdFeed buzzAdFeed;

    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, FeedEntryPointActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_entry_point);

        iconWithMessageImageView = findViewById(R.id.textViewIconWithMessage);
        button = findViewById(R.id.button);
        createEntryPointViewDynamically();

        buzzAdFeed = new BuzzAdFeed.Builder().build();
    }

    @Override
    protected void onResume() {
        super.onResume();

        buzzAdFeed.load(new BuzzAdFeed.FeedLoadListener() {
            @Override
            public void onSuccess() {
                updateMessages(buzzAdFeed.getAvailableRewards());
            }

            @Override
            public void onError(@Nullable AdError adError) {
                updateMessages(0);
            }
        });
    }

    private void createEntryPointViewDynamically() {
        final ViewGroup parent = findViewById(R.id.itemViewIconByCode);
        final FeedEntryView entryView = (FeedEntryView) getLayoutInflater().inflate(R.layout.view_feed_entry_view_icon, null);
        entryView.setFeedEntryViewName("entry point by code");
        parent.addView(entryView);
    }

    private void updateMessages(final int points) {
        if (points > 0) {
            updateMessagesWithPoint(points);
        } else {
            updateMessagesWithoutPoint();
        }
    }

    private void updateMessagesWithPoint(final int points) {
        final DecimalFormat pointsFormat = new DecimalFormat("#,###");
        final String pointsString = pointsFormat.format(points);
        if (iconWithMessageImageView != null) {
            final Spanned coloredMessage = Html.fromHtml(getString(R.string.feed_entry_point_message_with_highlight, pointsString));
            iconWithMessageImageView.setText(coloredMessage);
        }
        if (button != null) {
            button.setText(getString(R.string.feed_entry_point_message, pointsString));
        }
    }

    private void updateMessagesWithoutPoint() {
        final String message = getString(R.string.feed_entry_point_message_without_points);
        if (iconWithMessageImageView != null) {
            iconWithMessageImageView.setText(message);
        }
        if (button != null) {
            button.setText(getString(R.string.feed_entry_point_message, message));
        }
    }
}
