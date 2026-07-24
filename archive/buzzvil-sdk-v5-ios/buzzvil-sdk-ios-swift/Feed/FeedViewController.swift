import UIKit
import BuzzvilSDK

class FeedViewController: UIViewController {
  override func viewDidLoad() {
    super.viewDidLoad()
    
    showFeed()
  }
  
  // 피드 표시하기
  func showFeed() {
    let buzzAdFeed = BZVBuzzAdFeed { _ in }
    
    buzzAdFeed.show(on: self)
  }
  
  // 피드 표시하기
  func showFeedWithUnitID() {
    let buzzAdFeed = BZVBuzzAdFeed { builder in
      builder.config = BZVFeedConfig { builder in
        builder.unitID = "SECOND_FEED_UNIT_ID"
      }
    }
    buzzAdFeed.show(on: self)
  }
  
  // 필터 선택하면서 피드 표시하기
  func showFeedWithSelectFilter() {
    let buzzAdFeed = BZVBuzzAdFeed { builder in
      builder.config = BZVFeedConfig { builder in
        builder.unitID = "BENEFITHUB_UNIT_ID"
        builder.initialSelectedFilterName = "클릭적립"
      }
    }
    buzzAdFeed.show(on: self)
  }
  
  // 럭키박스 표시하기
  func showLuckyBox() {
    let buzzAdFeed = BZVBuzzAdFeed { builder in
      builder.config = BZVFeedConfig { builder in
        builder.unitID = "BENEFITHUB_UNIT_ID"
        builder.initialNavigationPage = .luckyBox
      }
    }
    buzzAdFeed.show(on: self)
  }

  // 적립 가능한 포인트 표시하기
  func getAvailiableReward() {
    let buzzAdFeed = BZVBuzzAdFeed { _ in }
    buzzAdFeed.load(
      onSuccess: {
        // 광고 로드에 성공했을 경우
        // 적립 가능한 포인트를 직접 구현한 UI에 업데이트합니다.
        let availiableReward = buzzAdFeed.availableRewards
      },
      onFailure: { error in
        // 적립 가능한 포인트를 가져올 수 없는 경우
      }
    )
  }
}
