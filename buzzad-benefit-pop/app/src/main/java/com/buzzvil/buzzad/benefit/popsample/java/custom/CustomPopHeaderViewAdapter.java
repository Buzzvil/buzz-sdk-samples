package com.buzzvil.buzzad.benefit.popsample.java.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.buzzvil.buzzad.benefit.pop.DefaultPopHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.pop.domain.model.CustomPreviewMessage;
import com.buzzvil.buzzad.benefit.popsample.R;

public class CustomPopHeaderViewAdapter extends DefaultPopHeaderViewAdapter {
    @Nullable
    @Override
    protected View getCustomPreviewMessageLayout(Context context, ViewGroup parent, CustomPreviewMessage customPreviewMessage) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return null;
        }
        View viewCustomPreviewMessage = inflater.inflate(R.layout.view_custom_preview_message, parent, false);
        final TextView textCustomPreviewMessageTitle = viewCustomPreviewMessage.findViewById(R.id.textCustomPreviewMessageTitle);
        textCustomPreviewMessageTitle.setText(customPreviewMessage.getMessage());
        viewCustomPreviewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDeeplinkActivity(context, customPreviewMessage.getLandingUrl());
            }
        });
        return viewCustomPreviewMessage;
    }
}
