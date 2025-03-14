package com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.buzzbenefit.BuzzAdError
import com.buzzvil.buzzbenefit.benefithub.BuzzBenefitHub
import com.buzzvil.buzzbenefit.buzznative.BuzzNative
import com.buzzvil.buzzbenefit.buzznative.BuzzNativeAd
import com.buzzvil.buzzbenefit.buzznative.BuzzNativeAdEventsListener
import com.buzzvil.buzzbenefit.buzznative.BuzzNativeRefreshEventsListener
import com.buzzvil.buzzbenefit.buzznative.BuzzNativeViewBinder
import com.buzzvil.buzzbenefit.buzznative.BuzzRewardResult
import com.buzzvil.sample.buzzvil_sdk_v6_sample.Constant
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.ActivityYourNativeCustomBinding

/**
 * 네이티브 custom 구현 예제입니다.
 */
class YourNativeCustomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYourNativeCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourNativeCustomBinding.inflate(layoutInflater)
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
            // 클릭이 되도록 하고 싶은 뷰를 추가합니다.
            .addClickableView(titleTextView)
            // addClickableView를 여러 번 호출하여 여러 개의 클릭 가능 영역을 추가할 수 있습니다.
            .addClickableView(descriptionTextView)
            .addClickableView(iconImageView)
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

            // Optional) 광고 이벤트 리스너를 설정합니다.
            // 로그 기록, 단순 알림 외에 다른 동작을 추가하는 것을 권장하지 않습니다. 자동 갱신 등 네이티브의 기능과 직접 구현한 동작이 충돌할 수 있습니다.
            buzzNative.setAdEventsListener(object : BuzzNativeAdEventsListener {
                override fun onImpressed(buzzNativeAd: BuzzNativeAd) {
                    // Native 광고가 유저에게 노출되었을 때 호출됩니다.
                    binding.simpleNativeAdLayout.layout.visibility = View.VISIBLE
                }

                override fun onClicked(buzzNativeAd: BuzzNativeAd) {
                    // 유저가 Native 광고를 클릭했을 때 호출됩니다.
                }

                override fun onRewardRequested(buzzNativeAd: BuzzNativeAd) {
                    // 리워드 적립을 요청했을 때 호출됩니다.
                }

                override fun onParticipated(buzzNativeAd: BuzzNativeAd) {
                    // 유저가 광고 참여를 완료하였을 때 호출됩니다.
                }

                override fun onRewarded(buzzNativeAd: BuzzNativeAd, buzzRewardResult: BuzzRewardResult) {
                    // 리워드가 적립되었을 때 호출됩니다.
                }
            })

            buzzNative.setRefreshEventsListener( object : BuzzNativeRefreshEventsListener {
                override fun onRequest() {
                    // 광고 할당을 요청한 상태입니다.
                    // 이후에는 onSuccess() 또는 onFailure() 중 하나가 호출됩니다.
                    // 광고 자동 갱신을 시도할 때마다 반복적으로 호출됩니다.
                    // 로딩 화면 등을 구현할 수 있습니다.
                    Toast.makeText(this@YourNativeCustomActivity, "onRequest", Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(buzzNativeAd: BuzzNativeAd) {
                    // 광고 할당에 성공하면 호출됩니다.
                    // 이후에 광고 갱신 시 onRequest()가 다시 호출됩니다.
                    // 광고 자동 갱신을 성공할 때마다 반복적으로 호출됩니다.
                    // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                    Toast.makeText(this@YourNativeCustomActivity, "onSuccess", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(adError: BuzzAdError) {
                    // 광고 할당에 실패하면 호출됩니다.
                    // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
                    Toast.makeText(this@YourNativeCustomActivity, "onFailure: ${adError.type.name}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.bindSimpleNativeAdButton.setOnClickListener {
            binder.bind(buzzNative)
        }
    }
}
