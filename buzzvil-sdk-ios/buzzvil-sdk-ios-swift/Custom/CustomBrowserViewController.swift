import UIKit
import BuzzvilSDK

class CustomBrowserViewController: UIViewController {
  private lazy var browserViewController: UIViewController = {
      BuzzAdBrowser.sharedInstance().browserViewController()
    }()
    
    override func viewDidLoad() {
      super.viewDidLoad()
      
      displayContentViewController(browserViewController)
    }

    private func displayContentViewController(_ contentViewController: UIViewController) {
      addChild(contentViewController)
      contentViewController.view.frame = view.bounds
      view.addSubview(contentViewController.view)
      contentViewController.didMove(toParent: self)
    }
}
