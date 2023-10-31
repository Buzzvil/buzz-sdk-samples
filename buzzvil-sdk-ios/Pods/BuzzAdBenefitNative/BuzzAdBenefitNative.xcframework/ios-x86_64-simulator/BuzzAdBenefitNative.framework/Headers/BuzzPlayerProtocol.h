@import UIKit;
@import CoreMedia;

extern NSNotificationName _Nonnull const BuzzPlayerDidFinishPlayingNotification;

@protocol BuzzPlayerProtocol <NSObject>

@property (nonatomic) BOOL muted;
@property (readonly) CMTime duration;
@property (nonatomic, readonly, nullable) NSError *error;

- (void)play;
- (void)pause;
- (void)replay;

- (BOOL)isPlaying;
- (BOOL)isFinished;

- (CMTime)currentTime;
- (void)seekToTime:(CMTime)time;
- (void)seekToTime:(CMTime)time toleranceBefore:(CMTime)toleranceBefore toleranceAfter:(CMTime)toleranceAfter;

@property (nonatomic, nullable) id videoGravity;
- (void)attachView:(nonnull UIView *)view;
- (void)detachView;

@end
