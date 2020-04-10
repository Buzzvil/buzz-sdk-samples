//
//  ViewController.swift
//  BABWebSample
//
//  Created by Jaehee Ko on 10/04/2020.
//  Copyright © 2020 Buzzvil. All rights reserved.
//

import UIKit
import WebKit
import BuzzAdBenefit
import BuzzAdBenefitWebInterface

class ViewController: UIViewController, WKScriptMessageHandler {

  @IBOutlet var loginButton: UIButton!
  @IBOutlet var webViewContainer: UIView!
  var webView: WKWebView!
  var webInterface: BABWebInterface!

  override func viewDidLoad() {
    super.viewDidLoad()

    let config = WKWebViewConfiguration()
    let contentController = WKUserContentController()
    contentController.add(self, name: BuzzAdBenefitWebInterfaceName)
    config.userContentController = contentController

    webView = WKWebView(frame: webViewContainer.bounds, configuration: config)
    webViewContainer.addSubview(webView)

    webInterface = BABWebInterface(webView: webView)

    NotificationCenter.default.addObserver(self, selector: #selector(sessionRegistered), name: NSNotification.Name.BABSessionRegistered, object: nil)
  }

  @IBAction func loginButtonTapped(_ sender: UIButton?) {
    let loggedIn = BuzzAdBenefit.sharedInstance().userProfile?.isSessionRegistered() ?? false

    if loggedIn {
      BuzzAdBenefit.setUserProfile(nil)
    } else {
      let userProfile = BABUserProfile(userId: "YOUR_SERVICE_USER_ID", birthYear: 1985, gender: BABUserGenderMale)
      BuzzAdBenefit.setUserProfile(userProfile)
    }
  }

  func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
    webInterface.handle(message)
  }

  @objc func sessionRegistered() {
    if let url = URL(string: "https://buzzvil.github.io/buzzad-benefit-sdk-publisher-web/") {
      let request = URLRequest(url: url, cachePolicy: .reloadIgnoringCacheData, timeoutInterval: 30)
      webView.load(request)
    }
  }
}

