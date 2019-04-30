//
//  BABDefaultAdViewHolder.h
//  BABNative
//
//  Created by Jaehee Ko on 21/02/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BABAdViewHolder.h"
#import "BABStateValue.h"

@class BABAd;

NS_ASSUME_NONNULL_BEGIN

@interface BABDefaultAdViewHolder : BABAdViewHolder

@property (nonatomic, strong) BABStateValue<UIImage *> *ctaViewIcon;
@property (nonatomic, strong) BABStateValue<UIColor *> *ctaViewBackgroundColor;
@property (nonatomic, strong) BABStateValue<UIColor *> *ctaViewTextColor;

@property (nonatomic, assign) CGFloat detailViewPadding;
@property (nonatomic, assign) CGFloat mediaViewCornerRadius;

@end

NS_ASSUME_NONNULL_END
