package com.buzzvil.benefit.web.flutterapp

import android.content.Context
import android.os.Build
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import com.buzzvil.buzzad.benefit.core.js.BuzzAdBenefitJavascriptInterface
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.platform.PlatformView

class FlutterWebView internal constructor(context: Context?, messenger: BinaryMessenger?, id: Int)
    : PlatformView, MethodCallHandler {

    private val webView: WebView
    private val methodChannel: MethodChannel

    init {
        webView = WebView(context)
        val javascriptInterface = BuzzAdBenefitJavascriptInterface(webView)
        webView.apply {
            settings.apply {
                // enable Javascript
                javaScriptEnabled = true
                settings.javaScriptEnabled = true
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // 롤리팝부터 Mixed Content 에러 막기 위함.
                    webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
                addJavascriptInterface(javascriptInterface, BuzzAdBenefitJavascriptInterface.INTERFACE_NAME)
            }
        }

        methodChannel = MethodChannel(messenger, "webview_$id")
        methodChannel.setMethodCallHandler(this)
    }

    @Override
    override fun getView(): View {
        return webView
    }

    @Override
    override fun onMethodCall(methodCall: MethodCall, result: MethodChannel.Result) {
        when (methodCall.method) {
            "setUrl" -> setUrl(methodCall, result)
            else -> result.notImplemented()
        }
    }

    private fun setUrl(methodCall: MethodCall, result: MethodChannel.Result) {
        val url = methodCall.arguments as String
        webView.loadUrl(url)
        result.success(null)
    }

    @Override
    override fun dispose() {
        webView.destroy()
    }
}
