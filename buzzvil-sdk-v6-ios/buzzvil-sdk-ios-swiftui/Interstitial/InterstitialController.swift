import UIKit
import Combine
import BuzzvilSDK

final class InterstitialController: NSObject, ObservableObject {
  @Published var isLoaded = false
  
  lazy var interstitial = BuzzInterstitial(unitId: "YOUR_INTERSTITIAL_UNIT_ID", type: .dialog)
  
  func loadAd() {
    isLoaded = false
    interstitial.delegate = self
    interstitial.load()
  }
}

extension InterstitialController: BuzzInterstitialDelegate {
  func buzzInterstitialDidLoadAd(_ interstitial: BuzzInterstitial) {
    // 할당된 광고가 있으면 호출됩니다.
    // Interstitial 광고를 화면에 표시합니다.
    DispatchQueue.main.async {
      self.isLoaded = true
    }
  }
  
  func buzzInterstitialDidFail(toLoadAd interstitial: BuzzInterstitial, withError: any Error) {
    // 할당된 광고가 없으면 호출됩니다.
    DispatchQueue.main.async {
      self.isLoaded = false
    }
  }
  
  func buzzInterstitialDidDismiss(_ viewController: UIViewController) {
    // Interstitial 지면이 종료되면 호출됩니다.
    // 필요에 따라 추가 기능을 구현하세요.
  }
}
