#import "AppDelegate.h"
#import "ViewController.h"
#import "Custom/CustomCtaView.h"
@import BuzzvilSDK;

@interface AppDelegate ()

@end

@implementation AppDelegate {
  UIWindow *_window;
}

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
  // Buzzvil SDK 초기화하기
  BuzzBenefitConfig *config = [BuzzBenefitConfig configWithBlock:^(BuzzBenefitConfigBuilder * _Nonnull builder) {
    builder.appID = @"YOUR_APP_ID";
  }];
  
  [[BuzzBenefit sharedInstance] initializeWithConfig:config onCompleted:^{ }];
  
  [self buzzBenefitLogin];
  
  _window = [[UIWindow alloc] initWithFrame:UIScreen.mainScreen.bounds];
  UINavigationController *navigationVC = [[UINavigationController alloc] initWithRootViewController:[[ViewController alloc] init]];
  _window.rootViewController = navigationVC;
  [_window makeKeyAndVisible];
  
  return YES;
}

// 로그인 요청하기
- (void)buzzBenefitLogin {
  // 로그인을 요청하는 코드입니다.
  BuzzBenefitUser *buzzBenefitUser = [BuzzBenefitUser userWithBlock:^(BuzzBenefitUserBuilder * _Nonnull builder) {
    builder.userID = @"PUBLIC_SAMPLE_APP_USER_ID";
    builder.gender = BuzzBenefitUserGenderMale;
    builder.birthYear = 1996;
    builder.marketingStatus = BuzzBenefitMarketingStatusUndetermined; // (optional) BuzzBooster 이벤트 사용 시 필요한 옵션입니다. (BuzzBenefitMarketingStatusOptIn / BuzzBenefitMarketingStatusOptOut / BuzzBenefitMarketingStatusUndetermined)
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

- (void)setBuzzTheme {
  BuzzTheme * buzzTheme = [BuzzTheme themeWithBlock:^(BuzzThemeBuilder * _Nonnull builder) {
    builder.ctaViewClass = CustomCtaView.self;
  }];
  [BuzzAdBenefit.sharedInstance setTheme:buzzTheme];
}

@end
