package com.buzzvil.sample.buzzvil_sdk_v6_sample.pop

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.buzzvil.buzzbenefit.pop.BuzzPopMessageView
import com.buzzvil.sample.buzzvil_sdk_v6_sample.R

class YourBuzzPopMessageView: BuzzPopMessageView {
    private val textTitle: TextView
    private val textDescription: TextView

    constructor(context: Context): super(context) {
        val view = LayoutInflater.from(context).inflate(R.layout.your_buzz_pop_message_view, this)
        this.textTitle = view.findViewById(R.id.textTitle)
        this.textDescription = view.findViewById(R.id.textDescription)
    }

    override fun updateView(reward: Int, remainSeconds: Int) {
        textTitle.text = "$reward 포인트 적립 가능합니다."
        textDescription.text = "$remainSeconds 초 후에 닫힙니다."
    }

    override fun getDurationInSeconds(): Int {
        return 5
    }

    override fun getMessage(): String {
        return textTitle.text.toString()
    }
}
