#import "FeedEntryBanner.h"

@implementation FeedEntryBanner

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    [self setupView];
    [self setupLayout];
  }
  return self;
}

- (void)setupView {
  _bannerImageView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"banner_image"]];
  [self addSubview:_bannerImageView];

  self.clickableViews = @[self.bannerImageView];
}

- (void)setupLayout {
  CGFloat aspectRatio = _bannerImageView.image.size.width / _bannerImageView.image.size.height;
  _bannerImageView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_bannerImageView.centerXAnchor constraintEqualToAnchor:self.centerXAnchor],
    [_bannerImageView.centerYAnchor constraintEqualToAnchor:self.centerYAnchor],
    [_bannerImageView.widthAnchor constraintEqualToAnchor:self.widthAnchor],
    [_bannerImageView.heightAnchor constraintEqualToAnchor:_bannerImageView.widthAnchor multiplier:1/aspectRatio],
  ]];
}

@end
