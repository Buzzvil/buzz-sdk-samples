import BuzzvilSDK
import UIKit

// 하위 뷰 컨트롤러로 베네핏허브 연동하기
class BenefitHubContainerViewController: UIViewController {
  let benefitHub = BuzzBenefitHub()
  
  override func viewDidLoad() {
    super.viewDidLoad()
    view.backgroundColor = .systemBackground

    displayContentViewController(benefitHub.createViewController())
  }
  
  private func displayContentViewController(_ contentViewController: UIViewController) {
    addChild(contentViewController)
    contentViewController.view.frame = view.bounds
    view.addSubview(contentViewController.view)
    contentViewController.didMove(toParent: self)
  }
}
