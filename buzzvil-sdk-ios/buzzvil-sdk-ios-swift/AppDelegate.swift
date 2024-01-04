import UIKit
import BuzzvilSDK

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
  var window: UIWindow? = nil
  
  func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    let feedConfig = BZVFeedConfig { builder in
      builder.unitID = "YOUR_FEED_UNIT_ID"
      builder.headerViewClass = CustomFeedHeaderView.self // 헤더
      builder.adViewClass = CustomFeedAdView.self // 일반 광고 디자인
      builder.cpsAdViewClass = CustomFeedCpsAdView.self // 쇼핑 광고 디자인
    }
    
    // Buzzvil SDK 초기화하기
    let config = BuzzBenefitConfig.Builder(appID: "YOUR_APP_ID")
      .setDefaultFeedConfig(feedConfig)
      .build()
    
    themeCustomize()
    
    BuzzBenefit.shared.initialize(with: config)
    
    setFeedTheme()
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
      .setMarketingStatus(.undetermined) // (optional) BuzzBooster 이벤트 사용 시 필요한 옵션 입니다. (.optIn/.optOut /.undetermined )
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
    let theme = BuzzBenefitTheme { builder in
      
      // 주요 색상
      // builder.primaryColor = YOUR_PRIMARY_COLOR
      // builder.primaryLightColor = YOUR_PRIMARY_LIGHT_COLOR
      
      // 리워드 아이콘
      // builder.rewardIcon = YOUR_REWARD_ICON
      // builder.participatedIcon = YOUR_PARTICIPATED_ICON
    }
    
    BuzzBenefit.shared.setGlobalTheme(theme)
  }
  
  func setFeedTheme() {
    // 광고 분류 필터
    let feedTheme = BZVFeedTheme { builder in
      builder.navigationBarTitle = "YOUR_TITLE" // 네비게이션 바 UI 스트링 변경하기
      builder.usePrimaryColorInFilter = true // 광고 분류 필터
      
      // 광고 미할당 안내 UI
      /*
      builder.noFillErrorImage = UIImage(named: "NO_FILL_ERROR_IMAGE")
      builder.noFillErrorTitle = "noFillErrorTitle"
      builder.noFillErrorDescription1st = "noFillErrorDescription1st"
      builder.noFillErrorDescription2nd = "noFillErrorDescription2nd"
      builder.noFillErrorDescription3rd = "noFillErrorDescription3rd"
      builder.noFillErrorDescriptionFinalAllFilter = "noFillErrorDescriptionFinalAllFilter"
      builder.noFillErrorDescriptionFinalOtherFilters = "noFillErrorDescriptionFinalOtherFilters"
      builder.noFillErrorButton1st = "noFillErrorButton1st"
      builder.noFillErrorButton2nd = "noFillErrorButton2nd"
      builder.noFillErrorButton3rd = "noFillErrorButton3rd"
      builder.noFillErrorButtonFinalAllFilter = "noFillErrorButtonFinalAllFilter"
      builder.noFillErrorButtonFinalOtherFilters = "noFillErrorButtonFinalOtherFilters"
     */
      
      // 유저 프로필 오류 안내 UI
      /*
      builder.userProfileErrorImage = ...
      builder.userProfileErrorTitle = ...
      builder.userProfileErrorDescription = ...
     */
      
      // 기타 오류화면 UI
      /*
      builder.privacyPolicyErrorImage = ...
      builder.agePolicyErrorImage = ...
      builder.networkErrorImage = ...
      builder.unknownErrorImage = ...
     */
      
      // ATT 허용 유도 모달
      /*
      builder.appTrackingTransparencyGuideModalImage = ...
     */
    }
    
    BZVBuzzAdFeed.setDefaultTheme(feedTheme)
  }
  
  // 동영상 광고 재생 조건 변경하기
  func setVideoPlayMod() {
    let userPreferences = BZVUserPreferences { builder in
      builder.autoPlayType = .onWifi
//      builder.autoPlayType = .enabled
//      builder.autoPlayType = .disabled
    }
    
    BuzzAdBenefit.setUserPreferences(userPreferences)
  }
}

