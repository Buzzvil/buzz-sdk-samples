//
//  BABNativeAdView.h
//  BABNative
//
//  Created by Jaehee Ko on 20/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BABNativeAdViewProtocol.h"
#import "BuzzImpressionTrackableView.h"

NS_ASSUME_NONNULL_BEGIN

@class BABAd;
@class BABNativeAdView;
@class BABMediaView;

@protocol BABNativeAdViewDelegate <NSObject>
- (UIViewController *)BABNativeAdViewViewControlleForPresentingFullscreen;
- (void)BABNativeAdView:(BABNativeAdView *)adView didImpressAd:(BABAd *)ad;
- (void)BABNativeAdView:(BABNativeAdView *)adView didClickAd:(BABAd *)ad;
- (void)BABNativeAdView:(BABNativeAdView *)adView didParticipateAd:(BABAd *)ad;
@end

@interface BABNativeAdView : UIView <BABNativeAdViewProtocol, BuzzImpressionTrackableView>

@property (nonatomic, weak) id<BABNativeAdViewDelegate> delegate;
@property (nonatomic, strong) BABMediaView *mediaView;
@property (nonatomic, strong) NSArray<UIView *> *clickableViews;
@property (nonatomic, strong) BABAd *ad;

@end

NS_ASSUME_NONNULL_END
