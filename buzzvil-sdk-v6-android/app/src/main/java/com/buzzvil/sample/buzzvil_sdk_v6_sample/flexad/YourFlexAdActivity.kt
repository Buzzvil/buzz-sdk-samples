package com.buzzvil.sample.buzzvil_sdk_v6_sample.flexad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.buzzbenefit.BuzzAdError
import com.buzzvil.buzzbenefit.flexad.BuzzFlex
import com.buzzvil.buzzbenefit.flexad.BuzzFlexAdView
import com.buzzvil.sample.buzzvil_sdk_v6_sample.Constant
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.ActivityYourFlexAdBinding

class YourFlexAdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYourFlexAdBinding
    private lateinit var buzzFlex: BuzzFlex

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourFlexAdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buzzFlexAdView = BuzzFlexAdView(this)
        binding.buzzFlexAdContainer.addView(buzzFlexAdView)

        buzzFlex = BuzzFlex(Constant.YOUR_FLEX_AD_ID)
        buzzFlex.setListener(object : BuzzFlex.Listener {
            override fun onSuccess() {
                // 광고 로드 성공 시 호출됩니다.
                // BuzzFlexAdView에 광고를 표시합니다.
                buzzFlexAdView.bind(buzzFlex)
            }

            override fun onFailure(adError: BuzzAdError) {
                // 광고 로드 실패 시 호출됩니다.
            }

            override fun onClicked() {
                // 광고 클릭 시 호출됩니다.
            }
        })

        buzzFlex.load()
    }

    override fun onDestroy() {
        super.onDestroy()
        buzzFlex.dispose()
    }
}
