//
//  BABAd.h
//  BAB
//
//  Created by Jaehee Ko on 20/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BABCreative.h"
#import "BABTrackable.h"

NS_ASSUME_NONNULL_BEGIN

@interface BABAd : NSObject <BABTrackable>

@property (nonatomic, copy, readonly) NSString *Id;
@property (nonatomic, assign, readonly) NSUInteger ttl;
@property (nonatomic, assign, readonly) double landingReward;
@property (nonatomic, assign, readonly) double actionReward;
@property (nonatomic, copy, readonly) NSString *payload;
@property (nonatomic, copy, readonly) NSDictionary *extra;
@property (nonatomic, copy, readonly, nullable) NSString *preferredBrowser;
@property (nonatomic, strong, readonly) BABCreative *creative;

@property (nonatomic, readonly) double reward;
@property (nonatomic, readonly) NSTimeInterval minimumPlayTimeForReward;

@property (nonatomic, strong, readonly, nullable) NSDate *fetchedAt;

- (instancetype)initWithDictionary:(NSDictionary *)dic fetchedAt:(nullable NSDate *)fetchedAt;

@end

NS_ASSUME_NONNULL_END
