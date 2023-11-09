#import "CustomFeedAdView.h"

@interface CustomFeedAdView () <BZVNativeAdEventDelegate>

@property (nonatomic, strong, readonly) BZVNativeAdView *nativeAdView;
@property (nonatomic, strong, readonly) BZVMediaView *mediaView;
@property (nonatomic, strong, readonly) UIImageView *iconImageView;
@property (nonatomic, strong, readonly) UILabel *titleLabel;
@property (nonatomic, strong, readonly) UILabel *descriptionLabel;
@property (nonatomic, strong, readonly) BZVDefaultCtaView *ctaView;
@property (nonatomic, strong, readonly) BZVNativeAdViewBinder *viewBinder;

@end

@implementation CustomFeedAdView

- (instancetype)initWithCoder:(NSCoder *)coder {
  if (self = [super initWithCoder:coder]) {
    [self setupView];
    [self setupLayout];
  }
  return self;
}

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    [self setupView];
    [self setupLayout];
  }
  return self;
}

- (void)setupView {
  _nativeAdView = [[BZVNativeAdView alloc] initWithFrame:CGRectZero];
  [self addSubview:_nativeAdView];

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
  
}

- (void)setupLayout {
  // LayoutConstraint 설정
  _nativeAdView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_nativeAdView.leadingAnchor constraintEqualToAnchor:self.leadingAnchor],
    [_nativeAdView.trailingAnchor constraintEqualToAnchor:self.trailingAnchor],
    [_nativeAdView.topAnchor constraintEqualToAnchor:self.safeAreaLayoutGuide.topAnchor constant:8],
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
}

- (void)renderAd:(BZVNativeAd *)ad {
  [_viewBinder bindWithNativeAd:ad];
  ad.delegate = self;
}

#pragma mark - BZVNativeAdEventDelegate
- (void)didImpressAd:(BZVNativeAd *)nativeAd {
}

- (void)didClickAd:(BZVNativeAd *)nativeAd {
}

- (void)didRequestRewardForAd:(BZVNativeAd *)nativeAd {
}

- (void)didRewardForAd:(BZVNativeAd *)nativeAd withResult:(BZVRewardResult)result {
}

- (void)didParticipateAd:(BZVNativeAd *)nativeAd {
}

@end
