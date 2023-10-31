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
  [[BuzzBenefit shared] initializeWith:config];
  
  // 다크모드 설정하기 TODO
  // ##ARTHUR
  //  [[BuzzBenefit shared] setUserInterfaceStyle:BZVUserInterfaceStyleSystem];
  //  [[BuzzBenefit shared] setUserInterfaceStyle:BZVUserInterfaceStyleLight];
  //  [[BuzzBenefit shared] setUserInterfaceStyle:BZVUserInterfaceStyleDark];
  
  return YES;
}

// ##ARTHUR
- (void)buzzBenefitLoginLogout {
  //  BuzzBenefitUser benefitUser = [[BuzzBenefitUser alloc] initWithUserId:]
  //  [BuzzBenefit loginWithBlock:^(BZVLoginRequestBuilder * _Nonnull builder) {
  //    builder.userId = @"USER_ID";
  //    builder.gender = BZVUserGenderMale; // 남성 사용자
  //    builder.birthYear = YYYY;
  //  } onSuccess:^{
  //  } onFailure:^(NSError * _Nonnull error) {
  //  }];
  
  // 로그인 상태를 확인하는 코드입니다.
  [[BuzzBenefit shared] isLoggedIn];
  
  // 로그아웃하는 코드입니다.
  [[BuzzBenefit shared] logout];
}


@end
