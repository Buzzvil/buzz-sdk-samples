package com.buzzvil.sample.buzzvilsdksample.nativead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buzzvil.buzzad.benefit.BaseRewardManager
import com.buzzvil.buzzad.benefit.BuzzAdBenefit
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2ViewBinder
import com.buzzvil.sample.buzzvilsdksample.Constant
import com.buzzvil.sample.buzzvilsdksample.databinding.ActivityNativeSimpleBinding

/**
 * 네이티브 기본 구현 예제입니다.
 */
class NativeSimpleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNativeSimpleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeSimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadSimpleNativeAdButton.setOnClickListener {
            loadNativeAd()
            setNativeToFeed()
            setNativeOverlay()
        }
    }

    override fun onResume() {
        super.onResume()
        updateNativeToFeedText(Constant.YOUR_FEED_UNIT_ID)
    }

    private fun loadNativeAd() {
        val nativeAd2View = binding.simpleNativeAdLayout.nativeAdView
        val mediaView = binding.simpleNativeAdLayout.adMediaView
        val titleTextView = binding.simpleNativeAdLayout.adTitleText
        val descriptionTextView = binding.simpleNativeAdLayout.adDescriptionText
        val iconImageView = binding.simpleNativeAdLayout.adIconImage
        val ctaView = binding.simpleNativeAdLayout.adCtaView

        // 광고 레이아웃을 설정합니다.
        val binder: NativeAd2ViewBinder = NativeAd2ViewBinder.Builder()
            .nativeAd2View(nativeAd2View)
            .mediaView(mediaView)
            .titleTextView(titleTextView)
            .descriptionTextView(descriptionTextView)
            .iconImageView(iconImageView)
            .ctaView(ctaView)
            .build(Constant.YOUR_NATIVE_UNIT_ID)

        // 광고 할당 및 표시를 자동으로 수행합니다.
        binder.bind()
    }

    private fun setNativeToFeed() {
        binding.simpleNativeAdLayout.nativeToFeedLayout.setNativeUnitId(Constant.YOUR_NATIVE_UNIT_ID)

        BuzzAdBenefit.getBaseRewardManager()?.getAvailableFeedBaseReward(
            Constant.YOUR_FEED_UNIT_ID,
            object : BaseRewardManager.BaseRewardListener {
                override fun onBaseRewardLoaded(reward: Int) {
                    if (reward < 1) {
                        binding.simpleNativeAdLayout.nativeToFeedText.text = "더 많은 참여 기회 보기"
                    } else {
                        binding.simpleNativeAdLayout.nativeToFeedText.text =
                            "$reward 포인트 받고 더 많은 참여 기회 보기"
                    }
                }
            })
    }

    private fun setNativeOverlay() {
        val nativeAdView = binding.simpleNativeAdLayout.nativeAdView
        nativeAdView.enableNativeToFeedOverlay()
    }

    private fun updateNativeToFeedText(feedUnitId: String) {
        BuzzAdBenefit.getBaseRewardManager()
            ?.getAvailableFeedBaseReward(feedUnitId, object : BaseRewardManager.BaseRewardListener {
                override fun onBaseRewardLoaded(reward: Int) {
                    if (reward < 1) {
                        binding.simpleNativeAdLayout.nativeToFeedText.text = "더 많은 참여 기회 보기"
                    } else {
                        binding.simpleNativeAdLayout.nativeToFeedText.text =
                            "$reward 포인트 받고 더 많은 참여 기회 보기"
                    }
                }
            })
    }
}
