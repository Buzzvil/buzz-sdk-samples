//
//  BABReadyMadeNativeAdView.h
//  BABNative
//
//  Created by Jaehee Ko on 21/02/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BABReadyMadeNativeAdViewConfig.h"

@class BABAd;

NS_ASSUME_NONNULL_BEGIN

@interface BABReadyMadeNativeAdView : UIView

- (void)renderAd:(BABAd *)ad withConfig:(nullable BABReadyMadeNativeAdViewConfig *)config;

@end

NS_ASSUME_NONNULL_END
