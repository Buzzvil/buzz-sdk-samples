//
//  BABConfig.h
//  BAB
//
//  Created by Jaehee Ko on 17/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef enum {
  BABEnvTest = 0,
  BABEnvProduction
} BABEnv;

@interface BABConfig : NSObject

@property (nonatomic, assign, readonly) BABEnv environment;
@property (nonatomic, assign, readonly) BOOL logging;
@property (nonatomic, copy, readonly) NSString *appId;

- (instancetype)initWithAppId:(NSString *)appId;
- (instancetype)initWithAppId:(NSString *)appId environment:(BABEnv)environment logging:(BOOL)logging;

@end

NS_ASSUME_NONNULL_END

