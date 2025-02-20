package com.buzzvil.sample.buzzvilsdksample.nativead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.buzzvil.buzzad.benefit.BaseRewardManager
import com.buzzvil.buzzad.benefit.BuzzAdBenefit
import com.buzzvil.buzzad.benefit.core.ad.AdError
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2Pool
import com.buzzvil.buzzad.benefit.nativead2.api.NativeAd2PoolInitListener
import com.buzzvil.sample.buzzvilsdksample.Constant
import com.buzzvil.sample.buzzvilsdksample.databinding.ActivityNativeCarouselBinding
import com.buzzvil.sample.buzzvilsdksample.nativead.carousel.NativeAd2CarouselAdapter
import com.buzzvil.sample.buzzvilsdksample.nativead.carousel.NativeAd2CarouselItem
import com.buzzvil.sample.buzzvilsdksample.nativead.carousel.PaddingDividerDecoration

/**
 * 네이티브 캐러셀 구현 예제입니다.
 */
class NativeCarouselActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNativeCarouselBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeCarouselBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 캐러셀 아이템 각각에 양 옆 여백 설정
        binding.carouselRecyclerView.addItemDecoration(PaddingDividerDecoration(16))

        binding.loadCarouselButton.setOnClickListener {
            initCarouselPool()
        }
    }

    override fun onResume() {
        super.onResume()
        updateNativeToFeedText(Constant.YOUR_FEED_UNIT_ID)
    }

    private fun initCarouselPool() {
        // 광고 중복 할당을 막기 위해 하나의 캐러셀에 하나의 NativeAd2Pool 인스턴스를 생성하여 사용합니다.
        val carouselPool = NativeAd2Pool(Constant.YOUR_NATIVE_UNIT_ID)
        val REQUEST_AD_COUNT = 5

        // 현재 할당 받을 수 있는 광고의 갯수를 확인합니다.
        carouselPool.init(REQUEST_AD_COUNT, object : NativeAd2PoolInitListener {
            override fun onLoaded(adCount: Int) {
                // 어댑터를 초기화합니다.
                initCarouselAdapter(adCount, carouselPool)

                binding.carouselRecyclerView.visibility = View.VISIBLE
                binding.toFeedLink.visibility = View.VISIBLE
            }

            override fun onError(adError: AdError) {
                // TODO 광고 레이아웃을 숨김 처리 하는 등 적절한 에러 처리를 할 수 있습니다.
                binding.carouselRecyclerView.visibility = View.GONE
                binding.toFeedLink.visibility = View.GONE
            }
        })
    }

    private fun initCarouselAdapter(adCount: Int, carouselPool: NativeAd2Pool) {
        // 무한 스크롤을 적용합니다.
        val isInfiniteLoopEnabled = true

        // 할당 받은 광고 갯수(adCount) 크기의 아이템 리스트를 만들어 NativeAd2CarouselAdapter를 생성합니다.
        val list = buildCarouselItems(adCount)
        val adapter = NativeAd2CarouselAdapter(list, carouselPool, isInfiniteLoopEnabled)

        // RecyclerView에 어댑터를 설정합니다.
        binding.carouselRecyclerView.adapter = adapter

        if (isInfiniteLoopEnabled) {
            // 적당히 큰 수의 position으로 이동하여 무한 스크롤 효과를 구현합니다.
            binding.carouselRecyclerView.layoutManager?.scrollToPosition(list.size * 10000)
        }
    }

    private fun buildCarouselItems(adCount: Int): List<NativeAd2CarouselItem> {
        // 마지막 페이지에 CarouselToFeedSlideItem을 추가하기 위해 1개 더 큰 사이즈로 배열을 생성합니다.
        val carouselItems = arrayOfNulls<NativeAd2CarouselItem>(adCount + 1)

        // 배열을 NativeAd2Item으로 채웁니다.
        carouselItems.fill(NativeAd2CarouselItem.NativeAd2Item)

        // 마지막 아이템은 CarouselToFeedSlideItem으로 설정합니다.
        val carouselToFeedSlideItem = NativeAd2CarouselItem.CarouselToFeedSlideItem
        carouselItems[carouselItems.size - 1] = carouselToFeedSlideItem

        return carouselItems.toList().filterNotNull()
    }

    private fun updateNativeToFeedText(feedUnitId: String) {
        BuzzAdBenefit.getBaseRewardManager()?.getAvailableFeedBaseReward(feedUnitId, object : BaseRewardManager.BaseRewardListener {
            override fun onBaseRewardLoaded(reward: Int) {
                if (reward < 1) {
                    binding.nativeToFeedLayoutTextView.text = "더 많은 참여 기회 보기"
                } else {
                    binding.nativeToFeedLayoutTextView.text = "$reward 포인트 받고 더 많은 참여 기회 보기"
                }
            }
        })
    }
}
