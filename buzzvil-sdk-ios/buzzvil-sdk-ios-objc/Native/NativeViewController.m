@import BuzzvilSDK;
#import "NativeViewController.h"

@interface NativeViewController ()  <BZVNativeAdViewVideoDelegate>

@property (nonatomic, strong, readonly) BZVNativeAd2View *nativeAd2View;
@property (nonatomic, strong, readonly) BZVMediaView *mediaView;
@property (nonatomic, strong, readonly) UIImageView *iconImageView;
@property (nonatomic, strong, readonly) UILabel *titleLabel;
@property (nonatomic, strong, readonly) UILabel *descriptionLabel;
@property (nonatomic, strong, readonly) BZVDefaultCtaView *ctaView;
@property (nonatomic, strong, readonly) BZVNativeAd2ViewBinder *viewBinder;

@property (nonatomic, strong, readonly) UIActivityIndicatorView *activityIndicatorView;

@end

@implementation NativeViewController
- (void)viewDidLoad {
  
  [super viewDidLoad];
  [self setupView];
  [self setupLayout];
  [self nativeAdLoad];
}

- (void)setupView {
  _nativeAd2View = [[BZVNativeAd2View alloc] initWithFrame:CGRectZero];
  [self.view addSubview:_nativeAd2View];
  
  _mediaView = [[BZVMediaView alloc] initWithFrame:CGRectZero];
  [_nativeAd2View addSubview:_mediaView];
  
  _iconImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
  [_nativeAd2View addSubview:_iconImageView];
  
  _titleLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [_nativeAd2View addSubview:_titleLabel];
  
  _descriptionLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [_nativeAd2View addSubview:_descriptionLabel];
  
  _ctaView = [[BZVDefaultCtaView alloc] initWithFrame:CGRectZero];
  [_nativeAd2View addSubview:_ctaView];
  
  _viewBinder = [BZVNativeAd2ViewBinder viewBinderWithBlock:^(BZVNativeAd2ViewBinderBuilder * _Nonnull builder) {
    builder.unitId = @"YOUR_NATIVE_UNIT_ID";
    builder.nativeAd2View = self.nativeAd2View;
    builder.mediaView = self.mediaView;
    builder.iconImageView = self.iconImageView;
    builder.titleLabel = self.titleLabel;
    builder.descriptionLabel = self.descriptionLabel;
    builder.ctaView = self.ctaView;
  }];
}

- (void)setupLayout {
  _nativeAd2View.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_nativeAd2View.leadingAnchor constraintEqualToAnchor:self.view.leadingAnchor],
    [_nativeAd2View.trailingAnchor constraintEqualToAnchor:self.view.trailingAnchor],
    [_nativeAd2View.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor constant:8],
    [_nativeAd2View.heightAnchor constraintGreaterThanOrEqualToConstant:0],
  ]];

  _mediaView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_mediaView.leadingAnchor constraintEqualToAnchor:_nativeAd2View.leadingAnchor],
    [_mediaView.trailingAnchor constraintEqualToAnchor:_nativeAd2View.trailingAnchor],
    [_mediaView.topAnchor constraintEqualToAnchor:_nativeAd2View.topAnchor],
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
    [_titleLabel.trailingAnchor constraintEqualToAnchor:_nativeAd2View.trailingAnchor constant:-8],
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
    [_ctaView.trailingAnchor constraintEqualToAnchor:_nativeAd2View.trailingAnchor constant:-8],
    [_ctaView.topAnchor constraintEqualToAnchor:_descriptionLabel.bottomAnchor constant:8],
    [_ctaView.bottomAnchor constraintEqualToAnchor:_nativeAd2View.bottomAnchor constant:-8],
  ]];
}

- (void)nativeAdLoad {
  // 광고 보여주기 & 광고 이벤트 리스너 등록하기
  /// Warning: retain cycle 방지를 위해 weak self를 사용해주세요.
  __weak typeof(self) weakSelf = self;
  [_viewBinder subscribeEventsOnRequest:^{
    // 광고 할당을 요청하면 호출됩니다.
    // 이후에는 onNext, onCompleted, onError 중 하나가 호출됩니다.
    // 광고 자동 갱신을 시도할 때마다 반복적으로 호출됩니다.
    // 로딩 화면 등을 구현할 수 있습니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.activityIndicatorView startAnimating];
    }
  } onNext:^(BZVNativeAd2 * _Nonnull nativeAd2) {
    // 광고 할당에 성공하면 호출됩니다.
    // 이후에 광고 갱신 시 onRequest가 다시 호출됩니다.
    // 광고 자동 갱신을 성공할 때마다 반복적으로 호출됩니다.
    // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.activityIndicatorView stopAnimating];
    }
  } onError:^(NSError * _Nonnull error) {
    // 최초 광고 할당에 실패하면 호출됩니다.
    // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.activityIndicatorView stopAnimating];
    }
    // NativeAd2View를 숨기거나, Error UI로 대체할 수 있습니다.
    NSLog(@"Failed to load ad by %@.", error.localizedDescription);
  } onCompleted:^{
    // 더 이상 갱신할 수 있는 광고가 없을 때 호출됩니다.
    // 로딩 화면 등을 구현한 경우, 여기에서 로딩을 종료합니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.activityIndicatorView stopAnimating];
    }
  }];
  
  // 광고 할당 및 표시를 자동으로 수행합니다.
  [_viewBinder bind];
  
  // 동영상 광고 리스너 등록하기
  _nativeAd2View.videoDelegate = self;
}

#pragma mark - BZVNativeAdViewVideoDelegate
- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView willStartPlayingVideoAd:(BZVNativeAd *)nativeAd {
  // 비디오 광고의 비디오가 시작하기 직전에 호출됩니다.
}

- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView didResumeVideoAd:(BZVNativeAd *)nativeAd {
  // 비디오 광고의 비디오가 재생되면 호출됩니다.
}

- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView willReplayVideoAd:(BZVNativeAd *)nativeAd {
  // 비디오 광고의 비디오가 리플레이되면 호출됩니다.
}

- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView didPauseVideoAd:(BZVNativeAd *)nativeAd {
  // 비디오 광고의 비디오가 일시정지되면 호출됩니다.
}

- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView didFinishPlayingVideoAd:(BZVNativeAd *)nativeAd {
  // 비디오 광고의 비디오가 종료되면 호출됩니다.
}

@end
