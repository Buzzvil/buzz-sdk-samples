package com.buzzvil.buzzad.benefit.sample.publisher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedHandler;

import java.text.DecimalFormat;

public class FeedEntryPointActivity extends AppCompatActivity {

    private TextView iconWithMessageImageView;
    private Button button;
    private FeedHandler feedHandler;

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

        feedHandler = new FeedHandler(this, App.UNIT_ID_FEED);
    }

    @Override
    protected void onResume() {
        super.onResume();

        feedHandler.preload(new FeedHandler.FeedPreloadListener() {
            @Override
            public void onPreloaded() {
                updateMessages(feedHandler.getTotalReward());
            }

            @Override
            public void onError(AdError adError) {
                updateMessages(0);
            }
        });
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
