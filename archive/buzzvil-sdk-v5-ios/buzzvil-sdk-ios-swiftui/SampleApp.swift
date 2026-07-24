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
    applyUserInterfaceStyle()
    applyCustomTheme()
    applyFeedCustomTheme()
    applyVideoPlayMode()
    
    return true
  }
  
  // 시작하기 - SDK 초기화하기
  private func initializeSDK() {
    let defaultFeedConfig = BZVFeedConfig { builder in
      builder.unitID = "YOUR_FEED_UNIT_ID"
      
      // 베네핏허브 헤더 자체 구현하기
      // builder.headerViewClass = CustomFeedHeaderView.self
      
      // 베네핏허브 일반 광고 디자인 자체 구현하기
      // builder.adViewClass = CustomFeedAdView.self
      
      // 베네핏허브 쇼핑 적립 광고 디자인 자체 구현하기
      // builder.cpsAdViewClass = CustomFeedCpsAdView.self
    }
    
    let config = BuzzBenefitConfig.Builder(appID: "YOUR_APP_ID")
      .setDefaultFeedConfig(defaultFeedConfig)
      .build()
    
    BuzzBenefit.shared.initialize(with: config)
  }
  
  // 시작하기 - 로그인 요청하기
  private func buzzBenefitLogin() {
    // 로그인을 요청하는 코드입니다.
    let buzzBenefitUser = BuzzBenefitUser.Builder(userID: "USER_ID")
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
  
  // 다크 모드 설정하기
  private func applyUserInterfaceStyle() {
    BuzzBenefit.shared.setUserInterfaceStyle(.system) // (.system / .dark / .light)
  }
  
  // 커스터마이징
  private func applyCustomTheme() {
    let theme = BuzzBenefitTheme { builder in
      // 주요 색상
      // builder.primaryColor = YOUR_PRIMARY_COLOR
      // builder.primaryLightColor = YOUR_PRIMARY_LIGHT_COLOR
      
      // 리워드 아이콘
      // builder.rewardIcon = YOUR_REWARD_ICON
      // builder.participatedIcon = YOUR_PARTICIPATED_ICON
      
      // 자체 구현한 CTA 버튼 GlobalTheme 적용하기
      // builder.ctaViewClass = CustomCtaView.self
      
      // Gradient 배경 적용하기
      // builder.backgroundGradientColors = [.systemRed, .systemBlue]
    }
    
    BuzzBenefit.shared.setGlobalTheme(theme)
  }
  
  // 커스터마이징 - 베네핏허브
  private func applyFeedCustomTheme() {
    let feedTheme = BZVFeedTheme { builder in
      // 내비게이션 바 UI 스트링 변경하기
      // builder.navigationBarTitle = "YOUR_TITLE"
      
      // 베네핏허브 광고 분류 필터
      // builder.usePrimaryColorInFilter = true
      
      // 광고 미할당 안내 UI
      // builder.noFillErrorImage = UIImage(named: "NO_FILL_ERROR_IMAGE")
      // builder.noFillErrorTitle = "noFillErrorTitle"
      // builder.noFillErrorDescription1st = "noFillErrorDescription1st"
      // builder.noFillErrorDescription2nd = "noFillErrorDescription2nd"
      // builder.noFillErrorDescription3rd = "noFillErrorDescription3rd"
      // builder.noFillErrorDescriptionFinalAllFilter = "noFillErrorDescriptionFinalAllFilter"
      // builder.noFillErrorDescriptionFinalOtherFilters = "noFillErrorDescriptionFinalOtherFilters"
      // builder.noFillErrorButton1st = "noFillErrorButton1st"
      // builder.noFillErrorButton2nd = "noFillErrorButton2nd"
      // builder.noFillErrorButton3rd = "noFillErrorButton3rd"
      // builder.noFillErrorButtonFinalAllFilter = "noFillErrorButtonFinalAllFilter"
      // builder.noFillErrorButtonFinalOtherFilters = "noFillErrorButtonFinalOtherFilters"
      
      // 유저 프로필 오류 안내 UI
      // builder.userProfileErrorImage = ...
      // builder.userProfileErrorTitle = ...
      // builder.userProfileErrorDescription = ...
      
      // 기타 오류화면 UI
      // builder.privacyPolicyErrorImage = ...
      // builder.agePolicyErrorImage = ...
      // builder.networkErrorImage = ...
      // builder.unknownErrorImage = ...
      
      // ATT 허용 유도 모달
      // builder.appTrackingTransparencyGuideModalImage = ...
    }
    
    BZVBuzzAdFeed.setDefaultTheme(feedTheme)
  }
  
  // 동영상 광고 재생 조건 변경하기
  private func applyVideoPlayMode() {
    let userPreferences = BZVUserPreferences { builder in
      builder.autoPlayType = .onWifi // (.onWifi / .enabled / .disabled)
    }
    
    BuzzAdBenefit.setUserPreferences(userPreferences)
  }
}
