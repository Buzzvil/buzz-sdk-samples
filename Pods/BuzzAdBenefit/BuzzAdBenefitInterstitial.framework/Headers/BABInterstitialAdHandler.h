//
//  BABInterstitialAdHandler.h
//  BABInterstitial
//
//  Created by Jaehee Ko on 21/02/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

@class BABInterstitialConfig;
@class BABInterstitialAdHandler;

NS_ASSUME_NONNULL_BEGIN

typedef enum {
  BABInterstitialDialog,
  BABInterstitialBottomSheet
} BABInterstitialType;

@protocol BABInterstitialAdHandlerDelegate <NSObject>
- (void)BABInterstitialAdHandlerDidFailToLoadAd:(BABInterstitialAdHandler *)adLoader;
- (void)BABInterstitialAdHandlerDidSucceedLoadingAd:(BABInterstitialAdHandler *)adLoader;
@end

@interface BABInterstitialAdHandler : NSObject

@property (nonatomic, weak) id<BABInterstitialAdHandlerDelegate> delegate;

- (instancetype)initWithUnitId:(NSString *)unitId type:(BABInterstitialType)type;
- (void)show:(UIViewController *)presentingViewController withConfig:(nullable BABInterstitialConfig *)config;

@end

NS_ASSUME_NONNULL_END
