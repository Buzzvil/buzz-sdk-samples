package com.buzzvil.sample.buzzvilsdksample.benefithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buzzvil.buzzad.benefit.presentation.feed.BuzzAdFeed
import com.buzzvil.sample.buzzvilsdksample.R
import com.buzzvil.sample.buzzvilsdksample.databinding.ActivityBenefitHubFragmentBinding

class BenefitHubFragmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBenefitHubFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBenefitHubFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            // 5.13.x 미만까지 사용하던 방식 (Deprecated)
            // val benefitHubFragment = BenefitHubFragment.getInstance()

            // 5.13.x 부터는 아래와 같이 사용합니다.
            val buzzAdFeed = BuzzAdFeed.Builder().build()
            val benefitHubFragment = buzzAdFeed.getBenefitHubFragment()

            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragmentContainer, benefitHubFragment)
                commit()
            }
        }
    }
}
