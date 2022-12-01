#import "Native2ViewController.h"

@import BuzzAdBenefit;
@import Toast;

#import "AppConstant.h"
#import "UIButton+Custom.h"

static NSString * const kNavigationItemTitle = @"Native2";

// MARK: 네이티브 2.0 기본 설정 - 광고 레이아웃 구성하기
@interface Native2ViewController () <BZVNativeAdViewVideoDelegate>

@property (nonatomic, strong, readonly) UIButton *startButton;
@property (nonatomic, strong, readonly) BZVNativeAd2View *nativeAd2View;
@property (nonatomic, strong, readonly) BZVMediaView *mediaView;
@property (nonatomic, strong, readonly) UIImageView *iconImageView;
@property (nonatomic, strong, readonly) UILabel *titleLabel;
@property (nonatomic, strong, readonly) UILabel *descriptionLabel;
@property (nonatomic, strong, readonly) BZVDefaultCtaView *ctaView;
@property (nonatomic, strong, readonly) BZVNativeAd2ViewBinder *viewBinder;
@property (nonatomic, strong, readonly) UIActivityIndicatorView *indicatorView;

@end

@implementation Native2ViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  [self setupView];
  [self setupLayout];
  [self setupAction];
}

- (void)setupView {
  self.navigationItem.title = kNavigationItemTitle;
  self.view.backgroundColor = UIColor.whiteColor;
  
  _startButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_startButton setTitle:@"Start" forState:UIControlStateNormal];
  [_startButton applyCustomStyle];
  [self.view addSubview:_startButton];
  
  _nativeAd2View = [[BZVNativeAd2View alloc] initWithFrame:CGRectZero];
  _nativeAd2View.layer.cornerRadius = 8;
  _nativeAd2View.clipsToBounds = YES;
  _nativeAd2View.videoDelegate = self;
  [self.view addSubview:_nativeAd2View];
  
  _mediaView = [[BZVMediaView alloc] initWithFrame:CGRectZero];
  _mediaView.layer.cornerRadius = 8;
  _mediaView.clipsToBounds = YES;
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
    builder.unitId = NATIVE_UNIT_ID;
    builder.nativeAd2View = self.nativeAd2View;
    builder.mediaView = self.mediaView;
    builder.iconImageView = self.iconImageView;
    builder.titleLabel = self.titleLabel;
    builder.descriptionLabel = self.descriptionLabel;
    builder.ctaView = self.ctaView;
  }];
  
  _indicatorView = [[UIActivityIndicatorView alloc] initWithFrame:CGRectZero];
  _indicatorView.activityIndicatorViewStyle = UIActivityIndicatorViewStyleGray;
  _indicatorView.hidesWhenStopped = YES;
  [self.view addSubview:_indicatorView];
}

- (void)setupLayout {
  // AutoLayout Constraints 설정
  _startButton.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_startButton.topAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.topAnchor constant:8],
    [_startButton.leadingAnchor constraintEqualToAnchor:self.view.leadingAnchor constant:8],
    [_startButton.trailingAnchor constraintEqualToAnchor:self.view.trailingAnchor constant:-8],
    [_startButton.heightAnchor constraintEqualToConstant:48],
  ]];
  
  _nativeAd2View.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_nativeAd2View.topAnchor constraintEqualToAnchor:_startButton.bottomAnchor constant:8],
    [_nativeAd2View.leadingAnchor constraintEqualToAnchor:self.view.leadingAnchor constant:8],
    [_nativeAd2View.trailingAnchor constraintEqualToAnchor:self.view.trailingAnchor constant:-8],
    [_nativeAd2View.bottomAnchor constraintLessThanOrEqualToAnchor:self.view.layoutMarginsGuide.bottomAnchor constant:-8],
  ]];
  
  _mediaView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_mediaView.topAnchor constraintEqualToAnchor:_nativeAd2View.topAnchor],
    [_mediaView.leadingAnchor constraintEqualToAnchor:_nativeAd2View.leadingAnchor],
    [_mediaView.trailingAnchor constraintEqualToAnchor:_nativeAd2View.trailingAnchor],
    [_mediaView.heightAnchor constraintEqualToAnchor:_mediaView.widthAnchor multiplier:627.0/1200.0],
  ]];
  
  _iconImageView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_iconImageView.topAnchor constraintEqualToAnchor:_mediaView.bottomAnchor constant:8],
    [_iconImageView.leadingAnchor constraintEqualToAnchor:_mediaView.leadingAnchor constant:8],
    [_iconImageView.heightAnchor constraintEqualToConstant:32],
    [_iconImageView.widthAnchor constraintEqualToConstant:32],
  ]];
  
  _titleLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_titleLabel.centerYAnchor constraintEqualToAnchor:_iconImageView.centerYAnchor],
    [_titleLabel.leadingAnchor constraintEqualToAnchor:_iconImageView.trailingAnchor constant:8],
    [_titleLabel.trailingAnchor constraintEqualToAnchor:_nativeAd2View.trailingAnchor constant:-8],
  ]];
  
  _descriptionLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_descriptionLabel.topAnchor constraintEqualToAnchor:_iconImageView.bottomAnchor constant:8],
    [_descriptionLabel.leadingAnchor constraintEqualToAnchor:_iconImageView.leadingAnchor],
    [_descriptionLabel.trailingAnchor constraintEqualToAnchor:_titleLabel.trailingAnchor],
  ]];
  
  _ctaView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_ctaView.topAnchor constraintEqualToAnchor:_descriptionLabel.bottomAnchor constant:8],
    [_ctaView.trailingAnchor constraintEqualToAnchor:_nativeAd2View.trailingAnchor constant:-8],
    [_ctaView.bottomAnchor constraintEqualToAnchor:_nativeAd2View.bottomAnchor constant:-8],
    [_ctaView.heightAnchor constraintEqualToConstant:32],
  ]];
  
  _indicatorView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_indicatorView.topAnchor constraintEqualToAnchor:_nativeAd2View.topAnchor],
    [_indicatorView.leadingAnchor constraintEqualToAnchor:_nativeAd2View.leadingAnchor],
    [_indicatorView.trailingAnchor constraintEqualToAnchor:_nativeAd2View.trailingAnchor],
    [_indicatorView.bottomAnchor constraintEqualToAnchor:_nativeAd2View.bottomAnchor],
  ]];
}

