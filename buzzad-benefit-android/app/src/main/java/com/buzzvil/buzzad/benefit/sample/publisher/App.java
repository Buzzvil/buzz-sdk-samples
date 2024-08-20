package com.buzzvil.buzzad.benefit.sample.publisher;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.presentation.feed.BuzzAdFeed;
import com.buzzvil.buzzad.benefit.presentation.feed.BuzzAdFeedTheme;
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig;
import com.buzzvil.buzzad.benefit.sample.publisher.feed.CustomAdsAdapter;
import com.buzzvil.buzzad.benefit.sample.publisher.feed.CustomFeedHeaderViewAdapter;
import com.buzzvil.buzzad.benefit.sample.publisher.feed.CustomFeedToolbarHolder;

public class App extends Application {
    public static final String UNIT_ID_NATIVE_AD = "YOUR_NATIVE_AD_UNIT_ID";
    public static final String UNIT_ID_FEED = "YOUR_FEED_UNIT_ID";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        final FeedConfig feedConfig = new FeedConfig.Builder(App.UNIT_ID_FEED)
                .adsAdapterClass(CustomAdsAdapter.class)
                .feedToolbarHolderClass(CustomFeedToolbarHolder.class)
                .feedHeaderViewAdapterClass(CustomFeedHeaderViewAdapter.class)
                .imageTypeEnabled(true)
                .build();
        BuzzAdBenefitConfig buzzAdBenefitConfig = new BuzzAdBenefitConfig.Builder(this)
                .setDefaultFeedConfig(feedConfig)
                .build();
        BuzzAdBenefit.init(this, buzzAdBenefitConfig);

        final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                .userId("SAMPLE_USER_ID")
                .gender(UserProfile.Gender.MALE)
                .birthYear(1985)
                .build();

        setFeedTheme();

        BuzzAdBenefit.setUserProfile(userProfile);
    }

    private void setFeedTheme() {
        // BuzzAdFeedTheme 설정을 통해 색상 등 UI 구성 요소를 변경할 수 있습니다.
        final BuzzAdFeedTheme buzzAdFeedTheme =
                BuzzAdFeedTheme.getDefault();

        buzzAdFeedTheme.backgroundColor(R.color.YOUR_BACKGROUND_COLOR);

        buzzAdFeedTheme.tabTextColorSelector(R.color.YOUR_TEXT_COLOR) // 탭의 텍스트 색상(state_selected 필수 적용)
                .tabBackgroundColor(R.color.YOUR_BACKGROUND_COLOR) // 탭의 배경 색상
                .tabIndicatorSelector(R.drawable.custom_tab_indicator_seletor); // 탭의 인디케이터에 대한 Selector
        BuzzAdFeed.setDefaultTheme(buzzAdFeedTheme);
    }
}
