package com.buzzvil.buzzad.benefit.popsample.java

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.buzzvil.buzzad.benefit.pop.DefaultPedometerPopHeaderViewAdapter
import com.buzzvil.buzzad.benefit.popsample.R

class CustomPedometerPopHeaderViewAdapter : DefaultPedometerPopHeaderViewAdapter() {
    override fun onCreateView(context: Context, parent: ViewGroup): View {
        val view = super.onCreateView(context, parent)
        val customLayout = LayoutInflater.from(context).inflate(
            R.layout.view_custom_feed_header,
            parent,
            false
        )
        val imgIcon: ImageView = customLayout.findViewById(R.id.imgIcon)
        imgIcon.setOnClickListener {
            Toast.makeText(context, "hi CustomPedometerPopHeaderViewAdapter", Toast.LENGTH_LONG).show()
        }
        (view as ViewGroup).addView(customLayout)

        return view
    }
}
