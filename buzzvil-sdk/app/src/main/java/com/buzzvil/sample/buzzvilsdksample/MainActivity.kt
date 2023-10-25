package com.buzzvil.sample.buzzvilsdksample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.buzzad.benefit.BaseRewardManager
import com.buzzvil.buzzad.benefit.BuzzAdBenefit
import com.buzzvil.buzzad.benefit.core.ad.AdError
import com.buzzvil.buzzad.benefit.core.models.UserProfile
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2EventListener
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2Pool
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2PoolInitListener
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2StateChangedListener
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2View
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2ViewBinder
import com.buzzvil.buzzad.benefit.presentation.feed.BuzzAdFeed
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotionFactory
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult
import com.buzzvil.buzzad.benefit.presentation.video.VideoErrorStatus
import com.buzzvil.buzzad.benefit.presentation.video.VideoEventListener
import com.buzzvil.sample.buzzvilsdksample.carousel.NativeAd2CarouselAdapter
import com.buzzvil.sample.buzzvilsdksample.carousel.NativeAd2CarouselItem
import com.buzzvil.sample.buzzvilsdksample.carousel.NativeAd2CarouselItem.CarouselToFeedSlideItem
import com.buzzvil.sample.buzzvilsdksample.carousel.NativeAd2CarouselItem.NativeAd2Item
import com.buzzvil.sample.buzzvilsdksample.carousel.PaddingDividerDecoration
import com.buzzvil.sample.buzzvilsdksample.databinding.ActivityMainBinding
import com.buzzvil.sdk.BuzzvilSdk
import com.buzzvil.sdk.BuzzvilSetUserProfileListener


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var buzzAdFeed: BuzzAdFeed? = null
    private fun getBuzzAdFeed(): BuzzAdFeed {
        if (buzzAdFeed == null) {
            // 하나의 피드에 하나의 buzzAdFeed 인스턴스를 생성하여 사용합니다.
            buzzAdFeed = BuzzAdFeed.Builder().build()
        }

        return buzzAdFeed!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            login()
        }

        binding.logoutButton.setOnClickListener {
            logout()
            // logoutWithListener()
        }

        binding.showBenefitHubButton.setOnClickListener {
            showBenefitHub()
        }

        binding.getAvailableRewardsButton.setOnClickListener {
            getAvailableRewards()
        }

        binding.benefitHubFragmentButton.setOnClickListener {
            val intent = Intent(this, BenefitHubFragmentActivity::class.java)
            startActivity(intent)
        }

        binding.loadAdsButton.setOnClickListener {
            loadAds()
        }

        binding.loadSimpleNativeAdButton.setOnClickListener {
            loadSimpleNativeAd()
            simpleNativeToFeed()
            setNativeOverlay(binding.simpleNativeAdLayout.nativeAdView)
        }

        binding.loadCardViewNativeAdButton.setOnClickListener {
            loadCardViewNativeAd()
            cardViewNativeToFeed()
            setNativeOverlay(binding.cardViewNativeAdLayout.nativeAdView)
        }

        binding.loadCarouselButton.setOnClickListener {
            initCarouselPool()
        }
    }

    private fun login() {
        // 유저 정보를 등록합니다.
        BuzzvilSdk.setUserProfile(
            userId = "SAMPLE_USER_ID",
            gender = UserProfile.Gender.MALE,
            birthYear = 1980,
            // (선택) 로그인 상태를 확인할 수 있는 리스너를 등록합니다.
            listener = object : BuzzvilSetUserProfileListener {
                override fun loggedIn() {
                    // 유저 정보가 정상적으로 등록된 경우 호출됩니다.
                    Toast.makeText(this@MainActivity, "loggedIn", Toast.LENGTH_SHORT).show()
                }

                override fun loggedOut() {
                    // 유저 정보를 삭제하는 경우 호출됩니다.
                    Toast.makeText(this@MainActivity, "loggedOut", Toast.LENGTH_SHORT).show()
                }

                override fun onSessionReady() {
                    // loggedIn() 이후에 버즈빌 서버에서 auth token을 정상적으로 받아오면 호출됩니다.
                    Toast.makeText(this@MainActivity, "onSessionReady", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(errorType: BuzzvilSetUserProfileListener.ErrorType) {
                    // 유저 정보를 정상적으로 등록하지 못한 경우 호출됩니다.
                    Toast.makeText(this@MainActivity, "onFailure: ${errorType.name}", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun logout() {
        // 유저 정보를 삭제합니다.
        BuzzvilSdk.setUserProfile(null)
    }

    private fun logoutWithListener() {
        BuzzvilSdk.setUserProfile(
            userId = null,
            // (선택) 로그인 상태를 확인할 수 있는 리스너를 등록합니다.
            listener = object : BuzzvilSetUserProfileListener {
                override fun loggedIn() {
                    // 유저 정보가 정상적으로 등록된 경우 호출됩니다.
                    Toast.makeText(this@MainActivity, "loggedIn", Toast.LENGTH_SHORT).show()
                }

                override fun loggedOut() {
                    // 유저 정보를 삭제하는 경우 호출됩니다.
                    Toast.makeText(this@MainActivity, "loggedOut", Toast.LENGTH_SHORT).show()
                }

                override fun onSessionReady() {
                    // loggedIn() 이후에 버즈빌 서버에서 auth token을 정상적으로 받아오면 호출됩니다.
                    Toast.makeText(this@MainActivity, "onSessionReady", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(errorType: BuzzvilSetUserProfileListener.ErrorType) {
                    // 유저 정보를 정상적으로 등록하지 못한 경우 호출됩니다.
                    Toast.makeText(this@MainActivity, "onFailure: ${errorType.name}", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun showBenefitHub() {
        getBuzzAdFeed().show(this@MainActivity)
    }

    private fun getAvailableRewards() {
        getBuzzAdFeed().load(object : BuzzAdFeed.FeedLoadListener {
            override fun onSuccess() {
                val availableReward = getBuzzAdFeed().getAvailableRewards()
                // 적립 가능한 포인트를 직접 구현한 UI에 업데이트합니다.
                binding.availableRewardTextView.text = "$availableReward 포인트 적립 가능!"
            }

            override fun onError(error: AdError?) {
                // 적립 가능한 포인트를 가져올 수 없는 경우
                binding.availableRewardTextView.text = "${error?.adErrorType?.name}"
            }
        })
    }

    private fun loadAds() {
        getBuzzAdFeed().load(object : BuzzAdFeed.FeedLoadListener {
            override fun onSuccess() {
                // 광고 재할당에 성공한 경우 호출됩니다.
                Toast.makeText(this@MainActivity, "onSuccess", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: AdError?) {
                // 광고 재할당에 실패한 경우 호출됩니다.
                Toast.makeText(this@MainActivity, "onError: ${error?.adErrorType?.name}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadSimpleNativeAd() {
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
            .build(Constant.YOUR_NATIVE_ID)

        // 광고 할당 및 표시를 자동으로 수행합니다.
        binder.bind()
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
            .build(Constant.YOUR_NATIVE_ID)

        // (Optional) 광고 요청 상태에 따른 UI를 구현합니다.
        binder.addNativeAd2StateChangedListener(object : NativeAd2StateChangedListener {
            override fun onRequested() {
                // 광고 할당을 요청한 상태입니다.
                // 이후에는 onNext(), onComplete(), onError() 중 하나가 호출됩니다.
                // 광고 자동 갱신을 시도할 때마다 반복적으로 호출됩니다.
                // 로딩 화면 등을 구현할 수 있습니다.
                Toast.makeText(this@MainActivity, "onRequested", Toast.LENGTH_SHORT).show()
            }

            override fun onNext(nativeAd2: NativeAd2) {
                // 광고 할당에 성공하면 호출됩니다.
                // 이후에 광고 갱신 시 onRequested()가 다시 호출됩니다.
                // 광고 자동 갱신을 성공할 때마다 반복적으로 호출됩니다.
                // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                Toast.makeText(this@MainActivity, "onNext", Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {
                // 더 이상 갱신할 수 있는 광고가 없을 때 호출됩니다.
                // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                Toast.makeText(this@MainActivity, "onComplete", Toast.LENGTH_SHORT).show()
            }

            override fun onError(adError: AdError) {
                // 최초 광고 할당에 실패하면 호출됩니다.
                // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                Toast.makeText(this@MainActivity, "onError: ${adError.adErrorType.name}", Toast.LENGTH_SHORT).show()
            }
        })

        // (Optional) 동영상 광고 리스너 등록하기
        mediaView.setVideoEventListener(object : VideoEventListener {
            override fun onError(videoErrorStatus: VideoErrorStatus, errorMessage: String?) {
                errorMessage?.let {
                    Toast.makeText(this@MainActivity, "Video onError: $it", Toast.LENGTH_SHORT).show()
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

    private fun simpleNativeToFeed() {
        binding.simpleNativeAdLayout.nativeToFeedLayout.setNativeUnitId(Constant.YOUR_NATIVE_ID)

        BuzzAdBenefit.getBaseRewardManager()?.getAvailableFeedBaseReward(Constant.YOUR_FEED_ID, object : BaseRewardManager.BaseRewardListener {
            override fun onBaseRewardLoaded(reward: Int) {
                if (reward < 1) {
                    binding.simpleNativeAdLayout.nativeToFeedText.text = "더 많은 참여 기회 보기"
                } else {
                    binding.simpleNativeAdLayout.nativeToFeedText.text = "$reward 포인트 받고 더 많은 참여 기회 보기"
                }
            }
        })
    }

    private fun cardViewNativeToFeed() {
        binding.cardViewNativeAdLayout.nativeToFeedLayout.setNativeUnitId(Constant.YOUR_NATIVE_ID)

        BuzzAdBenefit.getBaseRewardManager()?.getAvailableFeedBaseReward(Constant.YOUR_FEED_ID, object : BaseRewardManager.BaseRewardListener {
            override fun onBaseRewardLoaded(reward: Int) {
                if (reward < 1) {
                    binding.cardViewNativeAdLayout.nativeToFeedText.text = "더 많은 참여 기회 보기"
                } else {
                    binding.cardViewNativeAdLayout.nativeToFeedText.text = "$reward 포인트 받고 더 많은 참여 기회 보기"
                }
            }
        })
    }

    private fun setNativeOverlay(nativeAdView: NativeAd2View) {
        nativeAdView.enableNativeToFeedOverlay()
    }

    private fun initCarouselPool() {
        // 광고 중복 할당을 막기 위해 하나의 캐러셀에 하나의 NativeAd2Pool 인스턴스를 생성하여 사용합니다.
        val carouselPool = NativeAd2Pool(Constant.YOUR_NATIVE_ID)
        val REQUEST_AD_COUNT = 5

        // 현재 할당 받을 수 있는 광고의 갯수를 확인합니다.
        carouselPool.init(REQUEST_AD_COUNT, object : NativeAd2PoolInitListener {
            override fun onLoaded(adCount: Int) {
                // 어댑터를 초기화합니다.
                initCarouselAdapter(adCount, carouselPool)

                binding.carouselRecyclerView.visibility = View.VISIBLE
                binding.toFeedLink.visibility = View.VISIBLE
            }

            override fun onError(adError: AdError) {
                // TODO 광고 레이아웃을 숨김 처리 하는 등 적절한 에러 처리를 할 수 있습니다.
                binding.carouselRecyclerView.visibility = View.GONE
                binding.toFeedLink.visibility = View.GONE
            }
        })
    }

    private fun initCarouselAdapter(adCount: Int, carouselPool: NativeAd2Pool) {
        // 무한 스크롤을 적용합니다.
        val isInfiniteLoopEnabled = true

        // 할당 받은 광고 갯수(adCount) 크기의 아이템 리스트를 만들어 NativeAd2CarouselAdapter를 생성합니다.
        val list = buildCarouselItems(adCount)
        val adapter = NativeAd2CarouselAdapter(list, carouselPool, isInfiniteLoopEnabled)

        // RecyclerView에 어댑터를 설정합니다.
        binding.carouselRecyclerView.adapter = adapter

        // 양 옆 여백 설정
        binding.carouselRecyclerView.addItemDecoration(PaddingDividerDecoration(16))

        if (isInfiniteLoopEnabled) {
            // 적당히 큰 수의 position으로 이동하여 무한 스크롤 효과를 구현합니다.
            binding.carouselRecyclerView.layoutManager?.scrollToPosition(list.size * 10000)
        }
    }

    private fun buildCarouselItems(adCount: Int): List<NativeAd2CarouselItem> {
        // 마지막 페이지에 CarouselToFeedSlideItem을 추가하기 위해 1개 더 큰 사이즈로 배열을 생성합니다.
        val carouselItems = arrayOfNulls<NativeAd2CarouselItem>(adCount + 1)

        // 배열을 NativeAd2Item으로 채웁니다.
        carouselItems.fill(NativeAd2Item)

        // 마지막 아이템은 CarouselToFeedSlideItem으로 설정합니다.
        val carouselToFeedSlideItem = CarouselToFeedSlideItem(FeedPromotionFactory(Constant.YOUR_NATIVE_ID).buildForCarousel())
        carouselItems[carouselItems.size - 1] = carouselToFeedSlideItem

        return carouselItems.toList().filterNotNull()
    }
}
