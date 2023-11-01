@import BuzzAdBenefitSDK;
#import "InterstitialViewController.h"

@interface InterstitialViewController () <BZVBuzzAdInterstitialDelegate>

@property (nonatomic, strong, readonly) BZVBuzzAdInterstitial *buzzAdInterstitial;

@end

@implementation InterstitialViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  _buzzAdInterstitial = [BZVBuzzAdInterstitial interstitialWithBlock:^(BZVBuzzAdInterstitialBuilder * _Nonnull builder) {
    builder.unitId = @"YOUR_INTERSTITIAL_UNIT_ID";
    builder.type = BZVBuzzAdInterstitialDialog;
  }];
  _buzzAdInterstitial.delegate = self;
  [_buzzAdInterstitial load];
}

#pragma mark - BZVBuzzAdInterstitialDelegate
- (void)BZVBuzzAdInterstitialDidLoadAd:(BZVBuzzAdInterstitial *)interstitial {
  // 할당된 광고가 있으면 호출됩니다.
  // Interstitial 광고를 화면에 표시합니다.
  [interstitial presentOnViewController:self];
}

- (void)BZVBuzzAdInterstitialDidFailToLoadAd:(BZVBuzzAdInterstitial *)interstitial withError:(NSError *)error {
  // 할당된 광고가 없으면 호출됩니다.
}

- (void)BZVBuzzAdInterstitialDidDismiss:(UIViewController *)viewController {
  // Interstitial 지면이 종료되면 호출됩니다.
  // 필요에 따라 추가 기능을 구현하세요.
}

@end
