package com.buzzvil.sample.buzzvilsdksample.pop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buzzvil.buzzad.benefit.pop.feedutility.PopUtilityLayoutHandler
import com.buzzvil.sample.buzzvilsdksample.R

class CustomPopUtilityLayoutHandler(val context: Context): PopUtilityLayoutHandler(context) {
    override fun onCreateView(parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        return inflater.inflate(R.layout.your_pop_utility_view, parent, false)
    }
}
