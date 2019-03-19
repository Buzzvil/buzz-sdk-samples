//
//  BABAdTracker.h
//  BAB
//
//  Created by Jaehee Ko on 22/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@class BABAd;

@interface BABAdTracker : NSObject

- (void)adImpressed:(BABAd *)ad;
- (void)adClicked:(BABAd *)ad;
- (void)adParticipated:(BABAd *)ad;
- (void)videoAd:(BABAd *)ad playedForSeconds:(NSTimeInterval)seconds;

@end

NS_ASSUME_NONNULL_END
