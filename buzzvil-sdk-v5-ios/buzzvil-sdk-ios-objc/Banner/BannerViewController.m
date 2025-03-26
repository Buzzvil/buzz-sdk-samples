@import BuzzvilSDK;
#import "BannerViewController.h"

@interface BannerViewController () <BuzzAdBenefitBannerViewDelegate>

@property (nonatomic, strong, readonly) BuzzAdBenefitBannerView *bannerView;

@end

@implementation BannerViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  [self setupView];
}

- (void)setupView {
  BuzzAdBenefitBannerConfig * config = [BuzzAdBenefitBannerConfig configWithBlock:^(BuzzAdBenefitBannerConfigBuilder * _Nonnull builder) {
    builder.placementID = @"YOUR_PLCEMENT_ID";
    builder.size = BuzzAdBenefitBannerSizeW320h50;
  }];
  _bannerView = [[BuzzAdBenefitBannerView alloc] initWithFrame:CGRectZero];
  [_bannerView setConfigWithRootViewController:self config:config];
  
  [self.view addSubview:_bannerView];
}

- (void)viewWillAppear:(BOOL)animated {
  [super viewWillAppear:animated];
  
  [_bannerView requestAd];
}

- (void)viewWillDisappear:(BOOL)animated{
  [super viewWillDisappear:animated];
  
  [_bannerView removeAd];
}

#pragma mark - BuzzAdBenefitBannerViewDelegate

- (void)bannerView:(BuzzAdBenefitBannerView * _Nonnull)bannerView didClickApid:(NSString * _Nonnull)didClickApid { 
  // Banner에 광고가 할당 되었을 때 호출 됩니다.
}

- (void)bannerView:(BuzzAdBenefitBannerView * _Nonnull)bannerView didFailApid:(NSString * _Nonnull)didFailApid error:(NSError * _Nonnull)error { 
  // Banner에 광고 할당이 실패했을 때 호출 됩니다.
}

- (void)bannerView:(BuzzAdBenefitBannerView * _Nonnull)bannerView didLoadApid:(NSString * _Nonnull)didLoadApid { 
  // Banner가 클릭되었을 때 호출 됩니다.
}

- (void)bannerView:(BuzzAdBenefitBannerView * _Nonnull)bannerView didRemoveApid:(NSString * _Nonnull)didRemoveApid { 
  // Banner가 제거되었을 떄 호출 됩니다.
}

@end
