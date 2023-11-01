//
//  BABFullscreenVideoViewController.h
//  BABNative
//
//  Created by Jaehee Ko on 21/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@protocol BuzzPlayerProtocol;
@class AVPlayer;
@class BABAd;
@class BABFullscreenVideoViewController;

@protocol BABFullscreenVideoViewControllerDelegate <NSObject>
- (void)fullscreenVideoViewControllerDidDismiss:(BABFullscreenVideoViewController *)fullscreenVideoViewController currentFullScreenVideoAd:(BABAd *)currentFullScreenVideoAd;
- (void)fullscreenVideoViewControllerLearnMoreButtonTapped:(BABFullscreenVideoViewController *)fullscreenViewViewController;
@end

@interface BABFullscreenVideoViewController : UIViewController

@property (nonatomic, weak) id<BABFullscreenVideoViewControllerDelegate> delegate;

- (instancetype)initWithBuzzPlayer:(id<BuzzPlayerProtocol>)player ad:(BABAd *)ad autoplay:(BOOL)autoplay;

@end

NS_ASSUME_NONNULL_END