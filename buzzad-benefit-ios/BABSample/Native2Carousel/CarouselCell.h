@import UIKit;
@import BuzzAdBenefit;

NS_ASSUME_NONNULL_BEGIN

@interface CarouselCell : UICollectionViewCell

- (void)setPool:(BZVNativeAd2Pool *)pool forAdKey:(NSInteger)adKey;
- (void)bind;

@end

NS_ASSUME_NONNULL_END
