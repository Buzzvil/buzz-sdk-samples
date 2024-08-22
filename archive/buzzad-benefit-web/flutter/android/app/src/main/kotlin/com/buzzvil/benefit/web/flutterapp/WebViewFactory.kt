package com.buzzvil.benefit.web.flutterapp

import android.content.Context
import com.buzzvil.benefit.web.flutterapp.FlutterWebView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class WebViewFactory(private val messenger: BinaryMessenger) :
        PlatformViewFactory(StandardMessageCodec.INSTANCE) {

    override fun create(context: Context, id: Int, o: Any?): PlatformView {
        return FlutterWebView(context, messenger, id)
    }
}
