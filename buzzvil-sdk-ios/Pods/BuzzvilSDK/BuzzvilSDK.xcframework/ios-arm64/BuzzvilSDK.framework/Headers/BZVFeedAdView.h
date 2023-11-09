#import <UIKit/UIKit.h>
#import <BuzzAdBenefitSDK/BuzzAdBenefitSDK.h>

@class BZVNativeAd;

NS_ASSUME_NONNULL_BEGIN

@interface BZVFeedAdView : UIView

+ (CGFloat)desiredHeight:(CGFloat)width;
- (void)renderAd:(BZVNativeAd *)ad;

@end

NS_ASSUME_NONNULL_END
