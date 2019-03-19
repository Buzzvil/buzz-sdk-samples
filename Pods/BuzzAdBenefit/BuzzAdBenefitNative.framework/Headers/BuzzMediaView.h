//
//  BuzzMediaView.h
//  BuzzMediaView
//
//  Created by Jaehee Ko on 26/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BuzzVideoPlayer.h"

typedef enum {
  BuzzMediaViewAspectFit,
  BuzzMediaViewAspectFill
} BuzzMediaViewFillMode;

@interface BuzzMediaView : UIView 

@property (nonatomic, readonly) BuzzVideoPlayer *videoPlayer;
@property (nonatomic, assign) BuzzMediaViewFillMode fillMode;

- (void)loadImageAtUrl:(NSURL *)url;
- (void)loadVideoAtUrl:(NSURL *)url minimumPlayTime:(NSTimeInterval)minimumPlayTime autoPlay:(BOOL)autoPlay thumbnailImageUrl:(NSURL *)thumbnailImageUrl;
- (void)loadVideoWithPlayer:(AVPlayer *)player minimumPlayTime:(NSTimeInterval)minimumPlayTime autoPlay:(BOOL)autoPlay thumbnailImageUrl:(NSURL *)thumbnailImageUrl;

- (void)becomeVisibleOnScreen;
- (void)becomeInvisibleOnScreen;

- (void)didEnterFullscreen;
- (void)didExitFullscreen;

@end
