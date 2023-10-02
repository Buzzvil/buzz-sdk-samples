package com.buzzvil.sample.buzzvilsdksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buzzvil.sample.buzzvilsdksample.databinding.ActivityMainBinding
import com.buzzvil.sdk.BuzzvilSdk

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            // 사용자 정보를 등록하는 코드입니다.
            BuzzvilSdk.setUserProfile(
                userId = "SAMPLE_USER_ID",
                gender = BuzzvilSdk.Gender.MALE,
                birthYear = 1980,
            )
        }

        binding.logoutButton.setOnClickListener {
            // 사용자 정보를 삭제합니다.
            BuzzvilSdk.setUserProfile(null)
        }

        binding.showBenefitHubButton.setOnClickListener {
            BuzzvilSdk.showFeed(this@MainActivity)
        }
    }
}
