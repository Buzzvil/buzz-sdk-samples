package com.buzzvil.sample.buzzvilsdksample.custom

import android.content.Context
import android.content.Intent
import com.buzzvil.buzzad.benefit.presentation.LaunchInfo
import com.buzzvil.buzzad.benefit.presentation.Launcher
import com.buzzvil.buzzad.browser.BuzzAdJavascriptInterface
import java.io.Serializable


class CustomLauncher: Serializable, Launcher() {
    override fun launch(context: Context, launchInfo: LaunchInfo, listener: LauncherEventListener?, javascriptInterfaces: List<Class<out BuzzAdJavascriptInterface>>?) {
        // 커스텀 인앱 브라우저를 실행합니다.
        val intent = Intent(context, CustomBrowserActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(CustomBrowserActivity.KEY_URL, launchInfo.uri.toString()) // URI는 변경 하면 안 됨
        context.startActivity(intent)
    }
}
