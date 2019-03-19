//
//  BABReadyMadeNativeAdViewConfig.h
//  BABNative
//
//  Created by Jaehee Ko on 26/02/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BABStateValue.h"

NS_ASSUME_NONNULL_BEGIN

@interface BABReadyMadeNativeAdViewConfig : NSObject

@property (nonatomic, strong) BABStateValue<UIImage *> *ctaViewIcon;
@property (nonatomic, strong) BABStateValue<UIColor *> *ctaViewBackgroundColor;
@property (nonatomic, strong) BABStateValue<UIColor *> *ctaViewTextColor;

@property (nonatomic, assign) CGFloat detailViewPadding;
@property (nonatomic, assign) CGFloat mediaViewCornerRadius;

@end

NS_ASSUME_NONNULL_END
