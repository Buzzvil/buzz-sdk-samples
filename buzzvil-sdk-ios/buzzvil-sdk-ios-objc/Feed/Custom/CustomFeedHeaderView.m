#import "CustomFeedHeaderView.h"

@interface CustomFeedHeaderView ()

@property (nonatomic, strong, readonly) UILabel *headerLabel;

@end

@implementation CustomFeedHeaderView

+ (CGFloat)desiredHeight {
  return 60;
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
  _headerLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [self addSubview:_headerLabel];

  // LayoutConstraint 설정
  // ...
  _headerLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_headerLabel.trailingAnchor constraintEqualToAnchor:self.trailingAnchor],
    [_headerLabel.centerYAnchor constraintEqualToAnchor:self.centerYAnchor],
  ]];
}

- (void)availableRewardDidUpdate:(NSInteger)reward {
  _headerLabel.text = [NSString stringWithFormat:@"리워드 %ld원", reward];
}

@end
