//
//  BABNativeAdPresenterProtocol.h
//  BAB
//
//  Created by Jaehee Ko on 22/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

@class BABCreative;

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
