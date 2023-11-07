#import <Foundation/Foundation.h>

@protocol BABNativeAdPresenterProtocol <NSObject>

- (void)mediaViewPrepared;
- (BOOL)shouldAutoPlayVideo;

- (void)playButtonTapped;
- (void)pauseButtonTapped;
- (void)fullscreenButtonTapped;
- (void)learnMoreButtonTapped;
- (void)muteButtonTapped;
- (void)unmuteButtonTapped;

- (void)impressed;
- (void)clicked;

- (void)videoWillPlaying;
- (void)videoPassedMinimumPlayTime:(NSTimeInterval)seconds;
- (void)videoFinished:(NSTimeInterval)seconds;
- (void)videoPaused:(NSTimeInterval)seconds;

@end
