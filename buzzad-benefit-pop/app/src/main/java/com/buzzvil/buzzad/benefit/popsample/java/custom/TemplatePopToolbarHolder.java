package com.buzzvil.buzzad.benefit.popsample.java.custom;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;

import com.buzzvil.buzzad.benefit.pop.toolbar.DefaultPopToolbarHolder;
import com.buzzvil.buzzad.benefit.pop.toolbar.PopMenuImageView;
import com.buzzvil.buzzad.benefit.pop.toolbar.PopToolbar;
import com.buzzvil.buzzad.benefit.popsample.R;
import com.buzzvil.buzzad.benefit.popsample.java.App;

public class TemplatePopToolbarHolder extends DefaultPopToolbarHolder {

    @Override
    public View getView(Activity activity, @NonNull String unitId) {
        toolbar = new PopToolbar(activity);
        toolbar.setTitle("TemplatePopToolbarHolder");
        toolbar.setBackgroundColor(Color.LTGRAY);

        addInquiryMenuItemView(activity, App.UNIT_ID_POP);
        addRightMenuItemView1(activity);
        return toolbar;
    }

    private void addRightMenuItemView1(@NonNull final Activity activity) {
        PopMenuImageView menuItemView = toolbar.buildPopMenuItemView(activity, R.drawable.ic_notification_pop_gift);
        menuItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.buzzvil.com/ko/main";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                activity.startActivity(intent);
            }
        });
        toolbar.addRightMenuButton(menuItemView);
    }
}
