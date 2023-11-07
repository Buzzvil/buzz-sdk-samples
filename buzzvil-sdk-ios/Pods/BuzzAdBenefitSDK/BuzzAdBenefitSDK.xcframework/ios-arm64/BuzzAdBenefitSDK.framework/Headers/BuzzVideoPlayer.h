#import <UIKit/UIKit.h>
#import <AVKit/AVKit.h>

NS_ASSUME_NONNULL_BEGIN

@protocol BuzzPlayerProtocol;
@class BuzzVideoPlayer;
@class BuzzVideoPlayerOverlay;
@class BuzzVideoPlayerConfig;

@protocol BuzzVideoPlayerDelegate <NSObject>
- (void)BuzzVideoPlayerDidTappedLearnMore:(BuzzVideoPlayer *)videoPlayer;
- (void)BuzzVideoPlayerDidTappedFullscreen:(BuzzVideoPlayer *)videoPlayer;
- (NSTimeInterval)BuzzVideoPlayerMinimumPlayTime:(BuzzVideoPlayer *)videoPlayer;
- (BOOL)BuzzVideoPlayerShouldShowRemainingTime:(BuzzVideoPlayer *)videoPlayer;
@optional
- (BOOL)BuzzVideoPlayerShouldAutoPlay:(BuzzVideoPlayer *)videoPlayer;
- (BOOL)BuzzVideoPlayerShouldPlay:(BuzzVideoPlayer *)videoPlayer;
- (void)BuzzVideoPlayerDidTappedPlayButton:(BuzzVideoPlayer *)videoPlayer wasPlaying:(BOOL)wasPlaying;
- (void)BuzzVideoPlayerDidTappedMuteButton:(BuzzVideoPlayer *)videoPlayer wasMuted:(BOOL)wasMuted;
- (void)BuzzVideoPlayerWillStartPlayingVideo:(BuzzVideoPlayer *)videoPlayer;
- (void)BuzzVideoPlayerDidResumeVideo:(BuzzVideoPlayer *)videoPlayer;
- (void)BuzzVideoPlayerDidPassMinimumPlayTime:(BuzzVideoPlayer *)videoPlayer atSecond:(NSTimeInterval)second;
- (void)BuzzVideoPlayerDidFinishPlayingVideo:(BuzzVideoPlayer *)videoPlayer atSecond:(NSTimeInterval)second;
- (void)BuzzVideoPlayerDidPause:(BuzzVideoPlayer *)videoPlayer atSecond:(NSTimeInterval)second;
- (void)BuzzVideoPlayerWillReplayVideo:(BuzzVideoPlayer *)videoPlayer;
@end

@interface BuzzVideoPlayer : UIView

@property (nonatomic, weak) id<BuzzVideoPlayerDelegate> delegate;
@property (nonatomic, readonly) BOOL isPlaying;
@property (nonatomic, readonly) BOOL isMuted;
@property (nonatomic, readonly) BOOL isFinished;
@property (nonatomic, readonly) id<BuzzPlayerProtocol> buzzPlayer;
@property (nonatomic, assign) AVLayerVideoGravity videoGravity;
@property (nonatomic, strong) BuzzVideoPlayerOverlay *overlay;
@property (nonatomic, strong) BuzzVideoPlayerConfig *config;

- (void)loadVideoAtUrl:(NSURL *)url autoPlay:(BOOL)autoPlay fromSecond:(NSTimeInterval)fromSecond mute:(BOOL)mute;
- (void)loadVideoWithBuzzPlayer:(id<BuzzPlayerProtocol>)player authPlay:(BOOL)autoPlay;
- (void)loadVideoAtVastTag:(NSString *)vastTag autoPlay:(BOOL)autoPlay fromSecond:(NSTimeInterval)fromSecond mute:(BOOL)mute;

- (void)play;
- (void)startAutoPlay;
- (void)pause;
- (void)reset;
- (void)syncUI;
- (void)showControlForFullscreenMode;
- (void)hideControlForFullscreenMode;
- (void)detachView;

@end

NS_ASSUME_NONNULL_END
