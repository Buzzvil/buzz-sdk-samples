//
//  BABRewardHandler.h
//  BAB
//
//  Created by Jaehee Ko on 23/04/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BABRewardResult.h"

@class BABAd;

NS_ASSUME_NONNULL_BEGIN

@interface BABRewardHandler : NSObject

- (void)requestRewardForAd:(BABAd *)ad
                  clickUrl:(NSString *)clickUrl
                   onStart:(void (^)(void))onStart
                onComplete:(void (^)(BABRewardResult result))onComplete;

@end

NS_ASSUME_NONNULL_END
