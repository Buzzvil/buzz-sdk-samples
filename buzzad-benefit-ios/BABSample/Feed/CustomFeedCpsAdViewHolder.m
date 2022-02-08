#import "CustomFeedCpsAdViewHolder.h"

#import "CustomCtaView.h"

// MARK: 3.2. 쇼핑 적립 광고 디자인 자체 구현하기
@interface CustomFeedCpsAdViewHolder ()

@property (nonatomic, strong, readonly) BZVNativeAdView *nativeAdView;
@property (nonatomic, strong, readonly) BZVMediaView *mediaView;
@property (nonatomic, strong, readonly) UIImageView *iconImageView;
@property (nonatomic, strong, readonly) UILabel *titleLabel;
@property (nonatomic, strong, readonly) UILabel *descriptionLabel;
@property (nonatomic, strong, readonly) CustomCtaView *ctaView;
@property (nonatomic, strong, readonly) UILabel *priceLabel;
@property (nonatomic, strong, readonly) UILabel *originalPriceLabel;
@property (nonatomic, strong, readonly) UILabel *discountRateLabel;

@end

@implementation CustomFeedCpsAdViewHolder

- (instancetype)initWithCoder:(NSCoder *)coder {
  if (self = [super initWithCoder:coder]) {
    [self setupView];
  }
  return self;
}

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    [self setupView];
  }
  return self;
}

- (void)setupView {
  // 광고 레이아웃 컴포넌트를 생성합니다.
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

  _ctaView = [[CustomCtaView alloc] initWithFrame:CGRectZero];
  [_nativeAdView addSubview:_ctaView];

  _priceLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [_nativeAdView addSubview:_priceLabel];

  _originalPriceLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [_nativeAdView addSubview:_originalPriceLabel];

  _discountRateLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [_nativeAdView addSubview:_discountRateLabel];

  // LayoutConstraint 설정
  _nativeAdView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_nativeAdView.leadingAnchor constraintEqualToAnchor:self.leadingAnchor],
    [_nativeAdView.trailingAnchor constraintEqualToAnchor:self.trailingAnchor],
    [_nativeAdView.topAnchor constraintEqualToAnchor:self.topAnchor constant:8],
    [_nativeAdView.bottomAnchor constraintEqualToAnchor:self.bottomAnchor]
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
  ]];

  _priceLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_priceLabel.leadingAnchor constraintEqualToAnchor:_mediaView.leadingAnchor constant:8],
    [_priceLabel.topAnchor constraintEqualToAnchor:_descriptionLabel.bottomAnchor constant:8],
  ]];

  _originalPriceLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_originalPriceLabel.leadingAnchor constraintEqualToAnchor:_priceLabel.trailingAnchor constant:8],
    [_originalPriceLabel.topAnchor constraintEqualToAnchor:_descriptionLabel.bottomAnchor constant:8],
  ]];

  _discountRateLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_discountRateLabel.leadingAnchor constraintEqualToAnchor:_mediaView.leadingAnchor constant:8],
    [_discountRateLabel.topAnchor constraintEqualToAnchor:_originalPriceLabel.bottomAnchor constant:8],
    [_discountRateLabel.bottomAnchor constraintEqualToAnchor:_nativeAdView.bottomAnchor constant:-8],
  ]];
}

- (void)renderAd:(BZVNativeAd *)ad {
  BZVNativeAdViewBinder *viewBinder = [BZVNativeAdViewBinder viewBinderWithBlock:^(BZVNativeAdViewBinderBuilder * _Nonnull builder) {
    builder.nativeAdView = self.nativeAdView;
    builder.mediaView = self.mediaView;
    builder.titleLabel = self.titleLabel;
    builder.ctaView = self.ctaView;
    // 부가 기능: 뷰를 클릭할 수 있도록 설정합니다.
    builder.clickableViews = @[
      self.mediaView,
      self.priceLabel,
      self.originalPriceLabel,
      self.discountRateLabel,
      self.ctaView,
    ];
  }];
  [viewBinder bindWithNativeAd:ad];

  BZVNativeAdProduct *product = ad.product;
  if (product.discountedPrice != 0) {
    // 할인이 있는 쇼핑 광고
    _priceLabel.text = [self formattedStringFromNumber:@(product.discountedPrice)];
    _originalPriceLabel.text = [self formattedStringFromNumber:@(product.price)];
    CGFloat discountRate = (1 - product.discountedPrice / product.price) * 100;
    _discountRateLabel.text = [NSString stringWithFormat:@"%.f%%", discountRate];
  } else {
    // 할인이 없는 쇼핑 광고
    _priceLabel.text = [self formattedStringFromNumber:@(product.price)];
    _originalPriceLabel.text = [self formattedStringFromNumber:@(product.price)];
    _discountRateLabel.hidden = YES;
  }
}

- (NSString *)formattedStringFromNumber:(NSNumber *)number {
  NSNumberFormatter *formatter = [[NSNumberFormatter alloc] init];
  formatter.numberStyle = NSNumberFormatterDecimalStyle;
  return [formatter stringFromNumber:number];
}

@end
