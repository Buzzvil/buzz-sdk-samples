@import UIKit;

NS_ASSUME_NONNULL_BEGIN

@interface BZVNativeArticle : NSObject

@property (nonatomic, copy, readonly) NSString *imageURL;
@property (nonatomic, assign, readonly) CGFloat imageAspectRatio;
@property (nonatomic, copy, readonly) NSString *iconURL;
@property (nonatomic, copy, readonly) NSString *channelName;
@property (nonatomic, strong, readonly) NSDate *createdAt;
@property (nonatomic, copy, readonly) NSString *title;
@property (nonatomic, copy, readonly) NSString *description;
@property (nonatomic, copy, readonly) NSString *sourceURL;

@end

NS_ASSUME_NONNULL_END
