import SwiftUI
import BuzzvilSDK

@main
struct SampleApp: App {
  @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
  
  var body: some Scene {
    WindowGroup {
      ContentView()
    }
  }
}

class AppDelegate: UIResponder, UIApplicationDelegate {
  func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    initializeSDK()
    buzzBenefitLogin()
    
    // Optional
    applyCustomTheme()
    
    return true
  }
  
  // 시작하기 - SDK 초기화하기
  private func initializeSDK() {
    let config = BuzzBenefitConfig.Builder(appId: "YOUR_APP_ID")
      .build()
    
    BuzzBenefit.shared.initialize(with: config)
  }
  
  // 시작하기 - 로그인 요청하기
  private func buzzBenefitLogin() {
    // 로그인을 요청하는 코드입니다.
    let buzzBenefitUser = BuzzBenefitUser.Builder(userId: "PUBLIC_SAMPLE_APP_USER_ID")
      .setGender(.male)
      .setBirthYear(1996)
      .setMarketingStatus(.undetermined) // (optional) BuzzBooster 이벤트 사용 시 필요한 옵션 입니다. (.optIn / .optOut / .undetermined)
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
    _ = BuzzBenefit.shared.isLoggedIn()
  }
  
  private func buzzBenefitLogout() {
    // 로그아웃하는 코드입니다.
    BuzzBenefit.shared.logout()
  }
  
  // 커스터마이징
  private func applyCustomTheme() {
    let theme = BuzzTheme.Builder()
      .ctaViewClass(CustomCtaView.self)
      .build()
    BuzzAdBenefit.shared.setTheme(theme)
  }
}
