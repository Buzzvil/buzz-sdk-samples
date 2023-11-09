#import "AppDelegate.h"
#import "ViewController.h"
#import "Feed/Custom/CustomFeedHeaderView.h"
#import "Feed/Custom/CustomFeedAdView.h"
#import "Feed/Custom/CustomFeedCpsAdView.h"
@import BuzzvilSDK;

@interface AppDelegate ()
@end

@implementation AppDelegate {
  UIWindow *_window;
}

/** BABSample // ##ARTHUR
 AppId: 325625817193493
 FeedUnitId : 59026668927900
 NativeUnitId: 453995955032448
 InterstitialUnitId : 189682733480080
 */

/** BzzvilSDKSample
 AppId: 381196917823555
 FeedUnitId : 117188628912128
 */

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
  // 베네핏 허브 초기화 하기
  BZVFeedConfig *feedConfig = [BZVFeedConfig configWithBlock:^(BZVFeedConfigBuilder * _Nonnull builder) {
//    builder.unitID = @"YOUR_FEED_UNIT_ID"; ##ARTHUR
    builder.unitID = @"59026668927900";
    builder.headerViewClass = [CustomFeedHeaderView self]; // 헤더
//    builder.adViewClass = [CustomFeedAdView self]; // 일반 광고 디자인
//    builder.cpsAdViewClass = [CustomFeedCpsAdView self]; // 쇼핑 광고 디자인
  }];
  
  // Buzzvil SDK 초기화하기
  BuzzBenefitConfig *config = [BuzzBenefitConfig configWithBlock:^(BuzzBenefitConfigBuilder * _Nonnull builder) {
//    builder.appID = @"YOUR_APP_ID"; ##ARTHUR
    builder.appID = @"325625817193493";
    builder.defaultFeedConfig = feedConfig;
  }];
  
  [self themeCustomize];
  [self setFeedTheme];
  
  [[BuzzBenefit sharedInstance] initializeWithConfig:config];
  
  [self buzzBenefitLogin];
  
  // 다크모드 설정하기 TODO
//  [[BuzzBenefit sharedInstance] setUserInterfaceStyle:BuzzBenefitUserInterfaceStyleSystem];
//  [[BuzzBenefit sharedInstance] setUserInterfaceStyle:BuzzBenefitUserInterfaceStyleLight];
//  [[BuzzBenefit sharedInstance] setUserInterfaceStyle:BuzzBenefitUserInterfaceStyleDark];
  
  _window = [[UIWindow alloc] initWithFrame:UIScreen.mainScreen.bounds];
  UINavigationController *navigationVC = [[UINavigationController alloc] initWithRootViewController:[[ViewController alloc] init]];
  _window.rootViewController = navigationVC;
  [_window makeKeyAndVisible];
  
  return YES;
}

- (void)buzzBenefitLogin {
  BuzzBenefitUser *buzzBenefitUser = [BuzzBenefitUser userWithBlock:^(BuzzBenefitUserBuilder * _Nonnull builder) {
    builder.userID = @"USER_ID";
    builder.gender = BuzzBenefitUserGenderMale;
    builder.birthYear = 1996;
  }];
  
  [[BuzzBenefit sharedInstance] loginWithUser:buzzBenefitUser onSuccess:^{
    // 로그인이 성공한 경우 호출됩니다.
  } onFailure:^(NSError * _Nonnull error) {
    // 로그인이 실패한 경우 호출됩니다.
  }];
  
  // 로그인 상태를 확인하는 코드입니다.
  [[BuzzBenefit sharedInstance] isLoggedIn];
}

- (void)buzzBenefitLogout {
   // 로그아웃하는 코드입니다.
  [[BuzzBenefit sharedInstance] logout];
}

// 커스터마이징
- (void)themeCustomize {
  BuzzBenefitTheme *theme = [BuzzBenefitTheme themeWithBlock:^(BuzzBenefitThemeBuilder * _Nonnull builder) {
    // 주요 색상
    // builder.primaryColor = YOUR_PRIMARY_COLOR;
    // builder.primaryLightColor = YOUR_PRIMARY_LIGHT_COLOR;
    builder.primaryColor = [UIColor redColor];
    builder.primaryLightColor = [UIColor blueColor];
    
    // 리워드 아이콘
    // builder.rewardIcon = YOUR_REWARD_ICON;
    // builder.participatedIcon = YOUR_PARTICIPATED_ICON;
  }];
  [[BuzzBenefit sharedInstance] setGlobalTheme:theme];
}

- (void)setFeedTheme {
  BZVFeedTheme *feedTheme = [BZVFeedTheme themeWithBlock:^(BZVFeedThemeBuilder * _Nonnull builder) {
    builder.navigationBarTitle = @"YOUR_TITLE";
    
    // 광고 분류 필터
    builder.usePrimaryColorInFilter = YES;
    
    // 광고 미할당 안내 UI
    // builder.noFillErrorImage = ...;
    // builder.noFillErrorTitle = ...;
    // builder.noFillErrorDescription1st = ...;
    // builder.noFillErrorDescription2nd = ...;
    // builder.noFillErrorDescription3rd = ...;
    // builder.noFillErrorDescriptionFinalAllFilter = ...;
    // builder.noFillErrorDescriptionFinalOtherFilters = ...;
    // builder.noFillErrorButton1st = ...;
    // builder.noFillErrorButton2nd = ...;
    // builder.noFillErrorButton3rd = ...;
    // builder.noFillErrorButtonFinalAllFilter = ...;
    // builder.noFillErrorButtonFinalOtherFilters = ...;
    
    // 유저 프로필 오류 안내 UI
    // builder.userProfileErrorImage = ...;
    // builder.userProfileErrorTitle = ...;
    // builder.userProfileErrorDescription = ...;
    
    // 기타 오류 화면 UI
    // builder.privacyPolicyErrorImage = ...
    // builder.agePolicyErrorImage = ...
    // builder.networkErrorImage = ...
    // builder.unknownErrorImage = ...
    
    // ATT 허용 유도 모달
    // builder.appTrackingTransparencyGuideModalImage = ...;
  }];
//  [BZVBuzzAdFeed setDefaultTheme:feedTheme];
}

// 동영상 광고 재생 조건 변경하기
- (void)setVideoPlayMod {
  BZVUserPreferences *userPreferences = [BZVUserPreferences userPreferencesWithBlock:^(BZVUserPreferencesBuilder * _Nonnull builder) {
    builder.autoPlayType = BZVVideoAutoPlayOnWifi;
    // builder.autoPlayType = BZVVideoAutoPlayEnabled;
    // builder.autoPlayType = BZVVideoAutoPlayDisabled;
  }];
  [BuzzAdBenefit setUserPreferences:userPreferences];
}

@end
