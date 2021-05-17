//
//  WebFeedViewController.swift
//  WebFeed Sample
//
//  Created by Brice Bang on 2021/04/26.
//

import UIKit
import WebKit

class WebFeedViewController: UIViewController {
    
    var webView: WKWebView!
    
    var appId: String = ""
    var params: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.createWebView()
        self.openWebFeed()
        
    }
    
    private func createWebView() {
        // Create WebView dynamic to avoid following error
        // WKWebView before IOS 11.0 (NSCoding support was broken in previous versions)
        self.webView = WKWebView()
        // Recognize Auto Layout
        webView.translatesAutoresizingMaskIntoConstraints = false
        
        view.addSubview(webView)

        if #available(iOS 11.0, *) {
            
            let safeArea = self.view.safeAreaLayoutGuide
            webView.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor).isActive = true
            webView.topAnchor.constraint(equalTo: safeArea.topAnchor).isActive = true
            webView.trailingAnchor.constraint(equalTo: safeArea.trailingAnchor).isActive = true
            webView.bottomAnchor.constraint(equalTo: safeArea.bottomAnchor).isActive = true
            
        }
        else {
            
            webView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
            webView.topAnchor.constraint(equalTo: topLayoutGuide.bottomAnchor).isActive = true
            webView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
            webView.bottomAnchor.constraint(equalTo: bottomLayoutGuide.topAnchor).isActive = true
            
        }
    }
    
    private func openWebFeed() {
        let serverUrl = Bundle.main.infoDictionary?["Webfeed Url"] as? String ?? ""
        if (serverUrl == "") {
            print("Server URL is invalid")
            return;
        }
        let params = self.getParams()
        let url = "\(serverUrl)\(self.appId)/feed\(params)"
        
        self.webView.allowsBackForwardNavigationGestures = true;
        self.webView.uiDelegate = self;
        
        self.load(url: url)
    }
    
    private func load(url: String) {
        self.webView.load(URLRequest(url: URL(string: url)!));
        print("WEBFEED: \(url)")
    }
    
    @IBAction func back() {
        if self.webView.canGoBack {
            self.webView.goBack();
        }
    }
    
    @IBAction func forward() {
        if self.webView.canGoForward {
            self.webView.goForward();
        }
    }
    
    private func getParams() -> String {
        if (self.params.isEmpty) {
            return ""
        } else {
            return "?\(self.params)"
        }
    }
}

extension WebFeedViewController : WKUIDelegate {
    func webView(_ webView: WKWebView, createWebViewWith configuration: WKWebViewConfiguration, for navigationAction: WKNavigationAction, windowFeatures: WKWindowFeatures) -> WKWebView? {
        if let url = navigationAction.request.url {
            if #available(iOS 10.0, *) {
                UIApplication.shared.open(url, options: [:], completionHandler: nil)
            } else {
                UIApplication.shared.openURL(url)
            }
        }
        return nil;
    }
}
