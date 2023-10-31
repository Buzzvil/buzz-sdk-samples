#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface BZVFeedErrorViewHolder : UIView

/// Update error view when error occurs.
/// This method can be used to customize the error UI, such as image and text, based on the error code.
- (void)updateViewWithError:(NSError *)error;

@end

NS_ASSUME_NONNULL_END
