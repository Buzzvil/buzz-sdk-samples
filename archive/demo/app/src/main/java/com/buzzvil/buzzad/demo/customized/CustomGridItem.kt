package com.buzzvil.buzzad.demo.customized

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.buzzvil.buzzad.demo.R
import com.buzzvil.buzzad.demo.databinding.ViewBzGridItemBinding

class CustomGridItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        val binding = ViewBzGridItemBinding.inflate(LayoutInflater.from(context), this, true)
        context.obtainStyledAttributes(attrs, R.styleable.CustomGridItem).let { attributes ->
            binding.itemImage.setImageResource(
                attributes.getResourceId(
                    R.styleable.CustomGridItem_itemImage,
                    R.drawable.ic_launcher_foreground
                )
            )
            binding.itemTitle.text = attributes.getText(R.styleable.CustomGridItem_itemTitle)
            binding.itemDescription.text = attributes.getText(R.styleable.CustomGridItem_itemDescription)
            attributes.recycle()
        }
    }
}
