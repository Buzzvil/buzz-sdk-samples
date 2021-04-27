//
//  MainViewController.swift
//  WebFeed Sample
//
//  Created by Brice Bang on 2021/04/26.
//

import UIKit

class MainViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func openWebFeed(sender: UIButton) {

        guard let nextViewController = self.storyboard?.instantiateViewController(withIdentifier: "WebFeedViewController") else {
            return
        }
        self.navigationController?.pushViewController(nextViewController, animated: true)
    }
    
}

