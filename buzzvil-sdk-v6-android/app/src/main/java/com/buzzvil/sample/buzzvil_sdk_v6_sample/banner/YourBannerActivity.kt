package com.buzzvil.sample.buzzvil_sdk_v6_sample.banner

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.buzzbanner.AdError
import com.buzzvil.buzzbanner.BuzzBanner
import com.buzzvil.buzzbanner.BuzzBannerConfig
import com.buzzvil.buzzbanner.BuzzBannerView
import com.buzzvil.buzzbanner.BuzzBannerViewListener
import com.buzzvil.sample.buzzvil_sdk_v6_sample.Constant.YOUR_BANNER_ID
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.ActivityYourBannerBinding

class YourBannerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYourBannerBinding
    private lateinit var bannerView: BuzzBannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourBannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bannerView = binding.buzzBannerView

        setBuzzBannerConfig()
    }

    public override fun onResume() {
        super.onResume()

        if (::bannerView.isInitialized) {
            bannerView.onResume()
        }
    }

    public override fun onPause() {
        super.onPause()

        if (::bannerView.isInitialized) {
            bannerView.onPause()
        }
    }

    override fun onDestroy() {
        if (::bannerView.isInitialized){
            bannerView.onDestroy()
        }

        super.onDestroy()
    }

    private fun setBuzzBannerConfig() {
        val buzzBannerViewListener = object: BuzzBannerViewListener {
            override fun onClicked() {
                Log.d("BuzzBannerViewListener", "onClicked()")
            }

            override fun onFailed(adError: AdError) {
                binding.bannerScreenTextView.text = "onFailed($adError)"

                Log.d("BuzzBannerViewListener", "onFailed($adError)")
            }

            override fun onLoaded() {
                binding.bannerScreenTextView.text = "onLoaded()"

                Log.d("BuzzBannerViewListener", "onLoaded()")
            }
        }

        bannerView.setBuzzBannerConfig(
            BuzzBannerConfig.Builder()
                .bannerSize(BuzzBanner.BannerSize.W320XH50)
                .placementId(YOUR_BANNER_ID)
                .build()
        )

        bannerView.setBuzzBannerViewListener(buzzBannerViewListener)
    }
}
