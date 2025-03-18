#import <UIKit/UIKit.h>
@import BuzzvilSDK;

@interface NativeViewController : UIViewController

@property (nonatomic, strong) BuzzNative *native;
@property (nonatomic, strong) BuzzNativeAdView *nativeAdView;
@property (nonatomic, strong) BuzzMediaView *mediaView;
@property (nonatomic, strong) UIImageView *iconImageView;
@property (nonatomic, strong) UILabel *titleLabel;
@property (nonatomic, strong) UILabel *descriptionLabel;
@property (nonatomic, strong) BuzzDefaultCtaView *ctaView;
@property (nonatomic, strong) BuzzNativeViewBinder *viewBinder;
@property (nonatomic, strong) UIActivityIndicatorView *activityIndicatorView;

@end

@implementation NativeViewController

- (instancetype)init {
  self = [super init];
  if (self) {
    _native = [[BuzzNative alloc] initWithUnitId:@"YOUR_NATIVE_UNIT_ID"];
    _nativeAdView = [[BuzzNativeAdView alloc] initWithFrame:CGRectZero];
    _mediaView = [[BuzzMediaView alloc] initWithFrame:CGRectZero];
    _iconImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
    _titleLabel = [[UILabel alloc] initWithFrame:CGRectZero];
    _descriptionLabel = [[UILabel alloc] initWithFrame:CGRectZero];
    _ctaView = [[BuzzDefaultCtaView alloc] initWithFrame:CGRectZero];
    
    _viewBinder = [BuzzNativeViewBinder viewBinderWithBlock:^(BuzzNativeViewBinderBuilder * _Nonnull builder) {
      builder.nativeAdView = _nativeAdView;
      builder.mediaView = _mediaView;
      builder.iconImageView = _iconImageView;
      builder.titleLabel = _titleLabel;
      builder.descriptionLabel = _descriptionLabel;
      builder.ctaView = _ctaView;
    }];
    
    _activityIndicatorView = [[UIActivityIndicatorView alloc] initWithFrame:CGRectZero];
  }
  return self;
}

- (void)viewDidLoad {
  [super viewDidLoad];
  
  [self setupView];
  [self setupLayout];
  [self nativeAdLoad];
}

- (void)setupView {
  self.view.backgroundColor = [UIColor systemBackgroundColor];
  [self.view addSubview:self.nativeAdView];
  [self.nativeAdView addSubview:self.mediaView];
  [self.nativeAdView addSubview:self.iconImageView];
  [self.nativeAdView addSubview:self.titleLabel];
  [self.nativeAdView addSubview:self.descriptionLabel];
  [self.nativeAdView addSubview:self.ctaView];
}

- (void)setupLayout {
  self.nativeAdView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [self.nativeAdView.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor constant:16],
    [self.nativeAdView.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.trailingAnchor constant:-16],
    [self.nativeAdView.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor constant:8]
  ]];
  
  self.mediaView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [self.mediaView.topAnchor constraintEqualToAnchor:self.nativeAdView.topAnchor],
    [self.mediaView.leadingAnchor constraintEqualToAnchor:self.nativeAdView.leadingAnchor],
    [self.mediaView.trailingAnchor constraintEqualToAnchor:self.nativeAdView.trailingAnchor],
    [self.mediaView.heightAnchor constraintEqualToAnchor:self.mediaView.widthAnchor multiplier:627.0/1200.0]
  ]];
  
  self.iconImageView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [self.iconImageView.topAnchor constraintEqualToAnchor:self.mediaView.bottomAnchor constant:8],
    [self.iconImageView.leadingAnchor constraintEqualToAnchor:self.mediaView.leadingAnchor constant:8],
    [self.iconImageView.heightAnchor constraintEqualToConstant:32],
    [self.iconImageView.widthAnchor constraintEqualToConstant:32]
  ]];
  
  self.titleLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [self.titleLabel.centerYAnchor constraintEqualToAnchor:self.iconImageView.centerYAnchor],
    [self.titleLabel.leadingAnchor constraintEqualToAnchor:self.iconImageView.trailingAnchor constant:8],
    [self.titleLabel.trailingAnchor constraintEqualToAnchor:self.nativeAdView.trailingAnchor constant:-8]
  ]];
  
  self.descriptionLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [self.descriptionLabel.topAnchor constraintEqualToAnchor:self.iconImageView.bottomAnchor constant:8],
    [self.descriptionLabel.leadingAnchor constraintEqualToAnchor:self.iconImageView.leadingAnchor],
    [self.descriptionLabel.trailingAnchor constraintEqualToAnchor:self.titleLabel.trailingAnchor]
  ]];
  
  self.ctaView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [self.ctaView.topAnchor constraintEqualToAnchor:self.descriptionLabel.bottomAnchor constant:8],
    [self.ctaView.trailingAnchor constraintEqualToAnchor:self.nativeAdView.trailingAnchor constant:-8],
    [self.ctaView.bottomAnchor constraintEqualToAnchor:self.nativeAdView.bottomAnchor constant:-8],
    [self.ctaView.heightAnchor constraintEqualToConstant:32]
  ]];
}

- (void)nativeAdLoad {
  __weak typeof(self) weakSelf = self;
  [self.native subscribeRefreshEventsOnRequest:^{
    // 광고 갱신 할당을 요청한 상태입니다.
  } onSuccess:^(BuzzNativeAd *nativeAd) {
    // 광고 갱신 할당에 성공하면 호출됩니다.
  } onFailure:^(NSError *error) {
    // 더 이상 갱신할 수 있는 광고가 없을 때 호출됩니다.
  }];
  
  [self.native loadOnSuccess:^(BuzzNativeAd *nativeAd) {
    // 초기 광고 할당에 성공하면 호출됩니다.
  } onFailure:^(NSError *error) {
    // 초기 광고 할당에 실패하면 호출됩니다.
  }];
  
  [self.native subscribeAdEventsOnImpressed:^(BuzzNativeAd * _Nonnull) {
    // Native 광고가 유저에게 노출되었을 때 호출됩니다.
  } onClicked:^(BuzzNativeAd * _Nonnull) {
    // 유저가 Native 광고를 클릭했을 때 호출됩니다.
  } onRewardRequested:^(BuzzNativeAd * _Nonnull) {
    // 리워드 적립 요청시에 호출됩니다.
  } onRewarded:^(BuzzNativeAd * _Nonnull, enum BuzzRewardResult) {
    // 리워드 적립 결과를 수신했을 때 호출됩니다.
  } onParticipated:^(BuzzNativeAd * _Nonnull) {
    // 광고 참여가 완료되었을 때 호출됩니다.
  }];
  
  [self.viewBinder bind:self.native];
}

@end
