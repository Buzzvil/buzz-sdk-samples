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

import java.text.DecimalFormat;

public class FeedEntryPointActivity extends AppCompatActivity {

    private TextView iconWithMessageImageView;
    private Button button;

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateMessages(12345);
    }

    private void updateMessages(final int points) {
        DecimalFormat pointsFormat = new DecimalFormat("#,###");
        updateMessages(pointsFormat.format(points));

    }

    private void updateMessages(final String points) {
        if (iconWithMessageImageView != null) {
            final Spanned coloredMessage = Html.fromHtml(getString(R.string.feed_entry_point_message_with_highlight, points));
            iconWithMessageImageView.setText(coloredMessage);
        }
        if (button != null) {
            button.setText(getString(R.string.feed_entry_point_message, points));
        }
    }
}
