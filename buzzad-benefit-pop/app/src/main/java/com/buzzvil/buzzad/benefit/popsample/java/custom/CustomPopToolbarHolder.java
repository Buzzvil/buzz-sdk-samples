package com.buzzvil.buzzad.benefit.popsample.java.custom;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.buzzvil.buzzad.benefit.pop.toolbar.DefaultPopToolbarHolder;
import com.buzzvil.buzzad.benefit.popsample.R;

public class CustomPopToolbarHolder extends DefaultPopToolbarHolder {
    @Override
    public View getView(Activity activity) {
        ViewGroup root =  (ViewGroup) activity.getLayoutInflater().inflate(R.layout.view_pop_custom_toolbar, null);

        View buttonInquiry = root.findViewById(R.id.buttonInquiry);
        buttonInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInquiry(activity);
            }
        });
        return root;
    }
}
