//
//  BuzzVideoPlayer.h
//  BuzzMediaView
//
//  Created by Jaehee Ko on 15/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVKit/AVKit.h>

NS_ASSUME_NONNULL_BEGIN

@class BuzzVideoPlayer;

@protocol BuzzVideoPlayerDelegate <NSObject>
- (void)BuzzVideoPlayerDidTappedLearnMore:(BuzzVideoPlayer *)videoPlayer;
- (void)BuzzVideoPlayerDidTappedFullscreen:(BuzzVideoPlayer *)videoPlayer;
@optional
- (void)BuzzVideoPlayerDidPassMinimumPlayTime:(BuzzVideoPlayer *)videoPlayer atSecond:(NSTimeInterval)second;
- (void)BuzzVideoPlayerDidFinishPlayingVideo:(BuzzVideoPlayer *)videoPlayer atSecond:(NSTimeInterval)second;
- (void)BuzzVideoPlayerDidPause:(BuzzVideoPlayer *)videoPlayer atSecond:(NSTimeInterval)second;
@end

@interface BuzzVideoPlayer : UIView

@property (nonatomic, weak) id<BuzzVideoPlayerDelegate> delegate;
@property (nonatomic, readonly) BOOL isPlaying;
@property (nonatomic, readonly) BOOL isMuted;
@property (nonatomic, readonly) BOOL isFinished;
@property (nonatomic, assign) NSTimeInterval minimumPlayTime;
@property (nonatomic, readonly) AVPlayer *player;
@property (nonatomic, assign) AVLayerVideoGravity videoGravity;

- (void)loadVideoAtUrl:(NSURL *)url minimumPlayTime:(NSTimeInterval)minimumPlayTime autoPlay:(BOOL)autoPlay;
- (void)loadVideoWithPlayer:(AVPlayer *)player minimumPlayTime:(NSTimeInterval)minimumPlayTime autoPlay:(BOOL)autoPlay;

- (void)play;
- (void)pause;
- (void)reset;
- (void)syncUI;
- (void)showControlForFullscreenMode;
- (void)hideControlForFullscreenMode;

@end

NS_ASSUME_NONNULL_END
