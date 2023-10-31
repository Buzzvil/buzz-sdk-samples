import UIKit
import BuzzvilSDK

class BenefitHubViewController: UIViewController {
  override func viewDidLoad() {
    super.viewDidLoad()
    let benefitHub = BZVBenefitHub { _ in
      
    }
    
    benefitHub.show(on: self)
  }
}
