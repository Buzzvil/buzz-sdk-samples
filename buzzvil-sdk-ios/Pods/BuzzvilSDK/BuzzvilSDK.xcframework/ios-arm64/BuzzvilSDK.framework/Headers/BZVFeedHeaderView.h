#import <UIKit/UIKit.h>
#import <BuzzAdBenefitSDK/BuzzAdBenefitSDK.h>

NS_ASSUME_NONNULL_BEGIN

@interface BZVFeedHeaderView : UIView

+ (CGFloat)desiredHeight:(CGFloat)width;
- (void)availableRewardDidUpdate:(NSInteger)reward;

@end

NS_ASSUME_NONNULL_END
