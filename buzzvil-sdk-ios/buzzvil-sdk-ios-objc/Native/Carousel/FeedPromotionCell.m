#import "FeedPromotionCell.h"
@import BuzzvilSDK;

@interface FeedPromotionCell ()

@property (nonatomic, strong, readonly) BZVFeedPromotionView *feedPromotionView;
@property (nonatomic, strong, readonly) UIImageView *creativeView;
@property (nonatomic, strong, readonly) UIImageView *iconImageView;
@property (nonatomic, strong, readonly) UILabel *titleLabel;
@property (nonatomic, strong, readonly) BZVDefaultCtaView *ctaView;

@property (nonatomic, strong, readonly) BZVFeedPromotionViewBinder *viewBinder;

@end


@implementation FeedPromotionCell

- (instancetype)initWithFrame:(CGRect)frame {
  self = [super initWithFrame:frame];
  if (self) {
    [self setupView];
    [self setupLayout];
  }
  return self;
}

- (void)setupView {
  [self.contentView addSubview:self.feedPromotionView];
  [self.feedPromotionView addSubview:self.creativeView];
  [self.feedPromotionView addSubview:self.iconImageView];
  [self.feedPromotionView addSubview:self.titleLabel];
  [self.feedPromotionView addSubview:self.ctaView];
  
  // NativeAd2View와 하위 컴포넌트를 연결합니다.
  _viewBinder = [BZVFeedPromotionViewBinder viewBinderWithBlock:^(BZVFeedPromotionViewBinderBuilder * _Nonnull builder) {
    builder.unitId = @"59026668927900";
    builder.feedPromotionView = self.feedPromotionView;
    builder.creativeView = self.creativeView;
    builder.iconImageView = self.iconImageView;
    builder.titleLabel = self.titleLabel;
    builder.ctaView = self.ctaView;
  }];
}

- (void)setupLayout {
  // AutoLayout Constraints를 설정하세요.
  // ...
  _feedPromotionView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_feedPromotionView.topAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.topAnchor],
    [_feedPromotionView.leadingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.leadingAnchor constant:16],
    [_feedPromotionView.trailingAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.trailingAnchor constant:-16],
    [_feedPromotionView.bottomAnchor constraintEqualToAnchor:self.contentView.safeAreaLayoutGuide.bottomAnchor],
  ]];
}

- (void)prepareForReuse {
  [super prepareForReuse];
  // prepareForReuse 내에서 unbind를 반드시 호출하여 cell을 재사용할 때 문제가 발생하지 않게 합니다.
  [_viewBinder unbind];
}

// collectionView의 collectionView(_:cellForItemAt:) 시점에 호출합니다.
- (void)bind {
  // BZVFeedPromotionViewBinder의 bind()를 호출하면 베네핏허브 진입 슬라이드 데이터가 자동으로 제공됩니다.
  [_viewBinder bind];
}

@end
