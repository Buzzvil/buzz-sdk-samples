//
//  BABUserPreference.h
//  BAB
//
//  Created by Jaehee Ko on 18/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSUInteger, BABVideoAutoPlayType) {
  BABVideoAutoPlayNotSet = 0,
  BABVideoAutoPlayDisabled,
  BABVideoAutoPlayEnabled,
  BABVideoAutoPlayOnWifi
} ;

@interface BABUserPreference : NSObject

- (instancetype)initWithAutoPlayType:(BABVideoAutoPlayType)autoplayType;

@property (nonatomic, readonly) BABVideoAutoPlayType autoplayType;

@end

NS_ASSUME_NONNULL_END
