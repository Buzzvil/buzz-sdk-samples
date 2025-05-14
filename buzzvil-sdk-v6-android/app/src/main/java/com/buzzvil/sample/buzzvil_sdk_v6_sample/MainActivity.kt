package com.buzzvil.sample.buzzvil_sdk_v6_sample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.buzzbenefit.BuzzAdError
import com.buzzvil.buzzbenefit.benefithub.BuzzBenefitHub
import com.buzzvil.buzzbenefit.benefithub.BuzzBenefitHubConfig
import com.buzzvil.buzzbenefit.benefithub.BuzzBenefitHubPage
import com.buzzvil.buzzbenefit.interstitial.BuzzInterstitial
import com.buzzvil.buzzbenefit.interstitial.BuzzInterstitialListener
import com.buzzvil.buzzbenefit.pop.BuzzPop
import com.buzzvil.buzzbenefit.pop.BuzzPopActivateListener
import com.buzzvil.sample.buzzvil_sdk_v6_sample.Constant.YOUR_USER_ID
import com.buzzvil.sample.buzzvil_sdk_v6_sample.banner.YourBannerActivity
import com.buzzvil.sample.buzzvil_sdk_v6_sample.buzzbenefithub.YourBenefitHubActivity
import com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative.YourNativeCarouselActivity
import com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative.YourNativeCustomActivity
import com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative.YourNativeSimpleActivity
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.ActivityMainBinding
import com.buzzvil.sdk.BuzzvilSdk
import com.buzzvil.sdk.BuzzvilSdkLoginListener
import com.buzzvil.sdk.BuzzvilSdkUser

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sdkVersionTextView.text = "SDK Version: ${BuzzvilSdk.versionName}"

        // User Profile
        binding.loginButton.setOnClickListener {
            login()
        }

        binding.logoutButton.setOnClickListener {
            // 유저 정보를 삭제합니다.
            BuzzvilSdk.logout()
            Toast.makeText(this@MainActivity, "loggedOut", Toast.LENGTH_SHORT).show()
        }

        // BenefitHub
        binding.showBenefitHubButton.setOnClickListener {
            BuzzBenefitHub.show(this)
        }

        binding.showBenefitHubFragmentButton.setOnClickListener {
            navigateActivity(YourBenefitHubActivity::class.java)
        }

        // SIS
        binding.showLuckyBoxSis.setOnClickListener {
            val benefitHubConfig = BuzzBenefitHubConfig.Builder()
                .routePath(BuzzBenefitHubPage.LUCKY_BOX.toRoutePath())
                .build()

            BuzzBenefitHub.show(context = this, benefitHubConfig = benefitHubConfig)
        }

        binding.showMissionPackSisButton.setOnClickListener {
            val benefitHubConfig = BuzzBenefitHubConfig.Builder()
                .routePath(BuzzBenefitHubPage.MISSION_PACK.toRoutePath())
                .build()

            BuzzBenefitHub.show(context = this, benefitHubConfig = benefitHubConfig)
        }

        binding.showHistorySisButton.setOnClickListener {
            val benefitHubConfig = BuzzBenefitHubConfig.Builder()
                .routePath(BuzzBenefitHubPage.HISTORY.toRoutePath())
                .build()

            BuzzBenefitHub.show(context = this, benefitHubConfig = benefitHubConfig)
        }

        // Native
        binding.nativeSimpleButton.setOnClickListener {
            navigateActivity(YourNativeSimpleActivity::class.java)
        }

        binding.nativeCustomButton.setOnClickListener {
            navigateActivity(YourNativeCustomActivity::class.java)
        }

        binding.nativeCarouselButton.setOnClickListener {
            navigateActivity(YourNativeCarouselActivity::class.java)
        }

        // Interstitial
        binding.interstitialDialogButton.setOnClickListener {
            val buzzAdInterstitial =
                BuzzInterstitial.Builder(Constant.YOUR_INTERSTITIAL_ID).buildDialog()

            buzzAdInterstitial?.load(object : BuzzInterstitialListener() {
                override fun onAdLoaded() {
                    // 할당된 광고가 있으면 호출됩니다.
                    // Interstitial 광고를 화면에 표시합니다.
                    buzzAdInterstitial.show(this@MainActivity)
                }

                override fun onAdLoadFailed(error: BuzzAdError?) {
                    // 할당된 광고가 없으면 호출됩니다.
                    Toast.makeText(
                        this@MainActivity,
                        "onFailure: ${error?.type?.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAdClosed() {
                    super.onAdClosed()
                    // Interstitial 지면이 종료되면 호출됩니다.
                    // 필요에 따라 추가 기능을 구현하세요.
                }
            })
        }

        binding.interstitialBottomSheetButton.setOnClickListener {
            val buzzAdInterstitial =
                BuzzInterstitial.Builder(Constant.YOUR_INTERSTITIAL_ID).buildBottomSheet()

            buzzAdInterstitial?.load(object : BuzzInterstitialListener() {
                override fun onAdLoaded() {
                    // 할당된 광고가 있으면 호출됩니다.
                    // Interstitial 광고를 화면에 표시합니다.
                    buzzAdInterstitial.show(this@MainActivity)
                }

                override fun onAdLoadFailed(error: BuzzAdError?) {
                    // 할당된 광고가 없으면 호출됩니다.
                    Toast.makeText(
                        this@MainActivity,
                        "onFailure: ${error?.type?.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAdClosed() {
                    super.onAdClosed()
                    // Interstitial 지면이 종료되면 호출됩니다.
                    // 필요에 따라 추가 기능을 구현하세요.
                }
            })
        }

        // Pop
        binding.activatePopButton.setOnClickListener {
            BuzzPop.activate(object : BuzzPopActivateListener {
                override fun onActivated() {
                    // 정상적으로 Pop이 활성화 되었을 때 호출됩니다.

                    // 유저 화면에 Pop을 표시합니다.
                    BuzzPop.show()
                }

                override fun onActivationFailed(error: Throwable) {
                    // Pop 활성화에 실패하였을 때 호출됩니다.

                    Toast.makeText(
                        this@MainActivity,
                        "onActivationFailed: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        binding.deactivatePopButton.setOnClickListener {
            BuzzPop.deactivate()
        }

        binding.togglePopButton.setOnClickListener {
            if (BuzzPop.isActivated) {
                BuzzPop.deactivate()
            } else {
                BuzzPop.activate(
                    object : BuzzPopActivateListener {
                        override fun onActivated() {
                            // 정상적으로 Pop이 활성화 되었을 때 호출됩니다.

                            // 유저 화면에 Pop을 표시합니다.
                            BuzzPop.show()
                        }

                        override fun onActivationFailed(error: Throwable) {
                            // Pop 활성화에 실패하였을 때 호출됩니다.

                            Toast.makeText(
                                this@MainActivity,
                                "onActivationFailed: ${error.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
            }
        }

        // Banner
        binding.buzzBannerButton.setOnClickListener {
            navigateActivity(YourBannerActivity::class.java)
        }


//        lifecycleScope.launch(Dispatchers.IO) {
//            // SDK 초기화를 하지 않으면 privacyPolicyManager는 null을 반환합니다.
//
//            // 개인정보 동의 여부를 확인합니다.
//            val isConsentGranted: Result<Boolean>? = BuzzBenefit.privacyPolicyManager?.isConsentGranted()
//
//            // 직접 구현한 개인정보 제3자 제공 동의 UI에서 동의를 받았을 경우 아래 코드를 실행하면, 베네핏허브 진입 시 개인정보 제3자 제공 동의 UI가 노출되지 않으면서 광고 할당이 정상적으로 진행 됩니다.
//            val result: Result<Unit>? = BuzzBenefit.privacyPolicyManager?.grantConsent()
//        }
    }

    private fun login() {
        // 유저 정보를 등록합니다.
        val buzzvilSdkUser = BuzzvilSdkUser(
            userId = YOUR_USER_ID,
            gender = BuzzvilSdkUser.Gender.MALE,
            birthYear = 1980,
        )

        // 유저 정보를 등록합니다.
        BuzzvilSdk.login(
            buzzvilSdkUser = buzzvilSdkUser,
            // (선택) 로그인 상태를 확인할 수 있는 리스너를 등록합니다.
            listener = object : BuzzvilSdkLoginListener {
                override fun onSuccess() {
                    // 로그인에 성공한 경우 호출됩니다.
                    Toast.makeText(this@MainActivity, "loggedIn", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(errorType: BuzzvilSdkLoginListener.ErrorType) {
                    // 로그인에 실패한 경우 호출됩니다.
                    // NOT_INITIALIZED: SDK 초기화를 하지 않고 로그인을 시도한 경우
                    // INVALID_USER_ID: 적절하지 않은 값을 입력한 경우 (e.g. userId가 빈 문자열인 경우)
                    // UNKNOWN: 그 외 알 수 없는 오류
                    Toast.makeText(
                        this@MainActivity,
                        "onFailure: ${errorType.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

    private fun navigateActivity(activity: Class<*>) {
        Intent(this, activity).apply {
            startActivity(this)
        }
    }
}