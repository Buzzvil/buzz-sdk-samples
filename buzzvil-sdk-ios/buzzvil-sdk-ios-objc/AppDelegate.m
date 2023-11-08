#import "AppDelegate.h"
@import BuzzvilSDK;

@interface AppDelegate ()

@end

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
  // 베네핏 허브 초기화 하기
  BZVBenefitHubConfig *benefitHubConfig = [BZVBenefitHubConfig configWithBlock:^(BZVBenefitHubConfigBuilder * _Nonnull builder) {
    builder.unitID = @"YOUR_FEED_UNIT_ID";
  }];
  
  // Buzzvil SDK 초기화하기
  BuzzBenefitConfig *config = [BuzzBenefitConfig configWithBlock:^(BuzzBenefitConfigBuilder * _Nonnull builder) {
    builder.appID = @"YOUR_APP_ID";
    builder.benefitHubConfig = benefitHubConfig;
  }];
  [[BuzzBenefit sharedInstance] initializeWithConfig:config];
  
  // 다크모드 설정하기 TODO
  [[BuzzBenefit sharedInstance] setUserInterfaceStyle:BuzzBenefitUserInterfaceStyleSystem];
  [[BuzzBenefit sharedInstance] setUserInterfaceStyle:BuzzBenefitUserInterfaceStyleLight];
  [[BuzzBenefit sharedInstance] setUserInterfaceStyle:BuzzBenefitUserInterfaceStyleDark];
  
  return YES;
}

- (void)buzzBenefitLoginLogout {
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
  
  // 로그아웃하는 코드입니다.
  [[BuzzBenefit sharedInstance] logout];
}

@end
