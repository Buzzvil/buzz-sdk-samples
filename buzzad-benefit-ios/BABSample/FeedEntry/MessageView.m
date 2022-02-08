//#import "MessageView.h"
//
//static CGFloat const kMessageTailWidth = 5;
//static CGFloat const kMessageTailHeight = 6;
//static CGFloat const kMessageCornerRadius = 4;
//
//@implementation MessageView
//
//- (instancetype)initWithFrame:(CGRect)frame {
//  if (self = [super initWithFrame:frame]) {
//    [self setupView];
//    [self setupLayout];
//  }
//  return self;
//}
//
//- (void)setupView {
//  self.backgroundColor = UIColor.clearColor;
//  
//  _textLabel = [[UILabel alloc] initWithFrame:CGRectZero];
//  _textLabel.font = [UIFont systemFontOfSize:12];
//  _textLabel.textColor = UIColor.whiteColor;
//  [self addSubview:_textLabel];
//}
//
//- (void)setupLayout {
//  _textLabel.translatesAutoresizingMaskIntoConstraints = NO;
//  [NSLayoutConstraint activateConstraints:@[
//    [_textLabel.leadingAnchor constraintEqualToAnchor:self.leadingAnchor constant:kMessageTailWidth + 4],
//    [_textLabel.trailingAnchor constraintEqualToAnchor:self.trailingAnchor constant:-4],
//    [_textLabel.centerYAnchor constraintEqualToAnchor:self.centerYAnchor],
//  ]];
//}
//
//- (void)drawRect:(CGRect)rect {
//  CGFloat width = rect.size.width;
//  CGFloat height = rect.size.height;
//  UIColor *fillColor = [UIColor colorWithWhite:0 alpha:0.55];
//  UIBezierPath *messageBodyPath = [UIBezierPath bezierPathWithRoundedRect:CGRectMake(kMessageTailWidth, 0, width - kMessageTailWidth, height)
//                                                             cornerRadius:kMessageCornerRadius];
//  [fillColor setFill];
//  [messageBodyPath fill];
//  
//  UIBezierPath *messageTailPath = [UIBezierPath bezierPath];
//  [messageTailPath moveToPoint:CGPointMake(0, height / 2)];
//  [messageTailPath addLineToPoint:CGPointMake(kMessageTailWidth, height / 2 - kMessageTailHeight / 2)];
//  [messageTailPath addLineToPoint:CGPointMake(kMessageTailWidth, height / 2 + kMessageTailHeight / 2)];
//  [messageTailPath addLineToPoint:CGPointMake(0, height / 2)];
//  [fillColor setFill];
//  [messageTailPath fill];
//  [messageTailPath closePath];
//}
//
//@end
