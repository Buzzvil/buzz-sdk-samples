//
//  MainViewController.swift
//  WebFeed Sample
//
//  Created by Brice Bang on 2021/04/26.
//

import UIKit

class MainViewController: UIViewController {

    @IBOutlet weak var serverDomainLabel: UILabel!
    @IBOutlet weak var appIdTextField: UITextField!
    @IBOutlet weak var pathnameLabel: UILabel!
    @IBOutlet weak var paramsTextField: UITextField!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.serverDomainLabel.text = Bundle.main.infoDictionary?["Webfeed Url"] as? String
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        guard let webFeedViewController = segue.destination as? WebFeedViewController
            else { return }
        webFeedViewController.appId = self.appIdTextField.text ?? ""
        webFeedViewController.params = self.paramsTextField.text ?? ""
    }
    @IBAction func onParamsTextChanged(_ sender: Any) {
        if let params = paramsTextField.text, !params.isEmpty {
            pathnameLabel.text = "/feed?"
        } else {
            pathnameLabel.text = "/feed"
        }
    }
    @IBAction func onWebFeedButtonClicked(_ sender: Any) {
        performSegue(withIdentifier: "openWebFeed", sender: nil)
    }
}
