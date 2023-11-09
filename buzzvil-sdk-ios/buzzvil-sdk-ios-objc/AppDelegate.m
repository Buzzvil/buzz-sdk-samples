#import "AppDelegate.h"
#import "ViewController.h"
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
  }];
  
  // Buzzvil SDK 초기화하기
  BuzzBenefitConfig *config = [BuzzBenefitConfig configWithBlock:^(BuzzBenefitConfigBuilder * _Nonnull builder) {
//    builder.appID = @"YOUR_APP_ID"; ##ARTHUR
    builder.appID = @"325625817193493";
    builder.defaultFeedConfig = benefitHubConfig;
  }];
  [[BuzzBenefit sharedInstance] initializeWithConfig:config];
  
  [self buzzBenefitLogin];
  
  // 다크모드 설정하기 TODO
  [[BuzzBenefit sharedInstance] setUserInterfaceStyle:BuzzBenefitUserInterfaceStyleSystem];
//  [[BuzzBenefit sharedInstance] setUserInterfaceStyle:BuzzBenefitUserInterfaceStyleLight];
//  [[BuzzBenefit sharedInstance] setUserInterfaceStyle:BuzzBenefitUserInterfaceStyleDark];
  
  _window = [[UIWindow alloc] initWithFrame:UIScreen.mainScreen.bounds];
  UINavigationController *navigationVC = [[UINavigationController alloc] initWithRootViewController:[[ViewController alloc] init]];
  _window.rootViewController = navigationVC;
  [_window makeKeyAndVisible];
  
  return YES;
}

- (void)buzzBenefitLogin {
//  BuzzBenefitUser *buzzBenefitUser = [BuzzBenefitUser userWithBlock:^(BuzzBenefitUserBuilder * _Nonnull builder) {
//    builder.userID = @"USER_ID";
//    builder.gender = BuzzBenefitUserGenderMale;
//    builder.birthYear = 1996;
//  }];
  
//  [[BuzzBenefit sharedInstance] loginWithUser:buzzBenefitUser onSuccess:^{
//    // 로그인이 성공한 경우 호출됩니다.
//  } onFailure:^(NSError * _Nonnull error) {
//    // 로그인이 실패한 경우 호출됩니다.
//  }];
  
  // 로그인 상태를 확인하는 코드입니다.
  [[BuzzBenefit sharedInstance] isLoggedIn];
  
}

- (void)buzzBenefitLogout {
   // 로그아웃하는 코드입니다.
  [[BuzzBenefit sharedInstance] logout];
}

// 커스터마이징
- (void)themeCustomize {
//  BuzzBenefitTheme *theme = [BuzzBenefitTheme alloc];
}

- (void)setBuzzBenefitHubTheme {
  BZVBenefitHubTheme *benefitHubTheme = [[BZVBenefitHubTheme alloc] initWithBlock:^(BZVBenefitHubThemeBuilder * _Nonnull builder) {
  }];
}

@end
