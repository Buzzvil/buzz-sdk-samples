package com.buzzvil.sample.buzzvilsdksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buzzvil.buzzad.benefit.feed.benefithub.BenefitHubFragment
import com.buzzvil.sample.buzzvilsdksample.databinding.ActivityBenefitHubFragmentBinding

class BenefitHubFragmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBenefitHubFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBenefitHubFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val benefitHubFragment = BenefitHubFragment.getInstance()

            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragmentContainer, benefitHubFragment)
                commit()
            }
        }
    }
}
