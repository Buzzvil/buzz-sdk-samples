package com.buzzvil.buzzad.benefit.sample.publisher.nativead2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2StateChangedListener;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2View;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2ViewBinder;
import com.buzzvil.buzzad.benefit.presentation.media.DefaultCtaView;
import com.buzzvil.buzzad.benefit.presentation.media.MediaView;
import com.buzzvil.buzzad.benefit.sample.publisher.App;
import com.buzzvil.buzzad.benefit.sample.publisher.R;

public class NativeAd2Activity extends AppCompatActivity {

    private final String unitId = App.UNIT_ID_NATIVE_AD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad2);

        NativeAd2View nativeAd2View = findViewById(R.id.nativeAd2View);
        MediaView mediaView = findViewById(R.id.mediaView);
        TextView titleTextView = findViewById(R.id.textTitle);
        ImageView iconImageView = findViewById(R.id.imageIcon);
        TextView descriptionTextView = findViewById(R.id.textDescription);
        DefaultCtaView ctaView = findViewById(R.id.ctaView);
        View loadingView = findViewById(R.id.loadingView);
        TextView nativeAd2ViewStateTextView = findViewById(R.id.nativeAd2ViewStateTextView);

        // 광고 레이아웃을 설정합니다.
        NativeAd2ViewBinder binder = new NativeAd2ViewBinder.Builder()
                .nativeAd2View(nativeAd2View)
                .mediaView(mediaView)
                .titleTextView(titleTextView)
                .iconImageView(iconImageView)
                .descriptionTextView(descriptionTextView)
                .ctaView(ctaView)
                .build(unitId);

        // (Optional) 광고 요청 상태에 따른 UI를 구현합니다.
        binder.addNativeAd2StateChangedListener(new NativeAd2StateChangedListener() {
            @Override
            public void onRequested() {
                // 광고 할당을 요청한 상태입니다. 잠시 후 onSuccess() 또는 onError()가 호출됩니다.
                // 로딩 화면 등을 구현할 수 있습니다.
                loadingView.setVisibility(View.VISIBLE);
                nativeAd2ViewStateTextView.setText("onRequested");
            }

            @Override
            public void onSuccess() {
                // 광고 할당에 성공하면 호출됩니다.
                // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                loadingView.setVisibility(View.GONE);
                nativeAd2ViewStateTextView.setText("onSuccess");
            }

            @Override
            public void onError(@NonNull AdError adError) {
                // 광고 할당에 실패하면 호출됩니다.
                // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                loadingView.setVisibility(View.GONE);
                nativeAd2ViewStateTextView.setText("onError: " + adError.getAdErrorType().name());
            }
        });

        // 광고 할당 및 표시를 자동으로 수행합니다.
        binder.bind();
    }
}
