#import <UIKit/UIKit.h>
#import <BuzzAdBenefitSDK/BuzzAdBenefitSDK.h>

@class BZVBuzzAdFeed;

NS_ASSUME_NONNULL_BEGIN

@interface BZVFeedEntryView : UIView

@property (nonatomic, strong, readwrite) NSArray<UIView *> *clickableViews;
@property (nonatomic, strong, readonly) BZVBuzzAdFeed *buzzAdFeed;

- (void)setEntryName:(NSString *)entryName;

@end

NS_ASSUME_NONNULL_END
