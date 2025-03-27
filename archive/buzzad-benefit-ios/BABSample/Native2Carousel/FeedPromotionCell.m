#import "FeedPromotionCell.h"
@import BuzzAdBenefit;

#import "AppConstant.h"

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
  if (self = [super initWithFrame:frame]) {
    [self setupView];
    [self setupLayout];
  }
  return self;
}

- (void)setupView {
  _feedPromotionView = [[BZVFeedPromotionView alloc] initWithFrame:CGRectZero];
  _creativeView = [[UIImageView alloc] initWithFrame:CGRectZero];
  _iconImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
  _titleLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  _ctaView = [[BZVDefaultCtaView alloc] initWithFrame:CGRectZero];
  
  [self.contentView addSubview:self.feedPromotionView];
  [self.feedPromotionView addSubview:self.creativeView];
  [self.feedPromotionView addSubview:self.iconImageView];
  [self.feedPromotionView addSubview:self.titleLabel];
  [self.feedPromotionView addSubview:self.ctaView];
  
  // NativeAd2View와 하위 컴포넌트를 연결합니다.
  _viewBinder = [BZVFeedPromotionViewBinder viewBinderWithBlock:^(BZVFeedPromotionViewBinderBuilder * _Nonnull builder) {
    builder.unitId = NATIVE_UNIT_ID;
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
  [self.viewBinder unbind];
}

- (void)bind {
  // BZVFeedPromotionViewBinder의 bind()를 호출하면 피드 진입 슬라이드 데이터가 자동으로 제공됩니다.
  [self.viewBinder bind];
}

@end
