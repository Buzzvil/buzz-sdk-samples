#import "UIButton+Custom.h"

@implementation UIButton (Custom)

- (void)applyCustomStyle {
  self.backgroundColor = UIColor.systemBlueColor;
  self.layer.cornerRadius = 8;
  [self setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
  self.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [self.heightAnchor constraintEqualToConstant:32],
  ]];
}

@end
