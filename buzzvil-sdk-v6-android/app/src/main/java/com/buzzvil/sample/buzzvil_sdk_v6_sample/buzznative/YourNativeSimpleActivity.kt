package com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.buzzbenefit.benefithub.BuzzBenefitHub
import com.buzzvil.buzzbenefit.buzznative.BuzzNative
import com.buzzvil.buzzbenefit.buzznative.BuzzNativeViewBinder
import com.buzzvil.sample.buzzvil_sdk_v6_sample.Constant
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.ActivityYourNativeSimpleBinding

/**
 * 네이티브 기본 구현 예제입니다.
 */
class YourNativeSimpleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYourNativeSimpleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourNativeSimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BuzzNative 객체를 생성합니다.
        val buzzNative = BuzzNative(Constant.YOUR_NATIVE_ID)

        val buzzNativeAdView = binding.simpleNativeAdLayout.buzzNativeAdView
        val buzzMediaView = binding.simpleNativeAdLayout.buzzMediaView
        val titleTextView = binding.simpleNativeAdLayout.textTitle
        val descriptionTextView = binding.simpleNativeAdLayout.textDescription
        val iconImageView = binding.simpleNativeAdLayout.imageIcon
        val buzzCtaView = binding.simpleNativeAdLayout.buzzCtaView

        // 광고 레이아웃을 설정합니다.
        val binder: BuzzNativeViewBinder = BuzzNativeViewBinder.Builder()
            .buzzNativeView(buzzNativeAdView)
            .buzzMediaView(buzzMediaView)
            .titleTextView(titleTextView)
            .descriptionTextView(descriptionTextView)
            .iconImageView(iconImageView)
            .buzzCtaView(buzzCtaView)
            .build()

        binding.simpleNativeAdLayout.yourNativeToBenefitHubEntryPoint.setOnClickListener {
            BuzzBenefitHub.show(this)
        }

        binding.loadSimpleNativeAdButton.setOnClickListener {
            // BuzzNative에서 광고를 할당받습니다.
            buzzNative.load(
                onSuccess = { nativeAd ->
                    // 광고 할당에 성공하면 호출됩니다.
                    // 이후에 광고 갱신 시 onRequest()가 다시 호출됩니다.
                    // 광고 자동 갱신을 성공할 때마다 반복적으로 호출됩니다.
                    Toast.makeText(this, "onSuccess : 광고 할당 성공 ${nativeAd.title}", Toast.LENGTH_SHORT).show()
                },
                onFailure = { adError ->
                    // 최초 광고 할당에 실패하면 호출됩니다.
                    Toast.makeText(this, "onFailure : 광고 할당 실패 ${adError.type.name}", Toast.LENGTH_SHORT).show()
                    binding.simpleNativeAdLayout.layout.visibility = View.GONE
                }
            )
        }

        binding.bindSimpleNativeAdButton.setOnClickListener {
            binder.bind(buzzNative)
        }
    }
}
