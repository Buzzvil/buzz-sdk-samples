package com.buzzvil.buzzscreen.sample_lock;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

import com.buzzvil.buzzscreen.sdk.BaseLockerActivity;
import com.buzzvil.buzzscreen.sdk.Campaign;
import com.buzzvil.buzzscreen.sdk.widget.Slider;
import com.buzzvil.buzzscreen.sdk.widget.SliderIcon;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by patrick on 2017. 12. 8..
 */

public class CustomLockerActivity extends BaseLockerActivity {
    final static String TAG = "LockerActivity";

    // Slider at the bottom of the screen
    // 화면 하단 슬라이더
    Slider slider;

    // clock
    // 시계
    TextView tvTime;
    TextView tvAmPm;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_locker);

        initUI();
    }

    private void initUI() {
        tvTime = (TextView)findViewById(R.id.locker_time);
        tvAmPm = (TextView)findViewById(R.id.locker_am_pm);
        tvDate = (TextView)findViewById(R.id.locker_date);
        tvTime.setTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL));

        slider = (Slider)findViewById(R.id.locker_slider);

        // Listener that is called when the left icon in the slider is selected (touch up in the left icon position)
        // 슬라이더에서 왼쪽 아이콘이 선택(왼쪽 아이콘 위치에서 터치 업)되었을 때 호출되는 리스너
        slider.setLeftOnSelectListener(new SliderIcon.OnSelectListener() {
            @Override
            public void onSelect() {
                landing();
            }
        });

        // Listener that is called when the right icon in the slider is selected (touch up in the right icon position)
        // 슬라이더에서 오른쪽 아이콘이 선택(오른쪽 아이콘 위치에서 터치 업)되었을 때 호출되는 리스너
        slider.setRightOnSelectListener(new SliderIcon.OnSelectListener() {
            @Override
            public void onSelect() {
                unlock();
            }
        });

        // Display up/down arrow when touching the screen
        // 화면 터치시 상하 페이지 화살표 표시
        setPageIndicators(
                findViewById(R.id.locker_arrow_top),
                findViewById(R.id.locker_arrow_bottom)
        );

    }

    @Override
    protected void onCurrentCampaignUpdated(Campaign campaign) {
        // This is called when the current campaign is updated.
        // If you want to change UI according to the current campaign, you can write the code here.
        // 현재 보여지고 있는 캠페인이 업데이트 될때 호출됩니다.
        // 현재 캠페인에 따라 UI를 변화시키고 싶으면 여기서 작업하면 됩니다.
        Log.i(TAG, campaign.toString());

        // Update UI when left and right points are changed
        // 좌우 포인트가 변경되었을 때 UI 업데이트
        int landingPoints = campaign.getLandingPoints();
        int unlockPoints = campaign.getUnlockPoints();

        if (landingPoints > 0) {
            slider.setLeftText(String.format("+ %d", campaign.getLandingPoints()));
        } else {
            slider.setLeftText("");
        }

        if (unlockPoints > 0) {
            slider.setRightText(String.format("+ %d", campaign.getUnlockPoints()));
        } else {
            slider.setRightText("");
        }
    }

    @Override
    protected void onTimeUpdated(Calendar cal) {
        Date now = cal.getTime();
        // set time
        String time = new SimpleDateFormat("h:mm", Locale.getDefault()).format(now);
        tvTime.setText(time);

        // set am pm
        String am_pm = new SimpleDateFormat("aa", Locale.US).format(now);
        tvAmPm.setText(am_pm);

        // set date
        String dateTemplate;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            dateTemplate = DateFormat.getBestDateTimePattern(Locale.getDefault(), "E d MMM");
        } else {
            Locale locale = Locale.getDefault();
            if (locale.equals(Locale.KOREA)) {
                dateTemplate = "MMM d일 EEEE";
            } else if (locale.equals(Locale.JAPAN)) {
                dateTemplate = "MMM d日 EEEE";
            } else if (locale.equals(Locale.US)) {
                dateTemplate = "E, MMM d";
            } else {
                dateTemplate = "E, d MMM";
            }
        }
        String date = new SimpleDateFormat(dateTemplate, Locale.getDefault()).format(now);
        tvDate.setText(date);
    }
}
