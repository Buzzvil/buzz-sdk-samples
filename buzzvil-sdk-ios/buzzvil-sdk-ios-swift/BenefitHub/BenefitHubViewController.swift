import UIKit
import BuzzvilSDK

class BenefitHubViewController: UIViewController {
  override func viewDidLoad() {
    super.viewDidLoad()
    
    showBenefitHub()
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
    let benefitHub = BZVBenefitHub { _ in }
    benefitHub.load(
      onSuccess: {
        // 광고 로드에 성공했을 경우
        // 적립 가능한 포인트를 직접 구현한 UI에 업데이트합니다.
        let availiableReward = benefitHub.availableRewards
      },
      onFailure: { error in
        // 적립 가능한 포인트를 가져올 수 없는 경우
      }
    )
  }
}
