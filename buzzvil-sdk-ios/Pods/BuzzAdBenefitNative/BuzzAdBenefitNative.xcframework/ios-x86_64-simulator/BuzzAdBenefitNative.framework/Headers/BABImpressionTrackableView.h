@import Foundation;

@protocol BABImpressionTrackableView <NSObject>
- (BOOL)shouldTrackImpression;
- (void)viewDidImpressed;
@optional
- (void)viewBecomeVisible;
- (void)viewBecomeInvisible;
@end