- (void)setupAction {
  [_startButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(start)]];
}

// MARK: 네이티브 2.0 기본 설정 - 광고 보여주기
- (void)start {
  [self subscribeEvents];
  [self subscribeAdEvents];
  
  // 광고 할당 및 표시를 자동으로 수행합니다.
  [_viewBinder bind];
}

// MARK: 네이티브 2.0 기본 설정 - 광고 보여주기
- (void)subscribeEvents {
  // Warning: 반드시 bind 함수를 호출하기 전에 호출해주세요.
  // Warning: retain cycle 방지를 위해 weak self를 사용해주세요.
  __weak typeof(self) weakSelf = self;
  [_viewBinder subscribeEventsOnRequest:^{
    // 광고 할당을 요청하면 호출됩니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.indicatorView startAnimating];
    }
  } onNext:^(BZVNativeAd2 * _Nonnull nativeAd) {
    // 광고 할당에 성공하면 호출됩니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.indicatorView stopAnimating];
    }
  } onError:^(NSError * _Nonnull error) {
    // 최초 광고 할당에 실패하면 호출됩니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.indicatorView stopAnimating];
      [strongSelf.view.window makeToast:[NSString stringWithFormat:@"Error: %@", error.localizedDescription]];
    }
  } onCompleted:^{
    // 더 이상 갱신할 수 있는 광고가 없을 때 호출됩니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.indicatorView stopAnimating];
    }
  }];
}

// MARK: 네이티브 2.0 고급 설정 - 광고 이벤트 리스너 등록하기
- (void)subscribeAdEvents {
  // Warning: 반드시 bind 함수를 호출하기 전에 호출해주세요.
  // Warning: retain cycle 방지를 위해 closure 내에서 반드시 weak self를 사용해주세요.
  __weak typeof(self) weakSelf = self;
  [_viewBinder subscribeAdEventsOnImpressed:^(BZVNativeAd2 * _Nonnull nativeAd) {
    // Native 광고가 유저에게 노출되었을 때 호출됩니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.view.window makeToast:[NSString stringWithFormat:@"Ad impressed: %@", nativeAd.title]];
    }
  } onClicked:^(BZVNativeAd2 * _Nonnull nativeAd) {
    // 유저가 Native 광고를 클릭했을 때 호출됩니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.view.window makeToast:[NSString stringWithFormat:@"Ad clicked: %@", nativeAd.title]];
    }
  } onRewardRequested:^(BZVNativeAd2 * _Nonnull nativeAd) {
    // 리워드 적립 요청시에 호출됩니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.view.window makeToast:[NSString stringWithFormat:@"Ad requested reward: %@", nativeAd.title]];
    }
  } onRewarded:^(BZVNativeAd2 * _Nonnull nativeAd, BZVRewardResult result) {
    // 리워드 적립의 결과를 수신했을 때 호출됩니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.view.window makeToast:[NSString stringWithFormat:@"Ad received reward result: %@", nativeAd.title]];
    }
  } onParticipated:^(BZVNativeAd2 * _Nonnull nativeAd) {
    // 광고 참여가 완료되었을 때 호출됩니다.
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.view.window makeToast:[NSString stringWithFormat:@"Ad participated: %@", nativeAd.title]];
    }
  }];
}

// MARK: 네이티브 2.0 고급 설정 - 동영상 광고 리스너 등록하기
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
