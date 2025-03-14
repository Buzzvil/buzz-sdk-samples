package com.buzzvil.sample.buzzvil_sdk_v6_sample.pop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.YourCustomFragmentBinding

class YourCustomFragment : Fragment() {
    private var _binding: YourCustomFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = YourCustomFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
