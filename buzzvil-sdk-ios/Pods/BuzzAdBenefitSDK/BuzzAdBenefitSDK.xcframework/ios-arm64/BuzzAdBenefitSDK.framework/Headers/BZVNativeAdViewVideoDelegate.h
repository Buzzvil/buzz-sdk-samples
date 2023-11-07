#import <UIKit/UIKit.h>

@class BZVNativeAd;
@class BZVNativeAdView;

NS_ASSUME_NONNULL_BEGIN

@protocol BZVNativeAdViewVideoDelegate <NSObject>

- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView willStartPlayingVideoAd:(BZVNativeAd *)nativeAd;
- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView didResumeVideoAd:(BZVNativeAd *)nativeAd;
- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView willReplayVideoAd:(BZVNativeAd *)nativeAd;
- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView didPauseVideoAd:(BZVNativeAd *)nativeAd;
- (void)BZVNativeAdView:(BZVNativeAdView *)nativeAdView didFinishPlayingVideoAd:(BZVNativeAd *)nativeAd;

@end

NS_ASSUME_NONNULL_END
