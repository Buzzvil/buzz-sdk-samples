package com.buzzvil.buzzad.benefit.sample.publisher.carousel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.buzzvil.buzzad.benefit.core.ad.AdError
import com.buzzvil.buzzad.benefit.presentation.feed.entrypoint.FeedPromotionFactory
import com.buzzvil.buzzad.benefit.presentation.nativead.*
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult
import com.buzzvil.buzzad.benefit.sample.publisher.App
import com.buzzvil.buzzad.benefit.sample.publisher.databinding.ActivityNativeCarouselBinding
import kotlin.math.roundToInt

/**
 * BuzzAd SDK를 사용하여 Carousel UI를 구현하는 방법에 대한 예제 코드
 */
class NativeCarouselActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "NativeCarouselActivity"

        @JvmStatic
        fun startActivity(context: Context) {
            val intent = Intent(context, NativeCarouselActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityNativeCarouselBinding
    private lateinit var adapter: CarouselAdapter

    // Carousel 지면에서 사용할 Unit ID
    private val unitId = App.UNIT_ID_NATIVE_AD

    // 무한 스크롤을 사용할지 설정
    private val infiniteLoop = true

    // Carousel 아이템 간의 사이 간격
    private val itemPaddingDp = 16

    // 화면 폭에 대한 Carousel 아이템의 폭 비율
    private val itemWidthPercentOfScreen = 0.8

    // LinearLayoutManager를 사용하고 HORIZONTAL로 설정하여 수평으로 일렬로 정렬된 레이아웃을 사용한다.
    private val layoutManager = object : LinearLayoutManager(
            this,
            HORIZONTAL,
            false
    ) {
        // Carousel 아이템의 폭을 설정한 비율에 맞게 변경한다.
        override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
            lp.width = (width * itemWidthPercentOfScreen).roundToInt()
            return true
        }
    }

    private val adsLoadedListener = object : NativeAdLoader.OnAdsLoadedListener {
        override fun onLoadError(error: AdError) {
            onError()
        }

        override fun onAdsLoaded(nativeAds: Collection<NativeAd>) {
            if (nativeAds.isEmpty()) {
                onEmpty()
            } else {
                this@NativeCarouselActivity.onAdsLoaded(nativeAds.toList())
            }
        }
    }

    private val nativeAdEventListener = object : NativeAdEventListener {
        override fun onImpressed(nativeAd: NativeAd) {
        }

        override fun onClicked(nativeAd: NativeAd) {
        }

        override fun onRewardRequested(nativeAd: NativeAd) {
        }

        override fun onRewarded(
                nativeAd: NativeAd,
                nativeAdRewardResult: RewardResult?
        ) {
        }

        override fun onParticipated(nativeAd: NativeAd) {
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeCarouselBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = CarouselAdapter(infiniteLoop)
        initCarousel()
        loadAds()
    }

    private fun initCarousel() {
        binding.carousel.adapter = adapter

        // ViewPager와 비슷하게, 스크롤이 중간에서 멈추면 한쪽 아이템을 선택하여 다 보일 때까지 스크롤
        PagerSnapHelper().attachToRecyclerView(binding.carousel)

        binding.carousel.layoutManager = layoutManager
        // 아이템 간에 여백을 넣는 Decoration 추가
        binding.carousel.addItemDecoration(PaddingDividerDecoration(itemPaddingDp))
    }

    private fun loadAds() {
        NativeAdLoader(unitId).loadAds(
                adsLoadedListener,
                NativeAdLoaderParams.Builder()
                        .count(5)
                        .build()
        )
    }

    private fun onError() {
        // 에러가 발생하면 지면을 표시하지 않는다.
        binding.placement.visibility = View.GONE
        Log.d(TAG, "An error occurred while loading ads")
    }

    private fun onEmpty() {
        // 보여줄 광고가 없으면 지면을 표시하지 않는다.
        binding.placement.visibility = View.GONE
        Log.d(TAG, "The ad list is empty")
    }

    private fun onAdsLoaded(nativeAds: List<NativeAd>) {
        binding.placement.visibility = View.VISIBLE
        nativeAds.forEach {
            it.addNativeAdEventListener(nativeAdEventListener)
        }
        buildCarouselItems(nativeAds).let {
            adapter.submitList(it)
            if (infiniteLoop) {
                // To show item before first item
                binding.carousel.layoutManager?.scrollToPosition(it.size * 10000)
            }
        }
        Log.d(TAG, "${nativeAds.size} Ads are loaded")
    }

    /**
     * NativeAd 배열을 Carousel에 채워넣을 CarouselItem으로 변환하는 함수
     *
     * @param nativeAds: Carousel에 표시할 광고 객체 배열
     *
     * 광고가 비어있지 않으면 마지막 페이지에 Carousel To Feed Slide를 추가한다.
     */
    private fun buildCarouselItems(nativeAds: List<NativeAd>): List<CarouselItem> {
        return nativeAds.map { CarouselItem.new(it) }
                .toMutableList<CarouselItem>().apply {
                    if (this.isNotEmpty()) {
                        add(CarouselItem.new(FeedPromotionFactory(unitId).buildForCarousel()))
                    }
                }
    }

}
