#import "CustomFeedHeaderViewHolder.h"

// MARK: 3.2. 헤더 영역 자체 구현하기
@interface CustomFeedHeaderViewHolder ()

@property (nonatomic, strong, readonly) UILabel *headerLabel;

@end

@implementation CustomFeedHeaderViewHolder

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

- (void)availableRewardDidUpdate:(NSInteger)reward {
  _headerLabel.text = [NSString stringWithFormat:@"리워드 %ld원", reward];
}

#pragma mark - UI setup
- (void)setupView {
  _headerLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [self addSubview:_headerLabel];

  // LayoutConstraint 설정
  _headerLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_headerLabel.leadingAnchor constraintEqualToAnchor:self.leadingAnchor],
    [_headerLabel.trailingAnchor constraintEqualToAnchor:self.trailingAnchor],
    [_headerLabel.topAnchor constraintEqualToAnchor:self.topAnchor],
    [_headerLabel.bottomAnchor constraintEqualToAnchor:self.bottomAnchor],
  ]];
}

@end
