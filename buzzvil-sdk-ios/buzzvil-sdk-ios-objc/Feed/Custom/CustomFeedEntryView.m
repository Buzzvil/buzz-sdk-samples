#import "CustomFeedEntryView.h"

@interface CustomFeedEntryView ()

@property (nonatomic, strong, readonly) UIView *customSubview1;
@property (nonatomic, strong, readonly) UIView *customSubview2;

@end

@implementation CustomFeedEntryView

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

- (void)setupView {
  // subview 초기화 및 LayoutConstraint 설정
  // ...생략...
  
  // 클릭이 가능한 영역을 지정합니다.
  self.clickableViews = @[self.customSubview1, self.customSubview2];
}

@end
