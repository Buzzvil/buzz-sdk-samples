package com.buzzvil.booster.sample.publisher

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.buzzvil.booster.external.BuzzBooster

class OptInMarketingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opt_in_marketing)

        findViewById<SwitchCompat>(R.id.switchOptInMarketing).setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                BuzzBooster.getInstance().sendEvent("bb_opt_in_marketing")
                Toast.makeText(this, "Go back to see the result", Toast.LENGTH_SHORT).show()
            } else {
                BuzzBooster.getInstance().sendEvent("bb_opt_out_marketing")
            }
        }
    }
}
