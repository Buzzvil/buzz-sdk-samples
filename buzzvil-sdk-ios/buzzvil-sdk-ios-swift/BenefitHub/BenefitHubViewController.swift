import UIKit
import BuzzvilSDK

class BenefitHubViewController: UIViewController {
  override func viewDidLoad() {
    super.viewDidLoad()
  }
  
  // 베네핏허브 표시하기
  func showBenefitHub() {
    let benefitHub = BZVBenefitHub { _ in }
    
    benefitHub.show(on: self)
  }
  
  // 베네핏허브 표시하기
  func showBenefitHubWithUnitID() {
    let benefitHub = BZVBenefitHub { builder in
      builder.config = BZVBenefitHubConfig { builder in
        builder.unitID = "SECOND_BENEFIT_HUB_UNIT_ID"
      }
    }
    benefitHub.show(on: self)
  }
  
  // 적립 가능한 포인트 표시하기
  func getAvailiableReward() {
    // ##ARTHUR
//    let benefitHub = BZVBenefitHub { _ in }
//    benefitHub.load(
//        onSuccess: { availableReward in
//            // 적립 가능한 포인트를 직접 구현한 UI에 업데이트합니다.
//        },
//        onFailure: { error in
//            // 적립 가능한 포인트를 가져올 수 없는 경우
//        }
//    )
  }
}
