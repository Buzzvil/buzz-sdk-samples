package com.buzzvil.buzzad.benefit.sample.publisher.nativead2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.buzzvil.buzzad.benefit.core.ad.AdError;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2EventListener;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2StateChangedListener;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2View;
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2ViewBinder;
import com.buzzvil.buzzad.benefit.presentation.media.DefaultCtaView;
import com.buzzvil.buzzad.benefit.presentation.media.MediaView;
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult;
import com.buzzvil.buzzad.benefit.sample.publisher.App;
import com.buzzvil.buzzad.benefit.sample.publisher.R;

public class NativeAd2Activity extends AppCompatActivity {

    private final String unitId = App.UNIT_ID_NATIVE_AD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad2);

        TextView stateTextView = findViewById(R.id.stateTextView);
        TextView eventTextView = findViewById(R.id.eventTextView);

        // 네이티브 2.0을 단독으로 사용하는 예제입니다.
        NativeAd2View nativeAd2View = findViewById(R.id.nativeAd2View);
        MediaView mediaView = findViewById(R.id.mediaView);
        TextView titleTextView = findViewById(R.id.textTitle);
        ImageView iconImageView = findViewById(R.id.imageIcon);
        TextView descriptionTextView = findViewById(R.id.textDescription);
        DefaultCtaView ctaView = findViewById(R.id.ctaView);
        View loadingView = findViewById(R.id.loadingView);

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
                // 광고 할당을 요청한 상태입니다.
                // 이후에는 onNext(), onComplete(), onError() 중 하나가 호출됩니다.
                // 광고 자동 갱신을 시도할 때마다 반복적으로 호출됩니다.
                // 로딩 화면 등을 구현할 수 있습니다.
                loadingView.setVisibility(View.VISIBLE);
                stateTextView.setText("state: onRequested");
            }

            @Override
            public void onNext(NativeAd2 nativeAd2) {
                // 광고 할당에 성공하면 호출됩니다.
                // 이후에 광고 갱신 시 onRequested()가 다시 호출됩니다.
                // 광고 자동 갱신을 성공할 때마다 반복적으로 호출됩니다.
                // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                loadingView.setVisibility(View.GONE);
                stateTextView.setText("state: onNext");
            }

            @Override
            public void onComplete() {
                // 더 이상 갱신할 수 있는 광고가 없을 때 호출됩니다.
                // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                loadingView.setVisibility(View.GONE);
                stateTextView.setText("state: onComplete");
            }

            @Override
            public void onError(@NonNull AdError adError) {
                // 광고 할당에 실패하면 호출됩니다.
                // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                loadingView.setVisibility(View.GONE);
                stateTextView.setText("state: onError (" + adError.getAdErrorType().name() + ")");
            }
        });

        binder.addNativeAd2EventListener(new NativeAd2EventListener() {
            @Override
            public void onImpressed(@NonNull NativeAd2 nativeAd2) {
                // Native 광고가 유저에게 노출되었을 때 호출됩니다.
                eventTextView.setText("event: onImpressed");
            }

            @Override
            public void onClicked(@NonNull NativeAd2 nativeAd2) {
                // 유저가 Native 광고를 클릭했을 때 호출됩니다.
                eventTextView.setText("event: onClicked");
            }

            @Override
            public void onRewardRequested(@NonNull NativeAd2 nativeAd2) {
                // 리워드 적립을 요청했을 때 호출됩니다.
                eventTextView.setText("event: onRewardRequested");
            }

            @Override
            public void onRewarded(@NonNull NativeAd2 nativeAd2, @NonNull RewardResult rewardResult) {
                // 리워드가 적립되었을 때 호출됩니다.
                eventTextView.setText("event: onRewarded, result: " + rewardResult.name());
            }

            @Override
            public void onParticipated(@NonNull NativeAd2 nativeAd2) {
                // 유저가 광고 참여를 완료하였을 때 호출됩니다.
                eventTextView.setText("event: onParticipated");
            }
        });

        // 광고 할당 및 표시를 자동으로 수행합니다.
        binder.bind();
    }
}
