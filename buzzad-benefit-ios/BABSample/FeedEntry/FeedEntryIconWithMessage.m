#import "FeedEntryIconWithMessage.h"
#import "MessageView.h"

@interface FeedEntryIconWithMessage () {
  MessageView *_messageView;
}

@end

@implementation FeedEntryIconWithMessage

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    [self setupView];
    [self setupLayout];
  }
  return self;
}

- (void)setupView {
  self.backgroundColor = UIColor.clearColor;
  
  _iconBackgoundView = [[UIView alloc] initWithFrame:CGRectZero];
  _iconBackgoundView.backgroundColor = UIColor.whiteColor;
  _iconBackgoundView.layer.cornerRadius = 4;
  _iconBackgoundView.layer.shadowColor = [UIColor colorWithWhite:0 alpha:0.16].CGColor;
  _iconBackgoundView.layer.shadowOpacity = 1;
  _iconBackgoundView.layer.shadowOffset = CGSizeMake(0, 0);
  _iconBackgoundView.layer.shadowRadius = 4;
  _iconBackgoundView.layer.shouldRasterize = YES;
  _iconBackgoundView.layer.rasterizationScale = UIScreen.mainScreen.scale;
  [self addSubview:_iconBackgoundView];
  
  _iconImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
  [self addSubview:_iconImageView];
  
  _messageView = [[MessageView alloc] initWithFrame:CGRectZero];
  [self addSubview:_messageView];
}

- (void)setupLayout {
  _iconBackgoundView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_iconBackgoundView.leadingAnchor constraintEqualToAnchor:self.leadingAnchor],
    [_iconBackgoundView.centerYAnchor constraintEqualToAnchor:self.centerYAnchor],
    [_iconBackgoundView.widthAnchor constraintEqualToConstant:32],
    [_iconBackgoundView.heightAnchor constraintEqualToConstant:32],
  ]];
  
  _iconImageView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_iconImageView.centerXAnchor constraintEqualToAnchor:_iconBackgoundView.centerXAnchor],
    [_iconImageView.centerYAnchor constraintEqualToAnchor:_iconBackgoundView.centerYAnchor],
    [_iconImageView.widthAnchor constraintEqualToConstant:24],
    [_iconImageView.heightAnchor constraintEqualToConstant:24],
  ]];
  
  _messageView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_messageView.leadingAnchor constraintEqualToAnchor:_iconBackgoundView.trailingAnchor constant:4],
    [_messageView.centerYAnchor constraintEqualToAnchor:self.centerYAnchor],
    [_messageView.heightAnchor constraintEqualToConstant:24],
  ]];
}

- (UILabel *)messageLabel {
  return _messageView.textLabel;
}

@end
