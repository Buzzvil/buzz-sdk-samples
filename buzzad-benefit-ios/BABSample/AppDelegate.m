#import "AppDelegate.h"

//#import "Feed/CustomFeedHeaderViewHolder.h"
//#import "Feed/CustomFeedAdViewHolder.h"
//#import "Feed/CustomFeedCpsAdViewHolder.h"
//#import "Feed/CustomFeedErrorViewHolder.h"
#import "Util/AppConstant.h"
#import "ViewController.h"

@import BuzzAdBenefit;

@interface AppDelegate ()

@end

@implementation AppDelegate {
  UIWindow *_window;
}

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
  // Override point for customization after application launch.
  _window = [[UIWindow alloc] initWithFrame:UIScreen.mainScreen.bounds];
  UINavigationController *navigationVC = [[UINavigationController alloc] initWithRootViewController:[[ViewController alloc] init]];
  _window.rootViewController = navigationVC;
  [_window makeKeyAndVisible];

  // MARK: 2. BuzzAd SDK 초기화하기
  BZVConfig *config = [BZVConfig configWithBlock:^(BZVConfigBuilder * _Nonnull builder) {
    builder.appId = APP_ID;

    // MARK: 3.1. Feed 지면 초기화하기
    builder.defaultFeedConfig = [BZVFeedConfig configWithBlock:^(BZVFeedConfigBuilder * _Nonnull builder) {
      builder.unitId = FEED_UNIT_ID;

      // MARK: 3.2. 헤더 영역 자체 구현하기
//      builder.headerViewHolderClass = [CustomFeedHeaderViewHolder class];
      // MARK: 3.2. 일반 광고 디자인 자체 구현하기
//      builder.adViewHolderClass = [CustomFeedAdViewHolder class];
      // MARK: 3.2. 쇼핑 적립 광고 디자인 자체 구현하기
//      builder.cpsAdViewHolderClass = [CustomFeedCpsAdViewHolder class];
      // MARK: 3.2. 광고 미할당 안내 디자인 자체 구현하기
//      builder.errorViewHolderClass = [CustomFeedErrorViewHolder class];
      // MARK: 3.2. Feed 진입 시 앱 추적 권한 허용 다이얼로그 노출
//      builder.shouldShowAppTrackingTransparencyDialog = YES;
      // MARK: 3.2. 헤더 영역에 앱 추적 허용 권한 설명 배너 추가하기
//      builder.shouldShowAppTrackingTransparencyGuideBanner = YES;
    }];
  }];
  [BuzzAdBenefit initializeWithConfig:config];

  BZVBuzzAdFeedTheme *buzzAdFeedTheme = [BZVBuzzAdFeedTheme themeWithBlock:^(BZVBuzzAdFeedThemeBuilder * _Nonnull builder) {
    // MARK: 3.3. 탭 디자인 변경하기
//    builder.tabBackgroundColor = UIColor.orangeColor;
//    builder.tabTextColor = [BZVControlStateResource resourceWithBlock:^(BZVControlStateResourceBuilder * _Nonnull builder) {
//      [builder setValue:UIColor.blueColor forState:BZVControlStateNormal];
//      [builder setValue:UIColor.redColor forState:BZVControlStateHighlight];
//    }];
//    builder.tabIndicatorColor = UIColor.grayColor;

    // MARK: 3.3. 필터 디자인 변경하기
//    builder.filterBackgroundColor = [BZVControlStateResource resourceWithBlock:^(BZVControlStateResourceBuilder * _Nonnull builder) {
//      [builder setValue:UIColor.blueColor forState:BZVControlStateNormal];
//      [builder setValue:UIColor.redColor forState:BZVControlStateHighlight];
//    }];
//    builder.filterTextColor = [BZVControlStateResource resourceWithBlock:^(BZVControlStateResourceBuilder * _Nonnull builder) {
//      [builder setValue:UIColor.redColor forState:BZVControlStateNormal];
//      [builder setValue:UIColor.blueColor forState:BZVControlStateHighlight];
//    }];

    // MARK: 3.3. 지면 구분선 디자인 변경하기
//    builder.separatorColor = UIColor.blackColor;
//    builder.separatorHeight = 10;
//    builder.separatorHorizontalMargin = 20;

    // MARK: 7.1 Feed 지면의 CTA 버튼 디자인 변경하기
//    builder.rewardIcon = [UIImage imageNamed:@"ic_coin"];
//    builder.participatedIcon = [UIImage imageNamed:@"ic_check"];
//    builder.ctaTextColor = [BZVControlStateResource resourceWithBlock:^(BZVControlStateResourceBuilder * _Nonnull builder) {
//      [builder setValue:UIColor.redColor forState:BZVControlStateNormal];
//    }];
//    builder.ctaBackgroundColor = [BZVControlStateResource resourceWithBlock:^(BZVControlStateResourceBuilder * _Nonnull builder) {
//      [builder setValue:UIColor.blueColor forState:BZVControlStateNormal];
//    }];
  }];

  [BZVBuzzAdFeed setDefaultTheme:buzzAdFeedTheme];
  return YES;
}


@end
