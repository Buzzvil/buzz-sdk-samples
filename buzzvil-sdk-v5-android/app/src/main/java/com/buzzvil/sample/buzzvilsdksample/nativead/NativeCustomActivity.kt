package com.buzzvil.sample.buzzvilsdksample.nativead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.buzzvil.buzzad.benefit.BaseRewardManager
import com.buzzvil.buzzad.benefit.BuzzAdBenefit
import com.buzzvil.buzzad.benefit.core.ad.AdError
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2EventListener
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2StateChangedListener
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2ViewBinder
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult
import com.buzzvil.buzzad.benefit.presentation.video.VideoErrorStatus
import com.buzzvil.buzzad.benefit.presentation.video.VideoEventListener
import com.buzzvil.sample.buzzvilsdksample.Constant
import com.buzzvil.sample.buzzvilsdksample.databinding.ActivityNativeCustomBinding

/**
 * 네이티브 기본 구현에 커스텀 레이아웃, 리스너 등을 추가한 예제입니다.
 */
class NativeCustomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNativeCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadCardViewNativeAdButton.setOnClickListener {
            loadCardViewNativeAd()
            cardViewNativeToFeed()
            setNativeOverlay()
        }
    }

    override fun onResume() {
        super.onResume()
        updateNativeToFeedText(Constant.YOUR_FEED_UNIT_ID)
    }

    private fun loadCardViewNativeAd() {
        val nativeAd2View = binding.cardViewNativeAdLayout.nativeAdView
        val mediaView = binding.cardViewNativeAdLayout.adMediaView
        val titleTextView = binding.cardViewNativeAdLayout.adTitleText
        val descriptionTextView = binding.cardViewNativeAdLayout.adDescriptionText
        val iconImageView = binding.cardViewNativeAdLayout.adIconImage
        val ctaView = binding.cardViewNativeAdLayout.adCtaView

        // 광고 레이아웃을 설정합니다.
        val binder: NativeAd2ViewBinder = NativeAd2ViewBinder.Builder()
            .nativeAd2View(nativeAd2View)
            .mediaView(mediaView)
            .titleTextView(titleTextView)
            .descriptionTextView(descriptionTextView)
            .iconImageView(iconImageView)
            .ctaView(ctaView)
            .build(Constant.YOUR_NATIVE_UNIT_ID)

        // (Optional) 광고 요청 상태에 따른 UI를 구현합니다.
        binder.addNativeAd2StateChangedListener(object : NativeAd2StateChangedListener {
            override fun onRequested() {
                // 광고 할당을 요청한 상태입니다.
                // 이후에는 onNext(), onComplete(), onError() 중 하나가 호출됩니다.
                // 광고 자동 갱신을 시도할 때마다 반복적으로 호출됩니다.
                // 로딩 화면 등을 구현할 수 있습니다.
                Toast.makeText(this@NativeCustomActivity, "onRequested", Toast.LENGTH_SHORT).show()
            }

            override fun onNext(nativeAd2: NativeAd2) {
                // 광고 할당에 성공하면 호출됩니다.
                // 이후에 광고 갱신 시 onRequested()가 다시 호출됩니다.
                // 광고 자동 갱신을 성공할 때마다 반복적으로 호출됩니다.
                // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                Toast.makeText(this@NativeCustomActivity, "onNext", Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {
                // 더 이상 갱신할 수 있는 광고가 없을 때 호출됩니다.
                // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                Toast.makeText(this@NativeCustomActivity, "onComplete", Toast.LENGTH_SHORT).show()
            }

            override fun onError(adError: AdError) {
                // 최초 광고 할당에 실패하면 호출됩니다.
                // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                Toast.makeText(this@NativeCustomActivity, "onError: ${adError.adErrorType.name}", Toast.LENGTH_SHORT).show()
            }
        })

        // (Optional) 동영상 광고 리스너 등록하기
        mediaView.setVideoEventListener(object : VideoEventListener {
            override fun onError(videoErrorStatus: VideoErrorStatus, errorMessage: String?) {
                errorMessage?.let {
                    Toast.makeText(this@NativeCustomActivity, "Video onError: $it", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onVideoStarted() {}

            override fun onResume() {}

            override fun onPause() {}

            override fun onReplay() {}

            override fun onLanding() {
                // 동영상 광고 랜딩 시 필요한 처리
            }

            override fun onVideoEnded() {
                // 동영상 재생 완료 시 필요한 처리
            }
        })

        // (Optional) 광고 이벤트 리스너를 등록합니다.
        // 로그 기록, 단순 알림 외에 다른 동작을 추가하는 것을 권장하지 않습니다. 자동 갱신 등 네이티브 2.0의 기능과 직접 구현한 동작이 충돌할 수 있습니다.
        binder.addNativeAd2EventListener(object : NativeAd2EventListener {
            override fun onImpressed(nativeAd2: NativeAd2) {
                // Native 광고가 유저에게 노출되었을 때 호출됩니다.
            }

            override fun onClicked(nativeAd2: NativeAd2) {
                // 유저가 Native 광고를 클릭했을 때 호출됩니다.
            }

            override fun onRewardRequested(nativeAd2: NativeAd2) {
                // 리워드 적립을 요청했을 때 호출됩니다.
            }

            override fun onParticipated(nativeAd2: NativeAd2) {
                // 유저가 광고 참여를 완료하였을 때 호출됩니다.
            }

            override fun onRewarded(nativeAd2: NativeAd2, rewardResult: RewardResult) {
                // 리워드가 적립되었을 때 호출됩니다.
            }
        })

        // 광고 할당 및 표시를 자동으로 수행합니다.
        binder.bind()
    }

    private fun cardViewNativeToFeed() {
        binding.cardViewNativeAdLayout.nativeToFeedLayout.setNativeUnitId(Constant.YOUR_NATIVE_UNIT_ID)

        BuzzAdBenefit.getBaseRewardManager()?.getAvailableFeedBaseReward(Constant.YOUR_FEED_UNIT_ID, object : BaseRewardManager.BaseRewardListener {
            override fun onBaseRewardLoaded(reward: Int) {
                if (reward < 1) {
                    binding.cardViewNativeAdLayout.nativeToFeedText.text = "더 많은 참여 기회 보기"
                } else {
                    binding.cardViewNativeAdLayout.nativeToFeedText.text = "$reward 포인트 받고 더 많은 참여 기회 보기"
                }
            }
        })
    }

    private fun setNativeOverlay() {
        val nativeAdView = binding.cardViewNativeAdLayout.nativeAdView
        nativeAdView.enableNativeToFeedOverlay()
    }

    private fun updateNativeToFeedText(feedUnitId: String) {
        BuzzAdBenefit.getBaseRewardManager()?.getAvailableFeedBaseReward(feedUnitId, object : BaseRewardManager.BaseRewardListener {
            override fun onBaseRewardLoaded(reward: Int) {
                if (reward < 1) {
                    binding.cardViewNativeAdLayout.nativeToFeedText.text = "더 많은 참여 기회 보기"
                } else {
                    binding.cardViewNativeAdLayout.nativeToFeedText.text = "$reward 포인트 받고 더 많은 참여 기회 보기"
                }
            }
        })
    }
}
