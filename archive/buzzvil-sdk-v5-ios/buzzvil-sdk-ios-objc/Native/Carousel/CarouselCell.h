#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface CarouselCell : UICollectionViewCell

- (void)setPool:(BZVNativeAd2Pool *)pool forAdKey:(NSInteger)adKey;
- (void)bind;
- (void)setupLoading;
- (void)setupEventListeners;

@end

NS_ASSUME_NONNULL_END
