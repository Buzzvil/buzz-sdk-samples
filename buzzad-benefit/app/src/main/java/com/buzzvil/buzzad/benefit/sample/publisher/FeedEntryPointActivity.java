package com.buzzvil.buzzad.benefit.sample.publisher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FeedEntryPointActivity extends AppCompatActivity {
    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, FeedEntryPointActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_entry_point);
    }
}
