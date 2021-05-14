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
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.serverDomainLabel.text = Bundle.main.infoDictionary?["Webfeed Url"] as? String
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        guard let webFeedViewController = segue.destination as? WebFeedViewController
            else { return }
        webFeedViewController.appId = self.appIdTextField.text ?? ""
    }
    @IBAction func onWebFeedButtonClicked(_ sender: Any) {
        performSegue(withIdentifier: "openWebFeed", sender: nil)
    }
}
