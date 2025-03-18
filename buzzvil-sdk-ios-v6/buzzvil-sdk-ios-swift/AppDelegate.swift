import UIKit
import BuzzvilSDK

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
  var window: UIWindow? = nil

  func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    // Override point for customization after application launch.
    
    // Buzzvil SDK 초기화하기
    let config = BuzzBenefitConfig.Builder(appID: "YOUR_APP_ID")
      .build()
    
    BuzzBenefit.shared.initialize(with: config)
    
    buzzBenefitLogin()
    
    window = UIWindow(frame: UIScreen.main.bounds)
    let viewController = ViewController()
    let navigationController = UINavigationController(rootViewController: viewController)
    window?.rootViewController = navigationController
    window?.makeKeyAndVisible()
    
    return true
  }
  
  // 로그인 요청하기
  private func buzzBenefitLogin() {
    // 로그인을 요청하는 코드입니다.
    let buzzBenefitUser = BuzzBenefitUser.Builder(userID: "public_sample_app")
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
    BuzzBenefit.shared.isLoggedIn()
  }
  
  func buzzBenefitLogout() {
    // 로그아웃하는 코드입니다.
    BuzzBenefit.shared.logout()
  }

  // MARK: UISceneSession Lifecycle

  func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
    // Called when a new scene session is being created.
    // Use this method to select a configuration to create the new scene with.
    return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
  }

  func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
    // Called when the user discards a scene session.
    // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
    // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
  }


}

