@import BuzzAdBenefitSDK;
#import "CarouselCell.h"

@interface CarouselCell ()

@property (nonatomic, strong, readonly) BZVNativeAd2View *nativeAd2View;
@property (nonatomic, strong, readonly) BZVMediaView *mediaView;
@property (nonatomic, strong, readonly) UIImageView *iconImageView;
@property (nonatomic, strong, readonly) UILabel *titleLabel;
@property (nonatomic, strong, readonly) UILabel *descriptionLabel;
@property (nonatomic, strong, readonly) BZVDefaultCtaView *ctaView;

@property (nonatomic, strong, readonly) BZVNativeAd2ViewBinder *viewBinder;


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
  _nativeAd2View = [[BZVNativeAd2View alloc] initWithFrame:CGRectZero];
  [self.contentView addSubview:_nativeAd2View];
  
  _mediaView = [[BZVMediaView alloc] initWithFrame:CGRectZero];
  [self.nativeAd2View addSubview:_mediaView];
  
  _iconImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
  [self.nativeAd2View addSubview:_iconImageView];
  
  _titleLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [self.nativeAd2View addSubview:_titleLabel];
  
  _descriptionLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [self.nativeAd2View addSubview:_descriptionLabel];
  
  _ctaView = [[BZVDefaultCtaView alloc] initWithFrame:CGRectZero];
  [self.nativeAd2View addSubview:_ctaView];
  
  // NativeAd2View와 하위 컴포넌트를 연결합니다.
  [BZVNativeAd2ViewBinder viewBinderWithBlock:^(BZVNativeAd2ViewBinderBuilder * _Nonnull builder) {
    builder.unitId = @"YOUR_NATIVE_UNIT_ID";
    builder.nativeAd2View = self.nativeAd2View;
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
  // eg. auto layout constraints for nativeAd2View
  _nativeAd2View.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_nativeAd2View.topAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.topAnchor],
    [_nativeAd2View.leadingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.leadingAnchor constant:16],
    [_nativeAd2View.trailingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.trailingAnchor constant:-16],
    [_nativeAd2View.bottomAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.bottomAnchor],
  ]];
  
  // AutoLayout Constraints를 설정하세요.
  // ...
  
  // 로딩화면 구현하기
  _activityIndicatorView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_activityIndicatorView.centerXAnchor constraintEqualToAnchor:self.centerXAnchor],
    [_activityIndicatorView.centerYAnchor constraintEqualToAnchor:self.centerYAnchor],
  ]];
}

// 앞뒤 광고 아이템을 부분적으로 노출하기
//- (void)setupLayout {
//  // CarouselCell과 동일한 크기로 설정합니다.
//  _nativeAd2View.translatesAutoresizingMaskIntoConstraints = NO;
//  [NSLayoutConstraint activateConstraints:@[
//    [_nativeAd2View.topAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.topAnchor],
//    [_nativeAd2View.leadingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.leadingAnchor],
//    [_nativeAd2View.trailingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.trailingAnchor],
//    [_nativeAd2View.bottomAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.bottomAnchor],
//  ]];
//  
//  // ...
//}

- (void)prepareForReuse {
  [super prepareForReuse];
  // prepareForReuse 내에서 unbind를 반드시 호출하여 cell을 재사용할 때 문제가 발생하지 않게 합니다.
  [_viewBinder unbind];
}

// collectionView의 collectionView(_:cellForItemAt:) 시점에 호출합니다.
- (void)setPool:(BZVNativeAd2Pool *)pool forAdKey:(NSInteger)adKey {
  // 해당 index(adKey)에 해당하는 NativeAd2ViewBinder가 NativeAd2Pool을 사용하도록 합니다.
  [_viewBinder setPool:pool at:adKey];
}

// collectionView의 collectionView(_:cellForItemAt:) 시점에 호출합니다.
- (void)bind {
  // NativeAd2ViewBinder의 bind()를 호출하면 광고 할당 및 갱신이 자동으로 수행됩니다.
  [_viewBinder bind];
}

// 로딩화면 구현하기
- (void)setupLoading {
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
