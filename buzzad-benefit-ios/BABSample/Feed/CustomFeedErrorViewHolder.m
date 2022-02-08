#import "CustomFeedErrorViewHolder.h"

// MARK: 3.2. 광고 미할당 안내 디자인 자체 구현하기
@interface CustomFeedErrorViewHolder ()

@property (nonatomic, strong, readonly) UIImageView *imageView;

@end

@implementation CustomFeedErrorViewHolder

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

#pragma mark - UI setup
- (void)setupView {
  UIImage *image = [UIImage imageNamed:@"ic_apple"];
  _imageView = [[UIImageView alloc] initWithImage:image];
  [self addSubview:_imageView];

  // LayoutConstraint 설정
  _imageView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_imageView.centerXAnchor constraintEqualToAnchor:self.centerXAnchor],
    [_imageView.centerYAnchor constraintEqualToAnchor:self.centerYAnchor],
  ]];
}

@end
