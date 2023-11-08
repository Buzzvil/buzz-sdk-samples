//##ARTHUR
//#import "CarouselFeedEntryView.h"
//
//@interface CarouselFeedEntryView ()
//
//@property (nonatomic, strong, readonly) UIButton *button;
//
//@end
//
//@implementation CarouselFeedEntryView
//
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
//  _button = [[UIButton alloc] initWithFrame:CGRectZero];
//  [_button setTitle:@"포인트 더 받으러 가기" forState:UIControlStateNormal];
//  [_button setTitleColor:[UIColor systemBlueColor] forState:UIControlStateNormal];
//  [self addSubview:_button];
//  self.clickableViews = @[self.button];
//}
//
//- (void)setupLayout {
//  _button.translatesAutoresizingMaskIntoConstraints = NO;
//  [NSLayoutConstraint activateConstraints:@[
//    [_button.topAnchor constraintEqualToAnchor:self.topAnchor],
//    [_button.leadingAnchor constraintEqualToAnchor:self.leadingAnchor],
//    [_button.trailingAnchor constraintEqualToAnchor:self.trailingAnchor],
//    [_button.bottomAnchor constraintEqualToAnchor:self.bottomAnchor],
//  ]];
//}
//
//@end
