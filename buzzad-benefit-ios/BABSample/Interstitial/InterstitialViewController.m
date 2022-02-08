#import "InterstitialViewController.h"

@import BuzzAdBenefit;

#import "AppConstant.h"
#import "CustomCtaView.h"
#import "UIButton+Custom.h"

static NSString * const kNavigationItemTitle = @"Interstitial";

// MARK: 5.1. 광고 할당 및 표시하기
@interface InterstitialViewController () <BZVBuzzAdInterstitialDelegate>

@property (nonatomic, strong, readonly) UIButton *loadAdButton;
@property (nonatomic, strong, readonly) BZVBuzzAdInterstitial *buzzAdInterstitial;

@end

@implementation InterstitialViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  [self setupView];
}

- (void)loadInterstitial:(id)sender {
  _buzzAdInterstitial = [BZVBuzzAdInterstitial interstitialWithBlock:^(BZVBuzzAdInterstitialBuilder * _Nonnull builder) {
    builder.unitId = INTERSTITIAL_UNIT_ID;
    builder.type = BZVBuzzAdInterstitialDialog;

    builder.theme = [BZVBuzzAdInterstitialTheme themeWithBlock:^(BZVBuzzAdInterstitialThemeBuilder * _Nonnull builder) {
      // MARK: 5.3. 텍스트 색상 변경하기
//      builder.textColor = UIColor.systemBlueColor; // 광고 타이틀과 설명 문구 색상
      // MARK: 5.3. 배경 색상 변경하기
//      builder.backgroundColor = UIColor.systemOrangeColor; // 지면 배경 색상
      // MARK: 7.1. Interstitial 지면의 CTA 버튼 디자인 변경하기
//      builder.rewardIcon = [UIImage imageNamed:@"ic_coin"];
//      builder.participatedIcon = [UIImage imageNamed:@"ic_check"];
//      builder.ctaTextColor = [BZVControlStateResource resourceWithBlock:^(BZVControlStateResourceBuilder * _Nonnull builder) {
//        [builder setValue:UIColor.redColor forState:BZVControlStateNormal];
//      }];
//      builder.ctaBackgroundColor = [BZVControlStateResource resourceWithBlock:^(BZVControlStateResourceBuilder * _Nonnull builder) {
//        [builder setValue:UIColor.blueColor forState:BZVControlStateNormal];
//      }];
    }];
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

#pragma mark - UI setup
- (void)setupView {
  self.navigationItem.title = kNavigationItemTitle;
  self.view.backgroundColor = UIColor.whiteColor;

  _loadAdButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_loadAdButton setTitle:@"Load Ad" forState:UIControlStateNormal];
  [_loadAdButton applyCustomStyle];
  [self.view addSubview:_loadAdButton];

  _loadAdButton.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_loadAdButton.leadingAnchor constraintEqualToAnchor:self.view.leadingAnchor constant:8],
    [_loadAdButton.trailingAnchor constraintEqualToAnchor:self.view.trailingAnchor constant:-8],
    [_loadAdButton.topAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.topAnchor],
    [_loadAdButton.heightAnchor constraintEqualToConstant:48],
  ]];

  [_loadAdButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(loadInterstitial:)]];
}

@end
