#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface BZVFeedHeaderViewHolder : UIView

+ (CGFloat)desiredHeight;
- (void)availableRewardDidUpdate:(NSInteger)reward;

@end

NS_ASSUME_NONNULL_END
