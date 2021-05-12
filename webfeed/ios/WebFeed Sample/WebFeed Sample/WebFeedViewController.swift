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

    override func viewDidLoad() {
        super.viewDidLoad()
        // Your APP ID
        let appId = "123";

        let url = "https://webfeed.buzzvil.com/\(appId)/feed"
        self.load(url: url);

        self.webView.allowsBackForwardNavigationGestures = true;
        self.webView.uiDelegate = self;
    }

    func load(url: String) {
        self.webView.load(URLRequest(url: URL(string: url)!));
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
