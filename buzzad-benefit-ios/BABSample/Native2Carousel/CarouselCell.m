#import "CarouselCell.h"

@import BuzzAdBenefit;
@import Toast;

#import "AppConstant.h"

@interface CarouselCell ()

@property (nonatomic, strong, readonly) BZVNativeAd2View *nativeAd2View;
@property (nonatomic, strong, readonly) BZVMediaView *mediaView;
@property (nonatomic, strong, readonly) UIImageView *iconImageView;
@property (nonatomic, strong, readonly) UILabel *titleLabel;
@property (nonatomic, strong, readonly) UILabel *descriptionLabel;
@property (nonatomic, strong, readonly) BZVDefaultCtaView *ctaView;
//CarouselFeedEntryView *_feedEntryView;
@property (nonatomic, strong, readonly) UIActivityIndicatorView *activityIndicatorView;
@property (nonatomic, strong, readonly) BZVNativeAd2ViewBinder *viewBinder;

@end

@implementation CarouselCell

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
  _nativeAd2View = [[BZVNativeAd2View alloc] initWithFrame:CGRectZero];
  _nativeAd2View.layer.cornerRadius = 8;
  _nativeAd2View.clipsToBounds = YES;
  [self.contentView addSubview:_nativeAd2View];
  
  _mediaView = [[BZVMediaView alloc] initWithFrame:CGRectZero];
  _mediaView.layer.cornerRadius = 8;
  _mediaView.clipsToBounds = YES;
  [_nativeAd2View addSubview:_mediaView];
  
  _iconImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
  [_nativeAd2View addSubview:_iconImageView];
  
  _titleLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [_nativeAd2View addSubview:_titleLabel];
  
  _descriptionLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  _descriptionLabel.numberOfLines = 2;
  [_nativeAd2View addSubview:_descriptionLabel];
  
  _ctaView = [[BZVDefaultCtaView alloc] initWithFrame:CGRectZero];
  [_nativeAd2View addSubview:_ctaView];
  
  //_feedEntryView = [[CarouselFeedEntryView alloc] initWithFrame:CGRectZero];
  
  _activityIndicatorView = [[UIActivityIndicatorView alloc] initWithFrame:CGRectZero];
  _activityIndicatorView.hidesWhenStopped = YES;
  [self.contentView addSubview:_activityIndicatorView];
  
  _viewBinder = [BZVNativeAd2ViewBinder viewBinderWithBlock:^(BZVNativeAd2ViewBinderBuilder * _Nonnull builder) {
    builder.unitId = NATIVE_UNIT_ID;
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
      [_nativeAd2View.topAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.topAnchor],
      [_nativeAd2View.leadingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.leadingAnchor constant:16],
      [_nativeAd2View.trailingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.trailingAnchor constant:-16],
      [_nativeAd2View.bottomAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.bottomAnchor],
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
  
  _activityIndicatorView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_activityIndicatorView.centerXAnchor constraintEqualToAnchor:self.centerXAnchor],
    [_activityIndicatorView.centerYAnchor constraintEqualToAnchor:self.centerYAnchor],
  ]];
}

- (void)prepareForReuse {
  [super prepareForReuse];
  [_viewBinder unbind];
}

- (void)setPool:(BZVNativeAd2Pool *)pool forAdKey:(NSInteger)adKey {
  [_viewBinder setPool:pool at:adKey];
}

- (void)bind {
  [_viewBinder bind];
}

- (void)setupActivityIndicator {
  __weak typeof(self) weakSelf = self;
  [_viewBinder subscribeEventsOnRequest:^{
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.activityIndicatorView startAnimating];
      strongSelf.nativeAd2View.alpha = 0.5;
    }
  } onNext:^(BZVNativeAd2 * _Nonnull ad) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.activityIndicatorView stopAnimating];
      strongSelf.nativeAd2View.alpha = 1;
    }
  } onError:^(NSError * _Nonnull error) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.activityIndicatorView stopAnimating];
      strongSelf.nativeAd2View.alpha = 1;
      NSLog(@"error: %@", error);
    }
  } onCompleted:^{
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.activityIndicatorView stopAnimating];
      strongSelf.nativeAd2View.alpha = 1;
      NSLog(@"completed");
    }
  }];
}

- (void)setupEventListeners {
  __weak typeof(self) weakSelf = self;
  [_viewBinder subscribeAdEventsOnImpressed:^(BZVNativeAd2 * _Nonnull ad) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      NSLog(@"impressed: %@", ad.title);
    }
  } onClicked:^(BZVNativeAd2 * _Nonnull ad) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      NSLog(@"clicked: %@", ad.title);
    }
  } onRewardRequested:^(BZVNativeAd2 * _Nonnull ad) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      NSLog(@"requested reward: %@", ad.title);
    }
  } onRewarded:^(BZVNativeAd2 * _Nonnull ad, BZVRewardResult result) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      NSLog(@"received reward result: %@", ad.title);
    }
  } onParticipated:^(BZVNativeAd2 * _Nonnull ad) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      NSLog(@"participated: %@", ad.title);
    }
  }];
}

@end
