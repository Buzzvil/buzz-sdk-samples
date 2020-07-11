package com.buzzvil.buzzad.benefit.popsample.java.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.buzzvil.buzzad.benefit.pop.DefaultPopHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.pop.domain.model.MessagePreview;
import com.buzzvil.buzzad.benefit.popsample.R;

public class CustomPopHeaderViewAdapter extends DefaultPopHeaderViewAdapter {
    @Nullable
    @Override
    protected View getMessagePreviewLayout(Context context, ViewGroup parent, MessagePreview messagePreview) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return null;
        }
        View viewMessagePreview = inflater.inflate(R.layout.view_custom_preview_message, parent, false);
        final TextView messagePreviewText = viewMessagePreview.findViewById(R.id.textCustomPreviewMessageTitle);
        messagePreviewText.setText(messagePreview.getMessage());
        viewMessagePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDeeplinkActivity(context, messagePreview.getLandingUrl());
            }
        });
        return viewMessagePreview;
    }
}
