package com.buzzvil.sample.buzzvilsdksample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.buzzvil.buzzad.benefit.annotation.BuzzExperimentalApi
import com.buzzvil.buzzad.benefit.core.ad.AdError
import com.buzzvil.buzzad.benefit.core.models.UserProfile
import com.buzzvil.buzzad.benefit.pop.BuzzAdPop
import com.buzzvil.buzzad.benefit.presentation.feed.BuzzAdFeed
import com.buzzvil.buzzad.benefit.presentation.feed.FeedConfig
import com.buzzvil.buzzad.benefit.presentation.feed.FeedInitialNavigationPage
import com.buzzvil.buzzad.benefit.presentation.interstitial.BuzzAdInterstitial
import com.buzzvil.buzzad.benefit.presentation.interstitial.InterstitialAdListener
import com.buzzvil.sample.buzzvilsdksample.benefithub.BenefitHubFragmentActivity
import com.buzzvil.sample.buzzvilsdksample.custom.CustomBenefitHubAdViewAdapter
import com.buzzvil.sample.buzzvilsdksample.custom.CustomFeedHeaderViewAdapter
import com.buzzvil.sample.buzzvilsdksample.databinding.ActivityMainBinding
import com.buzzvil.sample.buzzvilsdksample.nativead.NativeCarouselActivity
import com.buzzvil.sample.buzzvilsdksample.nativead.NativeCustomActivity
import com.buzzvil.sample.buzzvilsdksample.nativead.NativeSimpleActivity
import com.buzzvil.sdk.BuzzMissionPack
import com.buzzvil.sdk.BuzzvilSdk
import com.buzzvil.sdk.BuzzvilSetUserProfileListener
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    private var buzzAdFeed: BuzzAdFeed? = null
    private fun getBuzzAdFeed(): BuzzAdFeed {
        if (buzzAdFeed == null) {
            // 하나의 피드에 하나의 buzzAdFeed 인스턴스를 생성하여 사용합니다.
            buzzAdFeed = BuzzAdFeed.Builder().build()
        }

        return buzzAdFeed!!
    }

    private var customBuzzAdFeed: BuzzAdFeed? = null

    @OptIn(BuzzExperimentalApi::class) // 실험 기능을 사용하기 위한 어노테이션
    private fun getCustomBuzzAdFeed(): BuzzAdFeed {
        if (customBuzzAdFeed == null) {
            val feedConfig = FeedConfig.Builder(Constant.YOUR_FEED_UNIT_ID)
                .navigationBarVisibility(false) // 기본 내비게이션 바 제거하기
                .feedHeaderViewAdapterClass(CustomFeedHeaderViewAdapter::class.java) // 커스텀 헤더 UI 적용하기
                .benefitHubAdViewAdapterClass(CustomBenefitHubAdViewAdapter::class.java) // 커스텀 광고 UI 적용하기
                .initialSelectedFilterName("쇼핑적립") // (실험 기능) 초기 필터 선택하기
                .initialNavigationPage(FeedInitialNavigationPage.LUCKYBOX) // (실험 기능) 초기 페이지 선택하기
                .build()

            // 하나의 피드에 하나의 buzzAdFeed 인스턴스를 생성하여 사용합니다.
            customBuzzAdFeed = BuzzAdFeed.Builder()
                .feedConfig(feedConfig)
                .build()
        }

        return customBuzzAdFeed!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sdkVersionTextView.text = "SDK Version: ${BuzzvilSdk.versionName}"

        binding.loginButton.setOnClickListener {
            login()
        }

        binding.logoutButton.setOnClickListener {
            logout()
            // logoutWithListener()
        }

        binding.showBenefitHubButton.setOnClickListener {
            getBuzzAdFeed().show(this@MainActivity)
        }

        binding.showCustomBenefitHubButton.setOnClickListener {
            getCustomBuzzAdFeed().show(this@MainActivity)
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

        binding.nativeSimpleButton.setOnClickListener {
            val intent = Intent(this, NativeSimpleActivity::class.java)
            startActivity(intent)
        }

        binding.nativeCustomButton.setOnClickListener {
            val intent = Intent(this, NativeCustomActivity::class.java)
            startActivity(intent)
        }

        binding.nativeCarouselButton.setOnClickListener {
            val intent = Intent(this, NativeCarouselActivity::class.java)
            startActivity(intent)
        }

        binding.interstitialDialogButton.setOnClickListener {
            showInterstitialDialog()
        }

        binding.interstitialBottomSheetButton.setOnClickListener {
            showInterstitialBottomSheet()
        }

        binding.activatePopButton.setOnClickListener {
            activatePop()
        }

        binding.deactivatePopButton.setOnClickListener {
            deactivatePop()
        }

        binding.showMissionPackSisButton.setOnClickListener {
            showMissionPackSis()
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
                    Toast.makeText(
                        this@MainActivity,
                        "onFailure: ${errorType.name}",
                        Toast.LENGTH_SHORT
                    ).show()
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
                    Toast.makeText(
                        this@MainActivity,
                        "onFailure: ${errorType.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
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
                Toast.makeText(
                    this@MainActivity,
                    "onError: ${error?.adErrorType?.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun showInterstitialDialog() {
        val buzzAdInterstitial =
            BuzzAdInterstitial.Builder(Constant.YOUR_INTERSTITIAL_UNIT_ID).buildDialog()
        buzzAdInterstitial.load(object : InterstitialAdListener() {
            override fun onAdLoaded() {
                // 할당된 광고가 있으면 호출됩니다.
                // Interstitial 광고를 화면에 표시합니다.
                buzzAdInterstitial.show(this@MainActivity)
            }

            override fun onAdLoadFailed(error: AdError?) {
                // 할당된 광고가 없으면 호출됩니다.
                Log.e(TAG, "Failed to load a interstitial ad.", error);
            }

            override fun onAdClosed() {
                super.onAdClosed()
                // Interstitial 지면이 종료되면 호출됩니다.
                // 필요에 따라 추가 기능을 구현하세요.
            }
        })
    }

    private fun showInterstitialBottomSheet() {
        val buzzAdInterstitial =
            BuzzAdInterstitial.Builder(Constant.YOUR_INTERSTITIAL_UNIT_ID).buildBottomSheet()
        buzzAdInterstitial.load(object : InterstitialAdListener() {
            override fun onAdLoaded() {
                // 할당된 광고가 있으면 호출됩니다.
                // Interstitial 광고를 화면에 표시합니다.
                buzzAdInterstitial.show(this@MainActivity)
            }

            override fun onAdLoadFailed(error: AdError?) {
                // 할당된 광고가 없으면 호출됩니다.
                Log.e(TAG, "Failed to load a interstitial ad.", error);
            }

            override fun onAdClosed() {
                super.onAdClosed()
                // Interstitial 지면이 종료되면 호출됩니다.
                // 필요에 따라 추가 기능을 구현하세요.
            }
        })
    }

    private fun activatePop() {
        BuzzAdPop.getInstance().activate(object : BuzzAdPop.PopActivateListener {
            override fun onActivated() {
                // 정상적으로 Pop이 활성화 되었을 때 호출됩니다.
                // 유저 화면에 바로 Pop을 표시합니다.
                BuzzAdPop.getInstance().show()
            }

            override fun onActivationFailed(error: Throwable?) {
                // Pop 활성화에 실패하였을 때 호출됩니다.
                Toast.makeText(this@MainActivity, "Pop activation failed", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun deactivatePop() {
        BuzzAdPop.getInstance().deactivate(this)
    }

    private fun showMissionPackSis() {
        lifecycleScope.launch {
            if (BuzzMissionPack.isAvailable(unitId = Constant.YOUR_MISSION_PACK_UNIT_ID)) {
                BuzzMissionPack.showMissionPack(
                    unitId = Constant.YOUR_MISSION_PACK_UNIT_ID,
                    context = this@MainActivity
                )
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "MissionPack is not available",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
