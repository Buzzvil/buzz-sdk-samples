//
//  BABVideoAdMetadata.h
//  BAB
//
//  Created by Jaehee Ko on 17/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface BABVideoAdMetadata : NSObject

@property (nonatomic, copy, readonly) NSString *clickId;
@property (nonatomic, copy, readonly) NSString *lineItemId;
@property (nonatomic, copy, readonly) NSString *unitId;
@property (nonatomic, copy, readonly) NSString *playtimeTrackingUrl;
@property (nonatomic, copy, readonly) NSString *landingUrl;
@property (nonatomic, copy, readonly) NSString *postbackUrl;
@property (nonatomic, copy, readonly) NSString *videoId;
@property (nonatomic, copy, readonly) NSString *sessionId;
@property (nonatomic, assign, readonly) NSUInteger minimumTime;
@property (nonatomic, assign, readonly) BOOL shouldShowWithLanding;

- (instancetype)initWithDictionary:(NSDictionary *)dic;
- (NSDictionary *)postbackParams;
- (NSDictionary *)playtimeParams;

@end

NS_ASSUME_NONNULL_END
