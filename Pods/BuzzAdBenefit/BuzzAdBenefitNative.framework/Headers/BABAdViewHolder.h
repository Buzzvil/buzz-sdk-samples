//
//  BABAdViewHolder.h
//  BABNative
//
//  Created by Jaehee Ko on 25/03/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

@class BABAd;

NS_ASSUME_NONNULL_BEGIN

@interface BABAdViewHolder : UIView

@property (nonatomic, readonly) BABAd *ad;

- (void)renderAd:(BABAd *)ad;

@end

NS_ASSUME_NONNULL_END
