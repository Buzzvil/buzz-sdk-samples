//
//  WebFeedViewController.swift
//  WebFeed Sample
//
//  Created by Brice Bang on 2021/04/26.
//

import UIKit
import WebKit

class WebFeedViewController: UIViewController {

    @IBOutlet weak var webView: WKWebView!

    var appId: String = ""

    override func viewDidLoad() {
        super.viewDidLoad()

        let serverUrl = Bundle.main.infoDictionary?["Webfeed Url"] as? String ?? ""
        if (serverUrl == "") {
            print("Server URL is invalid")
            return;
        }
        let url = "\(serverUrl)\(self.appId)/feed"

        self.webView.allowsBackForwardNavigationGestures = true;
        self.webView.uiDelegate = self;

        self.load(url: url)
    }

    func load(url: String) {
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
}

extension WebFeedViewController : WKUIDelegate {
  func webView(_ webView: WKWebView, createWebViewWith configuration: WKWebViewConfiguration, for navigationAction: WKNavigationAction, windowFeatures: WKWindowFeatures) -> WKWebView? {
    if let url = navigationAction.request.url {
      UIApplication.shared.open(url, options: [:], completionHandler: nil)
    }
    return nil;
  }
}
