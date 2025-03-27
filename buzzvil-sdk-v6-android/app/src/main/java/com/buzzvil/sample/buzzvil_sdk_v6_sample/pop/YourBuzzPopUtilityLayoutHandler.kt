package com.buzzvil.sample.buzzvil_sdk_v6_sample.pop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buzzvil.buzzbenefit.pop.BuzzPopUtilityLayoutHandler
import com.buzzvil.sample.buzzvil_sdk_v6_sample.R

class YourBuzzPopUtilityLayoutHandler(val context: Context) : BuzzPopUtilityLayoutHandler(context) {
    override fun onCreateView(parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        return inflater.inflate(R.layout.your_buzz_pop_utility_view, parent, false)
    }
}
