//
//  BABInterstitialAdLoader.h
//  BABInterstitial
//
//  Created by Jaehee Ko on 21/02/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

@class BABInterstitialConfig;
@class BABInterstitialAdLoader;

NS_ASSUME_NONNULL_BEGIN

typedef enum {
  BABInterstitialDialog,
  BABInterstitialBottomSheet
} BABInterstitialType;

@protocol BABInterstitialAdLoaderDelegate <NSObject>
- (void)BABInterstitialAdLoaderDidFailToLoadAd:(BABInterstitialAdLoader *)adLoader;
- (void)BABInterstitialAdLoaderDidSucceedLoadingAd:(BABInterstitialAdLoader *)adLoader;
@end

@interface BABInterstitialAdLoader : NSObject

@property (nonatomic, weak) id<BABInterstitialAdLoaderDelegate> delegate;

- (instancetype)initWithUnitId:(NSString *)unitId type:(BABInterstitialType)type;
- (void)show:(UIViewController *)presentingViewController withConfig:(nullable BABInterstitialConfig *)config;

@end

NS_ASSUME_NONNULL_END
