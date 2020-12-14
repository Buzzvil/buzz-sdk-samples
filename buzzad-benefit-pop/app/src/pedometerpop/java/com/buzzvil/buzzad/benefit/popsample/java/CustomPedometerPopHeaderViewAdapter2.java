package com.buzzvil.buzzad.benefit.popsample.java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.buzzvil.buzzad.benefit.pop.DefaultPedometerPopHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.popsample.R;

public class CustomPedometerPopHeaderViewAdapter2 extends DefaultPedometerPopHeaderViewAdapter {
    @Override
    public View onCreateView(Context context, ViewGroup parent) {
        View view = super.onCreateView(context, parent);
        View customLayout = LayoutInflater.from(context).inflate(
                R.layout.view_custom_feed_header,
                parent,
                false
        );
        ImageView imgIcon = customLayout.findViewById(R.id.imgIcon);
        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hi CustomPedometerPopHeaderViewAdapter2", Toast.LENGTH_LONG).show();
            }
        });
        ((ViewGroup) view).addView(customLayout);
        return view;
    }
}
