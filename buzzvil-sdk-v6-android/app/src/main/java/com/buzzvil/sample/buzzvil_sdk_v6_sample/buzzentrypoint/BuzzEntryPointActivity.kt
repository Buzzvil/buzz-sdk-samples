package com.buzzvil.sample.buzzvil_sdk_v6_sample.buzzentrypoint

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.entrypoint.BuzzEntryPoint
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.ActivityBuzzEntryPointBinding
import com.buzzvil.sample.buzzvil_sdk_v6_sample.util.setupWindowInsets

class BuzzEntryPointActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBuzzEntryPointBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuzzEntryPointBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupWindowInsets()

        binding.buttonShowFab.setOnClickListener {
            // BuzzEntryPoint FAB를 표시합니다.
            BuzzEntryPoint.showFab(lifecycleOwner = this, binding.root)
        }

        binding.buttonShowBanner.setOnClickListener {
            // BuzzEntryPoint 배너를 표시합니다.
            BuzzEntryPoint.showBanner(binding.buzzEntryPointBanner)
        }

        binding.buttonShowPopup.setOnClickListener {
            // BuzzEntryPoint 팝업을 표시합니다.
            BuzzEntryPoint.showPopup(this)
        }

        binding.buttonShowBottomSheet.setOnClickListener {
            // BuzzEntryPoint 바텀 시트를 표시합니다.
            BuzzEntryPoint.showBottomSheet(this)
        }

        binding.buttonCustomEntry.setOnClickListener {
            // BuzzEntryPoint의 커스텀 엔트리 포인트를 클릭합니다.
            BuzzEntryPoint.customEntryPointClicked(this, "custom_1")
        }

        BuzzEntryPoint.load(
            onSuccess = {
                // Buzz Entry Point가 성공적으로 로드되면, 해당 Entry Point를 표시할 수 있습니다.
                Toast.makeText(
                    this,
                    "Buzz Entry Point loaded successfully",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onError = {
                // Buzz Entry Point 로드에 실패한 경우, 에러 메시지를 표시합니다.
                Toast.makeText(
                    this,
                    "Failed to load Buzz Entry Point: ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }
}