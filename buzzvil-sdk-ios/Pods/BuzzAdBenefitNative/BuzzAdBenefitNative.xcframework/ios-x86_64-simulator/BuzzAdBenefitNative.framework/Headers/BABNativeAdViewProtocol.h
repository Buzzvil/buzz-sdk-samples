#import <UIKit/UIKit.h>
#import <BuzzAdBenefitBase/BABRewardResult.h>

@class BuzzMediaView;

@protocol BABNativeAdViewProtocol <NSObject>

- (void)renderImageMediaAtUrl:(NSURL *)url;
- (void)renderVideoMediaAtUrl:(NSURL *)url autoPlay:(BOOL)autoPlay thumbnailImageUrl:(NSURL *)thumbnailImageUrl fromSecond:(NSTimeInterval)fromSecond mute:(BOOL)mute;
- (void)renderVideoMediaAtVastTag:(NSString *)vastTag autoPlay:(BOOL)autoPlay thumbnailImageUrl:(NSURL *)url fromSecond:(NSTimeInterval)fromSecond mute:(BOOL)mute;

- (void)switchToFullscreen;

- (void)handleImpression;
- (void)handleClick;
- (void)handleVideoPlayFinish;
- (void)handleRewardSuccess;
- (void)handleRewardStart;
- (void)handleRewardFailure:(BABRewardResult)result;
- (void)handleParticipation;
- (void)handleLandingPageOpen;
@end
