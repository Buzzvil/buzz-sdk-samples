@import BuzzvilSDK;
#import "CarouselCell.h"

@interface CarouselCell ()

@property (nonatomic, strong, readonly) BuzzNativeAdView *nativeAdView;
@property (nonatomic, strong, readonly) BuzzMediaView *mediaView;
@property (nonatomic, strong, readonly) UIImageView *iconImageView;
@property (nonatomic, strong, readonly) UILabel *titleLabel;
@property (nonatomic, strong, readonly) UILabel *descriptionLabel;
@property (nonatomic, strong, readonly) BuzzDefaultCtaView *ctaView;

@property (nonatomic, strong, readonly) BuzzNativeViewBinder *viewBinder;


// 로딩화면 구현하기
@property (nonatomic, strong, readonly) UIActivityIndicatorView *activityIndicatorView;

@end

@implementation CarouselCell

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    [self setupView];
    [self setupLayout];
  }
  return self;
}

- (void)setupView {
  _nativeAdView = [[BuzzNativeAdView alloc] initWithFrame:CGRectZero];
  [self.contentView addSubview:_nativeAdView];
  
  _mediaView = [[BuzzMediaView alloc] initWithFrame:CGRectZero];
  [self.nativeAdView addSubview:_mediaView];
  
  _iconImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
  [self.nativeAdView addSubview:_iconImageView];
  
  _titleLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [self.nativeAdView addSubview:_titleLabel];
  
  _descriptionLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [self.nativeAdView addSubview:_descriptionLabel];
  
  _ctaView = [[BuzzDefaultCtaView alloc] initWithFrame:CGRectZero];
  [self.nativeAdView addSubview:_ctaView];
  
  // NativeAdView와 하위 컴포넌트를 연결합니다.
  _viewBinder = [BuzzNativeViewBinder viewBinderWith:^(BuzzNativeViewBinderBuilder * _Nonnull builder) {
    builder.nativeAdView = self.nativeAdView;
    builder.mediaView = self.mediaView;
    builder.iconImageView = self.iconImageView;
    builder.titleLabel = self.titleLabel;
    builder.descriptionLabel = self.descriptionLabel;
    builder.ctaView = self.ctaView;
  }];
  
  // 로딩화면 구현하기
  _activityIndicatorView = [[UIActivityIndicatorView alloc] initWithFrame:CGRectZero];
  _activityIndicatorView.hidesWhenStopped = YES;
  [self.contentView addSubview:_activityIndicatorView];
}

- (void)setupLayout {
  _nativeAdView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_nativeAdView.topAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.topAnchor],
    [_nativeAdView.leadingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.leadingAnchor constant:16],
    [_nativeAdView.trailingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.trailingAnchor constant:-16],
    [_nativeAdView.bottomAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.bottomAnchor],
  ]];
  
  // MARK: 네이티브 캐러셀 구현 - 앞뒤 광고 아이템을 부분적으로 노출하기
//  _nativeAdView.translatesAutoresizingMaskIntoConstraints = NO;
//  [NSLayoutConstraint activateConstraints:@[
//    [_nativeAdView.topAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.topAnchor],
//    [_nativeAdView.leadingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.leadingAnchor],
//    [_nativeAdView.trailingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.trailingAnchor],
//    [_nativeAdView.bottomAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.bottomAnchor],
//  ]];
  
  _mediaView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_mediaView.topAnchor constraintEqualToAnchor:_nativeAdView.topAnchor],
    [_mediaView.leadingAnchor constraintEqualToAnchor:_nativeAdView.leadingAnchor],
    [_mediaView.trailingAnchor constraintEqualToAnchor:_nativeAdView.trailingAnchor],
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
    [_titleLabel.trailingAnchor constraintEqualToAnchor:_nativeAdView.trailingAnchor constant:-8],
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
    [_ctaView.trailingAnchor constraintEqualToAnchor:_nativeAdView.trailingAnchor constant:-8],
    [_ctaView.bottomAnchor constraintEqualToAnchor:_nativeAdView.bottomAnchor constant:-8],
    [_ctaView.heightAnchor constraintEqualToConstant:32],
  ]];
  
  // 로딩화면 구현하기
  _activityIndicatorView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_activityIndicatorView.centerXAnchor constraintEqualToAnchor:self.centerXAnchor],
    [_activityIndicatorView.centerYAnchor constraintEqualToAnchor:self.centerYAnchor],
  ]];
}

- (void)prepareForReuse {
  [super prepareForReuse];
  // prepareForReuse 내에서 unbind를 반드시 호출하여 cell을 재사용할 때 문제가 발생하지 않게 합니다.
  [_viewBinder unbind];
}

// collectionView의 collectionView(_:cellForItemAt:) 시점에 호출합니다.
- (void)bind:(BuzzNative *)native {
  __weak typeof(self) weakSelf = self;
  
  // 로딩화면 구현하기
  [native subscribeRefreshEventsOnRequest:^{
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.activityIndicatorView startAnimating];
      strongSelf.nativeAdView.alpha = 0.5;
    }
  } onSuccess:^(BuzzNativeAd * _Nonnull _) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.activityIndicatorView stopAnimating];
      strongSelf.nativeAdView.alpha = 1;
    }
  } onFailure:^(NSError * _Nonnull error) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.activityIndicatorView stopAnimating];
      strongSelf.nativeAdView.alpha = 1;
    }
  }];
  
  // 광고 이벤트 리스너 등록하기
  [native subscribeAdEventsOnImpressed:^(BuzzNativeAd * _Nonnull nativeAd) {
    NSLog(@"impressed: %@", nativeAd.title);
  } onClicked:^(BuzzNativeAd * _Nonnull nativeAd) {
    NSLog(@"clicked: %@", nativeAd.title);
  } onRewardRequested:^(BuzzNativeAd * _Nonnull nativeAd) {
    NSLog(@"requested reward: %@", nativeAd.title);
  } onRewarded:^(BuzzNativeAd * _Nonnull nativeAd, enum BuzzRewardResult result) {
    NSLog(@"received reward result: %@, %ld", nativeAd.title, (long)result);
  } onParticipated:^(BuzzNativeAd * _Nonnull nativeAd) {
    NSLog(@"impressed: %@", nativeAd.title);
  }];
  
  // bind()를 호출하면 할당된 광고 표시 및 갱신이 자동으로 수행됩니다.
  [_viewBinder bind:native];
}

@end
