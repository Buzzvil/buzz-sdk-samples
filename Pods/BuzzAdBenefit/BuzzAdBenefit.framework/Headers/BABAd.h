//
//  BABAd.h
//  BAB
//
//  Created by Jaehee Ko on 20/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BABCreative.h"

NS_ASSUME_NONNULL_BEGIN

@interface BABAd : NSObject

@property (nonatomic, copy, readonly) NSString *Id;
@property (nonatomic, assign, readonly) NSUInteger ttl;
@property (nonatomic, strong, readonly, nullable) NSArray<NSString *> *impressionTrackers;
@property (nonatomic, strong, readonly, nullable) NSArray<NSString *> *clickTrackers;
@property (nonatomic, strong, readonly, nullable) NSArray<NSString *> *failTrackers;
@property (nonatomic, assign, readonly) double landingReward;
@property (nonatomic, assign, readonly) double actionReward;
@property (nonatomic, copy, readonly) NSString *payload;
@property (nonatomic, copy, readonly) NSDictionary *extra;
@property (nonatomic, copy, readonly, nullable) NSString *preferredBrowser;
@property (nonatomic, strong, readonly, nonnull) BABCreative *creative;

@property (nonatomic, readonly) double reward;
@property (nonatomic, readonly) NSTimeInterval minimumPlayTimeForReward;

@property (nonatomic, strong, readonly, nullable) NSDate *fetchedAt;
@property (nonatomic, assign) BOOL isImpressed;
@property (nonatomic, assign) BOOL isClicked;
@property (nonatomic, assign) BOOL isParticipated;

- (instancetype)initWithDictionary:(NSDictionary *)dic fetchedAt:(nullable NSDate *)fetchedAt;

@end

NS_ASSUME_NONNULL_END
