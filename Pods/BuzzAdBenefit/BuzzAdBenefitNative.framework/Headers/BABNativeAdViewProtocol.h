//
//  BABNativeAdViewProtocol.h
//  BAB
//
//  Created by Jaehee Ko on 22/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

@class BuzzMediaView;

@protocol BABNativeAdViewProtocol <NSObject>

- (void)renderImageMediaAtUrl:(NSURL *)url;
- (void)renderVideoMediaAtUrl:(NSURL *)url minimumPlayTime:(NSTimeInterval)minimumPlayTime autoPlay:(BOOL)autoPlay thumbnailImageUrl:(NSURL *)url;

- (void)switchToFullscreen;

- (void)handleAdImpression;
- (void)handleAdClick;
- (void)handleAdParticipation;

@end
