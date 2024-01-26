package com.buzzvil.sample.buzzvilsdksample.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.buzzvil.buzzad.benefit.presentation.feed.header.FeedHeaderViewAdapter
import com.buzzvil.sample.buzzvilsdksample.R

class CustomFeedHeaderViewAdapter : FeedHeaderViewAdapter {

    override fun onCreateView(context: Context, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return inflater.inflate(R.layout.feed_header_layout, parent, false)
    }

    override fun onBindView(view: View, reward: Int) {
        val rewardTextView: TextView = view.findViewById(R.id.rewardText)
        rewardTextView.text = reward.toString()
    }

    override fun onDestroyView() {
        // Use this callback for clearing memory
    }
}
