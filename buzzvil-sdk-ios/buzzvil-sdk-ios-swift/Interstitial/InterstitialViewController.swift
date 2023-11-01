import BuzzAdBenefitSDK
import UIKit

class InterstitialViewController: UIViewController {
  let buzzAdInterstitial = BZVBuzzAdInterstitial { builder in
    builder.unitId = "YOUR_INTERSTITIAL_UNIT_ID"
    builder.type = .dialog
  }
  override func viewDidLoad() {
    super.viewDidLoad()
    buzzAdInterstitial.delegate = self
    buzzAdInterstitial.load()
    // Do any additional setup after loading the view.
  }
}

extension InterstitialViewController: BZVBuzzAdInterstitialDelegate {
  func bzvBuzzAdInterstitialDidLoadAd(_ interstitial: BZVBuzzAdInterstitial) {
    // 할당된 광고가 있으면 호출됩니다.
    // Interstitial 광고를 화면에 표시합니다.
    interstitial.present(on: self)
  }
  
  func bzvBuzzAdInterstitialDidFail(toLoadAd interstitial: BZVBuzzAdInterstitial, withError error: Error) {
    // 할당된 광고가 없으면 호출됩니다.
  }
  
  func bzvBuzzAdInterstitialDidDismiss(_ viewController: UIViewController) {
    // Interstitial 지면이 종료되면 호출됩니다.
    // 필요에 따라 추가 기능을 구현하세요.
  }
}
