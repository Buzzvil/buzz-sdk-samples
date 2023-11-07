#import <Foundation/Foundation.h>

@class BZVNativeAd;
@class BZVNativeArticle;

NS_ASSUME_NONNULL_BEGIN

@interface BZVLaunchInfo : NSObject

@property (nonatomic, copy, readonly) NSURL *url;
@property (nonatomic, assign, readonly) BOOL shouldLandingExternalBrowser;

@end

@interface BZVLaunchInfo ()

@property (nonatomic, copy, nullable, readonly) BZVNativeAd *ad;
@property (nonatomic, copy, nullable, readonly) BZVNativeArticle *article;

@end

NS_ASSUME_NONNULL_END
