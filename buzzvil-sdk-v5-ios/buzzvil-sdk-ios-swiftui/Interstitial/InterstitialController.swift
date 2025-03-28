import Foundation
import Combine
import BuzzvilSDK

final class InterstitialController: NSObject, ObservableObject {
  @Published var isLoaded = false
  
  lazy var interstitial: BZVBuzzAdInterstitial = {
    let interstitial = BZVBuzzAdInterstitial { builder in
      builder.unitId = "YOUR_INTERSTITIAL_UNIT_ID"
      builder.type = .dialog
      builder.buzzAdBenefitRouter = BuzzBenefitRouterFactory.createFeedRouter()
    }
    interstitial.delegate = self
    return interstitial
  }()
  
  func loadAd() {
    isLoaded = false
    interstitial.load()
  }
}

extension InterstitialController: BZVBuzzAdInterstitialDelegate {
  func bzvBuzzAdInterstitialDidLoadAd(_ interstitial: BZVBuzzAdInterstitial) {
    // 할당된 광고가 있으면 호출됩니다.
    // Interstitial 광고를 화면에 표시합니다.
    DispatchQueue.main.async {
      self.isLoaded = true
    }
  }
  
  func bzvBuzzAdInterstitialDidFail(toLoadAd interstitial: BZVBuzzAdInterstitial, withError error: any Error) {
    // 할당된 광고가 없으면 호출됩니다.
    DispatchQueue.main.async {
      self.isLoaded = false
    }
  }
  
  func bzvBuzzAdInterstitialDidDismiss(_ viewController: UIViewController) {
    // Interstitial 지면이 종료되면 호출됩니다.
    // 필요에 따라 추가 기능을 구현하세요.
  }
}
