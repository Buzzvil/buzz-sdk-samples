#import "UIButton+Custom.h"

@implementation UIButton (Custom)

- (void)applyCustomStyle {
  self.backgroundColor = UIColor.systemBlueColor;
  self.layer.cornerRadius = 8;
  [self setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
}

@end
