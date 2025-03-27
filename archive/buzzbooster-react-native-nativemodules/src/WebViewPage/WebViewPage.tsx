import * as React from 'react';
import { Platform } from 'react-native';
import { BuzzBoosterReact } from '../BuzzBooster/BuzzBoosterReact';
import WebView from 'react-native-webview';
import type { WebViewEvent, WebViewMessage } from 'react-native-webview/lib/WebViewTypes';

export default function WebViewPage() {
    let url;
    if (Platform.OS == 'ios') {
        url = 'http://localhost:3000/buzzbooster-web/apps/1234/test'
    } else {
        url = 'http://10.0.2.2:3000/buzzbooster-web/apps/1234/test'
    }
    const webViewRef = React.useRef<WebView>(null)
    const string = `
        const BuzzBoosterJavaScriptInterface = {
            postMessage: function(message) {
                window.ReactNativeWebView.postMessage(message);
            }
        };
        true; // this is required, or you'll sometimes get silent failures
    `
    const handleOnLoadEnd = () => {
        webViewRef.current?.injectJavaScript(string);
    }
    const onMessage = (event: WebViewEvent) => {
        BuzzBoosterReact.postJavaScriptMessage((event.nativeEvent as WebViewMessage).data);
    }

    return (
        <WebView
            ref={webViewRef}
            source={{
                uri: url,
            }}
            javaScriptEnabled={true}
            style={{ marginTop: 20 }}
            allowFileAccess={true}
            scalesPageToFit={true}
            originWhitelist={['*']}
            onMessage={onMessage}
            onLoadEnd={handleOnLoadEnd}
        />
    );
}
