import UIKit
import BuzzvilSDK

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
  var window: UIWindow? = nil
  
  /** BABSample
   AppId: 325625817193493
   FeedUnitId : 59026668927900
   NativeUnitId: 453995955032448
   InterstitialUnitId : 189682733480080
   */
  
  /** BzzvilSDKSample
   AppId: 381196917823555
   FeedUnitId : 117188628912128
   */

  
  func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    let benefitHubConfig = BZVBenefitHubConfig { builder in
      builder.unitID = "59026668927900" // ##ARTHUR
    }
    
    // Buzzvil SDK 초기화하기
    let config = BuzzBenefitConfig.Builder(appID: "325625817193493") // ##ARTHUR
      .setDefaultBenefitHubConfig(benefitHubConfig)
      .build()
    
    themeCustomize()
    
    BuzzBenefit.shared.initialize(with: config)
    
    setBuzzBenefitHubTheme()
    buzzBenefitLogin()
    
    // 다크 모드 설정하기
    BuzzBenefit.shared.setUserInterfaceStyle(.system)
//    BuzzBenefit.shared.setUserInterfaceStyle(.dark)
//    BuzzBenefit.shared.setUserInterfaceStyle(.light)
    
    window = UIWindow(frame: UIScreen.main.bounds)
    let viewController = ViewController()
    let navigationController = UINavigationController(rootViewController: viewController)
    window?.rootViewController = navigationController
    window?.makeKeyAndVisible()
    
    return true
  }
  
  // 로그인 요청하기
  func buzzBenefitLogin() {
    // 로그인을 요청하는 코드입니다.
    let buzzBenefitUser = BuzzBenefitUser.Builder(userID: "USER_ID")
      .setGender(.male)
      .setBirthYear(1996)
      .build()
    
    BuzzBenefit.shared.login(
      with: buzzBenefitUser,
      onSuccess: {
        // 로그인이 성공한 경우 호출됩니다.
      },
      onFailure: { error in
        // 로그인이 실패한 경우 호출됩니다.
      }
    )
    
    // 로그인 상태를 확인하는 코드입니다.
    BuzzBenefit.shared.isLoggedIn()
  }
  
  func buzzBenefitLogout() {
       // 로그아웃하는 코드입니다.
    BuzzBenefit.shared.logout()
  }
  
  // 커스터마이징
  func themeCustomize() {
    let theme = BuzzBenefitTheme()
    // 주요 색상
    theme.setPrimaryColor(UIColor.red)
    theme.setPrimaryLightColor(UIColor.blue)
    
    // 리워드 아이콘
//    theme.setRewardIcon(UIImage(named: "YOUR_REWARD_ICON"))
    BuzzBenefitTheme.setGlobalTheme(theme)
  }
  
  func setBuzzBenefitHubTheme() {
    // 광고 분류 필터
    let benefitHubTheme = BZVBenefitHubTheme { builder in
      builder.usePrimaryColorInFilter = true
    }
    
    BZVBenefitHub.setDefaultTheme(benefitHubTheme)
  }
}

