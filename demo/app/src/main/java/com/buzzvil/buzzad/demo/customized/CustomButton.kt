package com.buzzvil.buzzad.demo.customized

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import com.buzzvil.buzzad.demo.R
import com.buzzvil.buzzad.demo.databinding.ViewBzButtonBinding

class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        val binding = ViewBzButtonBinding.inflate(LayoutInflater.from(context), this, true)
        context.obtainStyledAttributes(attrs, R.styleable.CustomButton).let { attributes ->
            binding.customButton.text = attributes.getString(R.styleable.CustomButton_text)
            binding.customButton.setTextColor(
                attributes.getColorStateList(R.styleable.CustomButton_textColor) ?:
                AppCompatResources.getColorStateList(context, R.color.button_text_color)
            )
            binding.customButton.setBackgroundResource(
                attributes.getResourceId(
                    R.styleable.CustomButton_textBackground,
                    R.drawable.button_background
                )
            )
            attributes.recycle()
        }
    }
}
