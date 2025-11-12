package com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearSnapHelper
import com.buzzvil.buzzbenefit.benefithub.BuzzBenefitHub
import com.buzzvil.buzzbenefit.buzznative.BuzzNative
import com.buzzvil.buzzbenefit.buzznative.BuzzNativeGroup
import com.buzzvil.sample.buzzvil_sdk_v6_sample.Constant
import com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative.carousel.BuzzNativeCarouselAdapter
import com.buzzvil.sample.buzzvil_sdk_v6_sample.buzznative.carousel.PaddingDividerDecoration
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.ActivityYourNativeCarouselBinding
import com.buzzvil.sample.buzzvil_sdk_v6_sample.util.setupWindowInsets

/**
 * 네이티브 캐러셀 구현 예제입니다.
 */
class YourNativeCarouselActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYourNativeCarouselBinding
    private var buzzNativeGroup: BuzzNativeGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourNativeCarouselBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupWindowInsets()

        // snap
        binding.carouselRecyclerView.onFlingListener = null
        LinearSnapHelper().attachToRecyclerView(binding.carouselRecyclerView)

        // 캐러셀 아이템 각각에 양 옆 여백 설정
        binding.carouselRecyclerView.addItemDecoration(PaddingDividerDecoration(16))

        binding.carouselToFeedLink.setOnClickListener {
            BuzzBenefitHub.show(this)
        }

        binding.loadCarouselButton.setOnClickListener {
            initCarouselNativeGroup(Constant.YOUR_NATIVE_ID)
        }
    }

    private fun initCarouselNativeGroup(nativeUnitId: String) {
        // 광고 중복 할당을 막기 위해 하나의 캐러셀에 하나의 BuzzNativeGroup 인스턴스를 생성하여 사용합니다.
        buzzNativeGroup = BuzzNativeGroup(nativeUnitId)

        val REQUEST_AD_COUNT = 5

        // 현재 할당 받을 수 있는 광고의 갯수를 확인합니다.
        buzzNativeGroup?.let {
            it.load(
                REQUEST_AD_COUNT,
                onSuccess = { adCount ->
                    // 어댑터를 초기화 합니다.
                    initCarouselAdapter(it.natives, adCount)

                    binding.carouselRecyclerView.visibility = View.VISIBLE
                    binding.carouselToFeedLink.visibility = View.VISIBLE
                },
                onFailure = { adError ->
                    // TODO 광고 레이아웃을 숨김 처리 하는 등 적절한 에러 처리를 할 수 있습니다.
                    binding.carouselRecyclerView.visibility = View.GONE
                    binding.carouselToFeedLink.visibility = View.GONE
                }
            )
        }
    }

    private fun initCarouselAdapter(natives: List<BuzzNative>, adCount: Int) {
        // 무한 스크롤을 적용합니다.
        val isInfiniteLoopEnabled = true

        // 할당 받은 광고 리스트로 BuzzNativeCarouselAdapter를 생성합니다.
        val adapter = BuzzNativeCarouselAdapter(natives, isInfiniteLoopEnabled)

        // RecyclerView에 어댑터를 설정합니다.
        binding.carouselRecyclerView.adapter = adapter

        if (isInfiniteLoopEnabled) {
            // 적당히 큰 수의 position으로 이동하여 무한 스크롤 효과를 구현합니다.
            binding.carouselRecyclerView.layoutManager?.scrollToPosition(adCount * 10000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        buzzNativeGroup?.dispose()
    }
}
