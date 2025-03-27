#import "CustomFeedBaseRewardViewHolder.h"

// MARK: 피드 고급 설정 - 기본 적립 포인트 알림 팝업 자체 구현하기
@interface CustomFeedBaseRewardViewHolder ()

@property (nonatomic, strong, readonly) UILabel *baseRewardLabel;

@end

@implementation CustomFeedBaseRewardViewHolder

+ (CGFloat)desiredHeight {
  return 100;
}

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
  self.backgroundColor = UIColor.blackColor;

  _baseRewardLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  _baseRewardLabel.textColor = UIColor.whiteColor;
  [self addSubview:_baseRewardLabel];

  // LayoutConstraint 설정
  _baseRewardLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_baseRewardLabel.centerXAnchor constraintEqualToAnchor:self.centerXAnchor],
    [_baseRewardLabel.centerYAnchor constraintEqualToAnchor:self.centerYAnchor],
  ]];
}

- (void)didUpdateBaseReward:(NSInteger)baseReward {
  _baseRewardLabel.text = [NSString stringWithFormat:@"Received %@ points", @(baseReward)];
}

@end
