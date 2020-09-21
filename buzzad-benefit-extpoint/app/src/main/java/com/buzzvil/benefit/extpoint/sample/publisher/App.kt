package com.buzzvil.benefit.extpoint.sample.publisher

import android.app.Application
import android.graphics.Color
import com.buzzvil.buzzad.benefit.BuzzAdBenefit
import com.buzzvil.buzzad.benefit.BuzzAdBenefitConfig
import com.buzzvil.buzzad.benefit.extauth.BuzzAdBenefitExtauthConfig
import com.buzzvil.buzzad.benefit.extauth.BuzzAdBenefitExtauthInstaller

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        //// Extauth 모듈 활성화를 위해 다음 항목들을 추가합니다. ////
        // ExtauthConfig - (1) AppIcon 과 (2) ThemeColor 를 변경할 수 있습니다.
        val extauthConfig = BuzzAdBenefitExtauthConfig.Builder(this)
            .setAppIcon(applicationInfo.icon)
            .setThemeColor(Color.parseColor("#F93F5A"))
            .build()
        // ExtauthInstaller
        val extauthInstaller = BuzzAdBenefitExtauthInstaller(extauthConfig)
        val buzzAdBenefitConfig = extauthInstaller.installTo(BuzzAdBenefitConfig.Builder(this))
            .build()

        // (3) 초기화
        BuzzAdBenefit.init(this, buzzAdBenefitConfig)
    }
}
