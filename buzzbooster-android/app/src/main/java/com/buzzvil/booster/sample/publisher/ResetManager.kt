package com.buzzvil.booster.sample.publisher

import android.content.Context
import android.content.SharedPreferences

object ResetManager {
    fun reset(context: Context) {
        val prefs: SharedPreferences =
            context.getSharedPreferences("BuzzBoosterPrefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.remove("MESSAGE_CURSOR")
        editor.apply()
    }
}