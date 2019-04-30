//
//  BABFeedHandler.h
//  BABFeed
//
//  Created by Jaehee Ko on 07/03/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BABFeedViewController.h"

@class BABFeedConfig;

NS_ASSUME_NONNULL_BEGIN

@interface BABFeedHandler : NSObject

@property (nonatomic, readonly) NSUInteger adsCount;
@property (nonatomic, readonly) double availableReward;

- (instancetype)initWithConfig:(BABFeedConfig *)config;
- (void)preloadWithOnSuccess:(void (^)(void))onSuccess onFailure:(void (^)(NSError *))onFailure;
- (BABFeedViewController *)populateViewController;

@end

NS_ASSUME_NONNULL_END
