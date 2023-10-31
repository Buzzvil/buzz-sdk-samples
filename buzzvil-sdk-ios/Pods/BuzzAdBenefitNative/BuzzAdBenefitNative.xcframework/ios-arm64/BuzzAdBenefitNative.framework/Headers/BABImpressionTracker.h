@import UIKit;

@protocol BABImpressionTrackableView;

@interface BABImpressionTracker: NSObject

+ (BABImpressionTracker *)sharedInstance;
- (void)configureMinimumDuration:(NSTimeInterval)duration areaRatio:(double)areaRatio;
- (void)start;
- (void)stop;
- (void)registerView:(UIView<BABImpressionTrackableView> *)view;
- (void)deregisterView:(UIView<BABImpressionTrackableView> *)view;
- (void)resetView:(UIView<BABImpressionTrackableView> *)view;

- (BOOL)isVisible:(UIView *)view;
- (BOOL)isVisible:(UIView *)view minimumVisibleAreaRatio:(double)ratio hitTest:(BOOL)hitTest;

@end
