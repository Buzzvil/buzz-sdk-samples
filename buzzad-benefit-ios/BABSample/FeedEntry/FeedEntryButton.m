#import "FeedEntryButton.h"

@implementation FeedEntryButton

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    [self setupView];
    [self setupLayout];
  }
  return self;
}

- (void)setupView {
  _button = [[UIButton alloc] initWithFrame:CGRectZero];
  _button.backgroundColor = [UIColor colorWithRed:18/255.0 green:144/255.0 blue:255/255.0 alpha:1];
  _button.layer.cornerRadius = 8;
  _button.layer.masksToBounds = YES;
  _button.titleLabel.font = [UIFont systemFontOfSize:12];
  [_button.titleLabel setTextColor:UIColor.whiteColor];
  [_button setContentEdgeInsets:UIEdgeInsetsMake(0, 10, 0, 10)];
  [self addSubview:_button];

  self.clickableViews = @[self.button];
}

- (void)setupLayout {
  _button.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_button.leadingAnchor constraintEqualToAnchor:self.leadingAnchor],
    [_button.topAnchor constraintEqualToAnchor:self.topAnchor],
    [_button.heightAnchor constraintEqualToConstant:32],
  ]];
}

@end
