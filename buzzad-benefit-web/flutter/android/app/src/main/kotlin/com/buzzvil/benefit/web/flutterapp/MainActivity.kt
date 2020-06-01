package com.buzzvil.benefit.web.flutterapp

import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant
import com.buzzvil.buzzad.benefit.BuzzAdBenefit
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig
import com.buzzvil.buzzad.benefit.core.models.UserProfile

class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        flutterEngine.plugins.add(WebViewPlugin())
        BuzzAdBenefit.init(this, BuzzAdBenefitConfig.Builder(this).build())

        val builder: UserProfile.Builder = UserProfile.Builder(BuzzAdBenefit.getUserProfile())
        val userProfile: UserProfile = builder
                .userId("sampleUser")
                .gender(UserProfile.Gender.MALE)
                .birthYear(1985)
                .build()

        BuzzAdBenefit.setUserProfile(userProfile)
    }
}
