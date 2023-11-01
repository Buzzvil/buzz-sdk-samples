import Foundation
import BuzzAdBenefitSDK

final class CustomLauncher: NSObject, BZVLauncher {
  // 런처와 관련된 이벤트를 수신할 수 있습니다.
  var delegate: BZVLauncherDelegate?

  func open(with launchInfo: BZVLaunchInfo) {
    // launchInfo의 인자를 확인하여 광고 또는 콘텐츠인지 미리 판단할 수 있습니다.
    if let ad = launchInfo.ad {
      if ad.isDeepLink {
        // 딥링크 광고일 경우 필요한 작업을 할 수 있습니다.
      }
    } else if let article = launchInfo.article {
      let sourceURL = article.sourceURL
    }

    let customBrowserViewController = CustomBrowserViewController()
    YOUR_ROOT_VIEW_CONTROLLER.presentedViewController?.present(customBrowserViewController, animated: true, completion: nil)
  }
}
