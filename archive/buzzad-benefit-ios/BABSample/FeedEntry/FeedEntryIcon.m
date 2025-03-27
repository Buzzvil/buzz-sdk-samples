#import "FeedEntryIcon.h"

@implementation FeedEntryIcon

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    [self setupView];
    [self setupLayout];
  }
  return self;
}

#pragma mark - UI setup
- (void)setupView {
  _iconImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
  [self addSubview:_iconImageView];

  self.clickableViews = @[self.iconImageView];
}

- (void)setupLayout {
  _iconImageView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_iconImageView.centerXAnchor constraintEqualToAnchor:self.centerXAnchor],
    [_iconImageView.centerYAnchor constraintEqualToAnchor:self.centerYAnchor],
    [_iconImageView.widthAnchor constraintEqualToConstant:24],
    [_iconImageView.heightAnchor constraintEqualToConstant:24],
  ]];
}

@end

