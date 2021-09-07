package com.buzzvil.buzzad.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.buzzvil.buzzad.demo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initButtons() {
        binding.feedButton.setOnClickListener {
            viewModel.showFeed()
        }

        binding.popButton.setOnClickListener {
            viewModel.showPop()
        }

        binding.inAppPopButton.setOnClickListener {
            viewModel.showInAppPop()
        }

        binding.pushAlarmButton.setOnClickListener {
            viewModel.showPush()
        }

        binding.interstitialButton.setOnClickListener {
            viewModel.showInterstitial()
        }

        binding.nativeButton.setOnClickListener {
            viewModel.showNative()
        }

        binding.resetButton.setOnClickListener {
            viewModel.resetAll()
        }
    }
}
