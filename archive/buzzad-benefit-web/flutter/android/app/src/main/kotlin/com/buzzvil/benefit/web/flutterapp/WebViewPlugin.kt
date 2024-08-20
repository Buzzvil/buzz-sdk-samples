package com.buzzvil.benefit.web.flutterapp

import androidx.annotation.NonNull
import com.buzzvil.benefit.web.flutterapp.WebViewFactory
import io.flutter.embedding.engine.plugins.FlutterPlugin

class WebViewPlugin : FlutterPlugin {

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        flutterPluginBinding.platformViewRegistry
                .registerViewFactory(
                        "webview",
                        WebViewFactory(flutterPluginBinding.binaryMessenger)
                )
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    }
}
