package com.buzzvil.sample.buzzvil_sdk_v6_sample.flexad

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import com.buzzvil.buzzbenefit.BuzzAdError
import com.buzzvil.buzzbenefit.flexad.BuzzFlex
import com.buzzvil.buzzbenefit.flexad.BuzzFlexAdView
import com.buzzvil.sample.buzzvil_sdk_v6_sample.Constant
import com.buzzvil.sample.buzzvil_sdk_v6_sample.R
import com.buzzvil.sample.buzzvil_sdk_v6_sample.databinding.ActivityYourFlexAdBinding

/**
 * FlexAd를 스크롤 피드 최상단에 배치해, 스크롤 인/아웃에 따른 뷰어빌리티 동작을 확인하는 예시 화면.
 */
class YourFlexAdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYourFlexAdBinding
    private lateinit var buzzFlex: BuzzFlex

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "FlexAd"
        binding = ActivityYourFlexAdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buzzFlexAdView = BuzzFlexAdView(this)
        binding.buzzFlexAdContainer.addView(buzzFlexAdView)

        setupSampleContent()

        buzzFlex = BuzzFlex(Constant.YOUR_FLEX_AD_ID)
        buzzFlex.setListener(object : BuzzFlex.Listener {
            override fun onSuccess() {
                buzzFlexAdView.bind(buzzFlex)
            }

            override fun onFailure(adError: BuzzAdError) {}

            override fun onClicked() {}
        })
        buzzFlex.load()
    }

    override fun onDestroy() {
        super.onDestroy()
        buzzFlex.dispose()
    }

    // MARK: - 예시용 샘플 콘텐츠

    private data class SampleItem(val title: String, val body: String)

    private fun setupSampleContent() {
        binding.contentContainer.addView(makeSampleNotice())
        sampleFeed.forEachIndexed { index, item ->
            binding.contentContainer.addView(makeSampleCard(item, thumbnailTints[index % thumbnailTints.size]))
        }
    }

    private fun makeSampleNotice(): View =
        TextView(this).apply {
            text = "· 아래는 스크롤 예시용 샘플 콘텐츠입니다. ·"
            setTextColor(Color.parseColor("#9E9E9E"))
            textSize = 12f
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            ).apply { setMargins(dp(16), dp(16), dp(16), dp(8)) }
        }

    private fun makeSampleCard(item: SampleItem, tint: Int): View {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            ).apply { setMargins(dp(16), dp(8), dp(16), dp(8)) }
        }

        val thumbnail = ImageView(this).apply {
            setImageResource(R.drawable.ic_sample_image)
            setColorFilter(tint)
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setPadding(dp(20), dp(20), dp(20), dp(20))
            background = GradientDrawable().apply {
                cornerRadius = dp(8).toFloat()
                setColor(ColorUtils.setAlphaComponent(tint, 38))
            }
            layoutParams = LinearLayout.LayoutParams(dp(72), dp(72))
        }
        row.addView(thumbnail)

        val textColumn = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f,
            ).apply { marginStart = dp(12) }
        }
        textColumn.addView(TextView(this).apply {
            text = item.title
            setTextColor(Color.parseColor("#1C1C1E"))
            textSize = 15f
            setTypeface(typeface, Typeface.BOLD)
            maxLines = 2
        })
        textColumn.addView(TextView(this).apply {
            text = item.body
            setTextColor(Color.parseColor("#6E6E73"))
            textSize = 13f
            maxLines = 2
        })
        textColumn.addView(TextView(this).apply {
            text = "예시"
            setTextColor(Color.parseColor("#AEAEB2"))
            textSize = 10f
            setTypeface(typeface, Typeface.BOLD)
        })
        row.addView(textColumn)

        return row
    }

    private fun dp(value: Int): Int =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), resources.displayMetrics).toInt()

    private companion object {
        val thumbnailTints = intArrayOf(
            Color.parseColor("#007AFF"),
            Color.parseColor("#34C759"),
            Color.parseColor("#FF9500"),
            Color.parseColor("#AF52DE"),
            Color.parseColor("#FF2D55"),
            Color.parseColor("#30B0C7"),
        )

        val sampleFeed = listOf(
            SampleItem("샘플 콘텐츠 · 오늘의 주요 소식", "스크롤 확인용 예시 콘텐츠입니다."),
            SampleItem("샘플 콘텐츠 · 이번 주 인기 글", "실제 서비스 데이터가 아닌 자리표시자입니다."),
            SampleItem("샘플 콘텐츠 · 라이프스타일 팁", "레이아웃 미리보기를 위한 목업 텍스트입니다."),
            SampleItem("샘플 콘텐츠 · 테크 브리핑", "예시용으로 채워 넣은 내용입니다."),
            SampleItem("샘플 콘텐츠 · 건강 상식", "스크롤 공간 확보를 위한 샘플입니다."),
            SampleItem("샘플 콘텐츠 · 여행 가이드", "실제 콘텐츠가 아닌 예시 텍스트입니다."),
            SampleItem("샘플 콘텐츠 · 오늘의 맛집", "자리표시자 예시 콘텐츠입니다."),
            SampleItem("샘플 콘텐츠 · 경제 브리핑", "미리보기용 목업 문구입니다."),
            SampleItem("샘플 콘텐츠 · 문화·이벤트", "실제 서비스 데이터가 아닙니다."),
            SampleItem("샘플 콘텐츠 · 스포츠 하이라이트", "스크롤 확인용 예시 콘텐츠입니다."),
            SampleItem("샘플 콘텐츠 · 날씨 브리핑", "레이아웃 예시를 위한 자리표시자입니다."),
            SampleItem("샘플 콘텐츠 · 추천 읽을거리", "예시용으로 채워 넣은 목업 텍스트입니다."),
        )
    }
}
