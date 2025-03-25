@import BuzzvilSDK;
#import "InterstitialViewController.h"

@interface InterstitialViewController () <BuzzInterstitialDelegate>

@property (nonatomic, strong, readonly) BuzzInterstitial *buzzAdInterstitial;

@end

@implementation InterstitialViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  self.view.backgroundColor = [UIColor systemBackgroundColor];
  
  _buzzAdInterstitial = [BuzzInterstitial interstitialWith:^(BuzzInterstitialBuilder * _Nonnull builder) {
    builder.unitId = @"YOUR_INTERSTITIAL_UNIT_ID";
    builder.type = BuzzInterstitialTypeDialog;
  }];
  _buzzAdInterstitial.delegate = self;
  [_buzzAdInterstitial load];
}

#pragma mark - BuzzInterstitialDelegate
- (void)buzzInterstitialDidLoadAd:(BuzzInterstitial *)interstitial {
  // 할당된 광고가 있으면 호출됩니다.
  // Interstitial 광고를 화면에 표시합니다.
  [interstitial presentOnViewController:self];
}

- (void)buzzInterstitialDidFailToLoadAd:(BuzzInterstitial *)interstitial withError:(NSError *)withError {
  // 할당된 광고가 없으면 호출됩니다.
}

- (void)buzzInterstitialDidDismiss:(UIViewController *)viewController {
  // Interstitial 지면이 종료되면 호출됩니다.
  // 필요에 따라 추가 기능을 구현하세요.
}

@end
