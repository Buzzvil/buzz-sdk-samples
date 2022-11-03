#import "NativeViewController.h"

@import BuzzAdBenefit;

#import "AppConstant.h"
#import "UIButton+Custom.h"

static NSString * const kNavigationItemTitle = @"Native";

// MARK: 네이티브 기본 설정 - 광고 레이아웃 구성하기
@interface NativeViewController () <BZVNativeAdEventDelegate, BZVNativeAdViewVideoDelegate>

@property (nonatomic, strong, readonly) UIButton *loadAdButton;
@property (nonatomic, strong, readonly) BZVNativeAdView *nativeAdView;
@property (nonatomic, strong, readonly) BZVMediaView *mediaView;
@property (nonatomic, strong, readonly) UIImageView *iconImageView;
@property (nonatomic, strong, readonly) UILabel *titleLabel;
@property (nonatomic, strong, readonly) UILabel *descriptionLabel;
@property (nonatomic, strong, readonly) BZVDefaultCtaView *ctaView;
@property (nonatomic, strong, readonly) BZVNativeAdViewBinder *viewBinder;

@end

@implementation NativeViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  [self setupView];
}

// MARK: 네이티브 기본 설정 - 광고 할당 및 표시하기
- (void)loadAd:(id)sender {
  BZVNativeAdRequest *adRequest = [[BZVNativeAdRequest alloc] init];
  BZVBuzzAdNative *buzzAdNative = [BZVBuzzAdNative nativeWithUnitId:NATIVE_UNIT_ID];
  [buzzAdNative loadAdWithAdRequest:adRequest onSuccess:^(BZVNativeAd * _Nonnull nativeAd) {
    // 할당된 광고가 있으면 호출됩니다.
    // 광고 데이터를 바인딩합니다.
    [self renderAd:nativeAd];
  } onFailure:^(NSError * _Nonnull error) {
    // 할당된 광고가 없으면 호출됩니다.
    NSLog(@"Failed to load a native ad");
  }];
}

- (void)renderAd:(BZVNativeAd *)ad {
  [_viewBinder bindWithNativeAd:ad];

  // Optional: Native 광고 이벤트 처리를 위한 delegate을 등록하고 각 이벤트에 따라 필요한 기능을 구현합니다.
  // 주의: 반드시 bindWithNativeAd 함수를 호출한 이후에 리스너 등록을 해야합니다.
  [ad addNativeAdEventDelegate:self];
}

// MARK: 네이티브 고급 설정 - 한 번에 여러 개의 광고 로드하기
- (void)loadAds:(id)sender {
  BZVNativeAdsRequest *adsRequest = [BZVNativeAdsRequest requestWithBlock:^(BZVNativeAdsRequestBuilder * _Nonnull builder) {
    builder.adCount = @(10); // 한 번에 로드할 광고 개수를 설정합니다.
  }];

  BZVBuzzAdNative *buzzAdNative = [BZVBuzzAdNative nativeWithUnitId:NATIVE_UNIT_ID];
  [buzzAdNative loadAdsWithAdsRequest:adsRequest onSuccess:^(NSArray<BZVNativeAd *> * _Nonnull nativeAds) {
    // 광고 할당이 성공하면 호출됩니다.
    [self renderAd:[nativeAds firstObject]];
  } onFailure:^(NSError * _Nonnull error) {
    // 할당된 광고가 없으면 호출됩니다.
    NSLog(@"Failed to load native ads");
  }];
}

// MARK: 네이티브 고급 설정 - 광고 이벤트 리스너 등록하기
#pragma mark - BZVNativeAdEventDelegate
- (void)didImpressAd:(BZVNativeAd *)nativeAd {
// Native 광고가 유저에게 노출되었을 때 호출됩니다.
}

- (void)didClickAd:(BZVNativeAd *)nativeAd {
// 유저가 Native 광고를 클릭했을 때 호출됩니다.
}

- (void)didRequestRewardForAd:(BZVNativeAd *)nativeAd {
// 리워드 적립 요청시에 호출됩니다.
}

- (void)didRewardForAd:(BZVNativeAd *)nativeAd withResult:(BZVRewardResult)result {
// 리워드 적립의 결과를 수신했을 때 호출됩니다.
}

- (void)didParticipateAd:(BZVNativeAd *)nativeAd {
// 광고 참여가 완료되었을 때 호출됩니다.
}

// MARK: 네이티브 고급 설정 - 비디오 광고 리스너 등록하기
#pragma mark - BZVNativeAdViewVideoDelegate
- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView willStartPlayingVideoAd:(BZVNativeAd *)nativeAd {
}

- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView didResumeVideoAd:(BZVNativeAd *)nativeAd {
}

- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView willReplayVideoAd:(BZVNativeAd *)nativeAd {
}

- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView didPauseVideoAd:(BZVNativeAd *)nativeAd {
}

- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView didFinishPlayingVideoAd:(BZVNativeAd *)nativeAd {
}

#pragma mark - UI setup
- (void)setupView {
  self.navigationItem.title = kNavigationItemTitle;
  self.view.backgroundColor = UIColor.whiteColor;

  _loadAdButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_loadAdButton setTitle:@"Load Ad" forState:UIControlStateNormal];
  [_loadAdButton applyCustomStyle];
  [self.view addSubview:_loadAdButton];

  _nativeAdView = [[BZVNativeAdView alloc] initWithFrame:CGRectZero];
  _nativeAdView.videoDelegate = self;
  [self.view addSubview:_nativeAdView];

  _mediaView = [[BZVMediaView alloc] initWithFrame:CGRectZero];
  [_nativeAdView addSubview:_mediaView];

  _iconImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
  [_nativeAdView addSubview:_iconImageView];

  _titleLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [_nativeAdView addSubview:_titleLabel];

  _descriptionLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [_nativeAdView addSubview:_descriptionLabel];

  _ctaView = [[BZVDefaultCtaView alloc] initWithFrame:CGRectZero];
  [_nativeAdView addSubview:_ctaView];
  
  _viewBinder = [BZVNativeAdViewBinder viewBinderWithBlock:^(BZVNativeAdViewBinderBuilder * _Nonnull builder) {
    builder.nativeAdView = self.nativeAdView;
    builder.mediaView = self.mediaView;
    builder.iconImageView = self.iconImageView;
    builder.titleLabel = self.titleLabel;
    builder.descriptionLabel = self.descriptionLabel;
    builder.ctaView = self.ctaView;
  }];

  // LayoutConstraint 설정
  _loadAdButton.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_loadAdButton.leadingAnchor constraintEqualToAnchor:self.view.leadingAnchor constant:8],
    [_loadAdButton.trailingAnchor constraintEqualToAnchor:self.view.trailingAnchor constant:-8],
    [_loadAdButton.topAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.topAnchor],
    [_loadAdButton.heightAnchor constraintEqualToConstant:48],
  ]];

  _nativeAdView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_nativeAdView.leadingAnchor constraintEqualToAnchor:self.view.leadingAnchor],
    [_nativeAdView.trailingAnchor constraintEqualToAnchor:self.view.trailingAnchor],
    [_nativeAdView.topAnchor constraintEqualToAnchor:_loadAdButton.bottomAnchor constant:8],
    [_nativeAdView.heightAnchor constraintGreaterThanOrEqualToConstant:0],
  ]];

  _mediaView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_mediaView.leadingAnchor constraintEqualToAnchor:_nativeAdView.leadingAnchor],
    [_mediaView.trailingAnchor constraintEqualToAnchor:_nativeAdView.trailingAnchor],
    [_mediaView.topAnchor constraintEqualToAnchor:_nativeAdView.topAnchor],
    [_mediaView.heightAnchor constraintEqualToAnchor:_mediaView.widthAnchor multiplier:627.0 / 1200.0],
  ]];

  _iconImageView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_iconImageView.widthAnchor constraintEqualToConstant:32],
    [_iconImageView.heightAnchor constraintEqualToConstant:32],
    [_iconImageView.leadingAnchor constraintEqualToAnchor:_mediaView.leadingAnchor constant:8],
    [_iconImageView.topAnchor constraintEqualToAnchor:_mediaView.bottomAnchor constant:8],
  ]];

  _titleLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_titleLabel.leadingAnchor constraintEqualToAnchor:_iconImageView.trailingAnchor constant:8],
    [_titleLabel.trailingAnchor constraintEqualToAnchor:_nativeAdView.trailingAnchor constant:-8],
    [_titleLabel.centerYAnchor constraintEqualToAnchor:_iconImageView.centerYAnchor],
  ]];

  _descriptionLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_descriptionLabel.leadingAnchor constraintEqualToAnchor:_iconImageView.leadingAnchor],
    [_descriptionLabel.trailingAnchor constraintEqualToAnchor:_titleLabel.trailingAnchor],
    [_descriptionLabel.topAnchor constraintEqualToAnchor:_iconImageView.bottomAnchor constant:8],
  ]];

  _ctaView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_ctaView.trailingAnchor constraintEqualToAnchor:_nativeAdView.trailingAnchor constant:-8],
    [_ctaView.topAnchor constraintEqualToAnchor:_descriptionLabel.bottomAnchor constant:8],
    [_ctaView.bottomAnchor constraintEqualToAnchor:_nativeAdView.bottomAnchor constant:-8],
  ]];

  [_loadAdButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(loadAd:)]];
}

@end
