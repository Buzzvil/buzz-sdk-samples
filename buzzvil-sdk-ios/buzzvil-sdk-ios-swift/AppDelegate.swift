import UIKit
import BuzzvilSDK

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
  func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    let benefitHubConfig = BZVBenefitHubConfig { builder in
      builder.unitID = "YOUR_FEED_UNIT_ID"
    }
    
    // Buzzvil SDK 초기화하기
    let config = BuzzBenefitConfig.Builder(appID: "381196917823555")
      .setDefaultBenefitHubConfig(benefitHubConfig)
      .build()
    
    BuzzBenefit.shared.initialize(with: config)
    
    // 다크 모드 설정하기
    BuzzBenefit.shared.setUserInterfaceStyle(.system)
    BuzzBenefit.shared.setUserInterfaceStyle(.dark)
    BuzzBenefit.shared.setUserInterfaceStyle(.light)
    
    return true
  }
  
  // 로그인 요청하기
  func buzzBenefitLoginLogout() {
    // 로그인을 요청하는 코드입니다.
    let buzzBenefitUser = BuzzBenefitUser.Builder(userID: "USER_ID")
      .setGender(.male)
      .setBirthYear(1996)
      .build()
    
    BuzzBenefit.shared.login(
      with: buzzBenefitUser,
      onSuccess: {
        
      },
      onFailure: { error in
      })
    
    // 로그인 상태를 확인하는 코드입니다.
    BuzzBenefit.shared.isLoggedIn()
    
    // 로그아웃하는 코드입니다.
    BuzzBenefit.shared.logout()
  }
}

