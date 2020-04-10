//
//  AppDelegate.swift
//  BABWebSample
//
//  Created by Jaehee Ko on 10/04/2020.
//  Copyright © 2020 Buzzvil. All rights reserved.
//

import UIKit
import BuzzAdBenefit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
  var window: UIWindow?

  func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    let YOUR_APP_ID = "310600461728380"
    let config = BABConfig(appId: YOUR_APP_ID)
    BuzzAdBenefit.initialize(with: config)
    
    return true
  }



}

