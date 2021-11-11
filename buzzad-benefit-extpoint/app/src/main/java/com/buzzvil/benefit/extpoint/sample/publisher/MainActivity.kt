package com.buzzvil.benefit.extpoint.sample.publisher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.benefit.extpoint.sample.publisher.usecase.FeedActivityUseCase
import com.buzzvil.benefit.extpoint.sample.publisher.usecase.FeedFragmentUseCase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val feedActivityUseCase = FeedActivityUseCase()
    private val feedFragmentUseCase = FeedFragmentUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButtons()
    }

    private fun initButtons() {
        runFeedActivityExample.setOnClickListener {
            feedActivityUseCase(this@MainActivity)
        }

        runFeedFragmentExample.setOnClickListener {
            feedFragmentUseCase(this@MainActivity)
        }
    }
}
