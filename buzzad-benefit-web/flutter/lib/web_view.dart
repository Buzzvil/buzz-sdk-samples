import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

typedef void WebViewCreatedCallback(WebViewController controller);

class WebView extends StatefulWidget {
  const WebView({
    Key key,
    this.onWebViewViewCreated,
  }) : super(key: key);

  final WebViewCreatedCallback onWebViewViewCreated;

  @override
  State<StatefulWidget> createState() => _WebViewState();
}

class _WebViewState extends State<WebView> {
  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return AndroidView(
        viewType: 'webview',
        onPlatformViewCreated: _onPlatformViewCreated,
      );
    }
    // TODO ios
    return Text('$defaultTargetPlatform is not supported!');
  }

  void _onPlatformViewCreated(int id) {
    if (widget.onWebViewViewCreated == null)
      return;
    widget.onWebViewViewCreated(new WebViewController._(id));
  }
}

class WebViewController {
  WebViewController._(int id)
      : _channel = new MethodChannel('webview_$id');

  final MethodChannel _channel;

  Future<void> setUrl(String url) async {
    assert(url != null);
    return _channel.invokeMethod('setUrl', url);
  }
}
