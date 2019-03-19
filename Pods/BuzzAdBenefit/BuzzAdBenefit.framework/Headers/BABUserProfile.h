//
//  BABUserProfile.h
//  BAB
//
//  Created by Jaehee Ko on 17/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BABUserProfile.h"

NS_ASSUME_NONNULL_BEGIN

@class BABSession;

typedef enum {
  BABUserGenderUnknown = 0,
  BABUserGenderMale,
  BABUserGenderFemale
} BABUserGender;

@interface BABUserProfile : NSObject

@property (nonatomic, copy, readonly) NSString *userId;
@property (nonatomic, assign, readonly) BABUserGender gender;
@property (nonatomic, assign, readonly) NSUInteger birthYear;
@property (nonatomic, copy, readonly, nullable) NSString *region;
@property (nonatomic, copy) NSString *adId;
@property (nonatomic, strong) BABSession *session;

- (instancetype)initWithUserId:(NSString *)userId;
- (instancetype)initWithUserId:(NSString *)userId birthYear:(NSUInteger)birthYear gender:(BABUserGender)gender;
- (NSDictionary *)toDictionary;
- (BOOL)isSessionRegistered;

@end

NS_ASSUME_NONNULL_END
