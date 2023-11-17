package com.buzzvil.sample.buzzvilsdksample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.buzzad.benefit.core.ad.AdError
import com.buzzvil.buzzad.benefit.core.models.UserProfile
import com.buzzvil.buzzad.benefit.presentation.feed.BuzzAdFeed
import com.buzzvil.sample.buzzvilsdksample.benefithub.BenefitHubFragmentActivity
import com.buzzvil.sample.buzzvilsdksample.databinding.ActivityMainBinding
import com.buzzvil.sample.buzzvilsdksample.nativead.NativeCarouselActivity
import com.buzzvil.sample.buzzvilsdksample.nativead.NativeCustomActivity
import com.buzzvil.sample.buzzvilsdksample.nativead.NativeSimpleActivity
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

        binding.sdkVersionTextView.text = "SDK Version: ${BuzzvilSdk.versionName}"

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
}
