import BuzzvilSDK
import UIKit

class InterstitialViewController: UIViewController {
  private lazy var buzzAdInterstitial = BuzzInterstitial(unitId: "YOUR_INTERSTITIAL_UNIT_ID", type: .dialog)
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    view.backgroundColor = .systemBackground

    buzzAdInterstitial.delegate = self
    buzzAdInterstitial.load()
    // Do any additional setup after loading the view.
  }
}

extension InterstitialViewController: BuzzInterstitialDelegate {
  func buzzInterstitialDidLoadAd(_ interstitial: BuzzInterstitial) {
    // 할당된 광고가 있으면 호출됩니다.
    // Interstitial 광고를 화면에 표시합니다.
    interstitial.present(on: self)
  }
  
  func buzzInterstitialDidFail(toLoadAd interstitial: BuzzInterstitial, withError: any Error) {
    // 할당된 광고가 없으면 호출됩니다.
  }
  
  func buzzInterstitialDidDismiss(_ viewController: UIViewController) {
    // Interstitial 지면이 종료되면 호출됩니다.
    // 필요에 따라 추가 기능을 구현하세요.
  }
}
