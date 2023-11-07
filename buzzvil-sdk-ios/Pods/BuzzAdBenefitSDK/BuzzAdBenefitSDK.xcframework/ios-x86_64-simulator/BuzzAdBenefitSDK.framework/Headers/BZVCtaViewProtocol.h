@import Foundation;

NS_ASSUME_NONNULL_BEGIN

@protocol BZVCtaViewProtocol <NSObject>

- (void)renderParticipatingViewStateWithCtaText:(NSString *)ctaText;
- (void)renderParticipatedViewStateWithCtaText:(NSString *)ctaText;
- (void)renderRewardAvailableViewStateWithCtaText:(NSString *)ctaText reward:(NSInteger)reward;
- (void)renderRewardNotAvailableViewStateWithCtaText:(NSString *)ctaText;

@end

NS_ASSUME_NONNULL_END
