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

class ViewController: UIViewController {

  @IBOutlet var loginButton: UIButton!
  @IBOutlet var webView: WKWebView!

  override func viewDidLoad() {
    super.viewDidLoad()

  }

  @IBAction func loginButtonTapped(_ sender: UIButton?) {
    let loggedIn = BuzzAdBenefit.sharedInstance().userProfile?.isSessionRegistered() ?? false

    if loggedIn {
      BuzzAdBenefit.setUserProfile(nil)
    } else {
      let userProfile = BABUserProfile(userId: YOUR_SERVICE_USER_ID, birthYear: 1985, gender: BABUserGenderMale)
      BuzzAdBenefit.setUserProfile(userProfile)

    }
  }

}

