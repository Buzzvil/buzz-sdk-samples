package com.buzzvil.sample.buzzvil_sdk_v6_sample.buzzbenefithub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.buzzbenefit.benefithub.BuzzBenefitHubFragment
import com.buzzvil.sample.buzzvil_sdk_v6_sample.R
import com.buzzvil.sample.buzzvil_sdk_v6_sample.util.setupWindowInsets

class YourBenefitHubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_benefit_hub)
        setupWindowInsets()

        if (savedInstanceState == null) {
            // SDK를 초기화 하지 않은 경우 null이 반환됩니다.
            val buzzBenefitHubFragment = BuzzBenefitHubFragment.newInstance()

            if (buzzBenefitHubFragment != null) {
                supportFragmentManager.beginTransaction().apply {
                    add(R.id.yourFragmentContainer, buzzBenefitHubFragment)
                    commit()
                }
            } else {
                // 적절한 예외 처리를 추가하세요.
            }
        }
    }
}
