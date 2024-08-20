package com.buzzvil.buzzad.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.buzzvil.buzzad.benefit.core.ad.AdError
import com.buzzvil.buzzad.benefit.core.models.Ad
import com.buzzvil.buzzad.benefit.presentation.media.CtaPresenter
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAd
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdLoader
import com.buzzvil.buzzad.benefit.presentation.nativead.NativeAdView
import com.buzzvil.buzzad.benefit.presentation.reward.RewardResult
import com.buzzvil.buzzad.demo.databinding.FragmentNativeAdBinding
import io.reactivex.Single

class NativeAdFragment : Fragment() {

    private var _binding: FragmentNativeAdBinding? = null
    private val binding get() = _binding!!
    private val nativeAdBinding get() = _binding!!.nativeAdLayout

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNativeAdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGrids()
        setNativeAd()
    }

    private fun initGrids() {
        binding.inAppPopGrid.setOnClickListener {
            viewModel.showInAppPop()
        }

        binding.pushGrid.setOnClickListener {
            viewModel.showPush()
        }
    }

    @SuppressLint("CheckResult")
    private fun setNativeAd() {
        val nativeAd = loadNativeAd()
        nativeAd.subscribe({
            showNativeAd(it)
        }, { error ->
            context?.let {
                Toast.makeText(it.applicationContext, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadNativeAd(): Single<NativeAd> {
        val loader = NativeAdLoader(BuildConfig.NATIVE_UNIT_ID)
        return Single.create { emitter ->
            loader.loadAd(object : NativeAdLoader.OnAdLoadedListener {
                override fun onLoadError(error: AdError) {
                    if (!emitter.isDisposed) {
                        emitter.onError(error)
                    }
                }

                override fun onAdLoaded(nativeAd: NativeAd) {
                    if (!emitter.isDisposed) {
                        emitter.onSuccess(nativeAd)
                    }
                }
            })
        }
    }

    private fun showNativeAd(nativeAd: NativeAd) {
        binding.nativeAdContainer.visibility = View.VISIBLE
        nativeAdBinding.nativeToFeedLayout.setNativeUnitId(BuildConfig.NATIVE_UNIT_ID)
        populateNativeAd(nativeAd)
    }

    private fun populateNativeAd(nativeAd: NativeAd) {
        setAd(nativeAd.ad)
        nativeAdBinding.nativeAdView.setClickableViews(getClickableViews())
        nativeAdBinding.nativeAdView.addOnNativeAdEventListener(
            getNativeAdListener(
                CtaPresenter(nativeAdBinding.ctaView)
            )
        )
        nativeAdBinding.nativeAdView.setMediaView(nativeAdBinding.mediaView)
        nativeAdBinding.nativeAdView.setNativeAd(nativeAd)
    }

    private fun setAd(ad: Ad) {
        nativeAdBinding.mediaView.setCreative(ad.creative)
        nativeAdBinding.adTitle.text = ad.title
        nativeAdBinding.adDescription.text = ad.description
        Glide.with(this).load(ad.iconUrl).into(nativeAdBinding.adIconImage)
    }

    private fun getClickableViews() = mutableListOf<View>()
        .apply {
            add(nativeAdBinding.mediaView)
            add(nativeAdBinding.ctaView)
        }

    private fun getNativeAdListener(ctaPresenter: CtaPresenter) = object : NativeAdView.OnNativeAdEventListener {
        override fun onImpressed(view: NativeAdView, nativeAd: NativeAd) {
            ctaPresenter.bind(nativeAd)
        }

        override fun onClicked(view: NativeAdView, nativeAd: NativeAd) {
            ctaPresenter.bind(nativeAd)
        }

        override fun onRewardRequested(p0: NativeAdView, p1: NativeAd) {
        }

        override fun onRewarded(p0: NativeAdView, p1: NativeAd, p2: RewardResult?) {
        }

        override fun onParticipated(view: NativeAdView, nativeAd: NativeAd) {
            ctaPresenter.bind(nativeAd)
        }
    }
}
