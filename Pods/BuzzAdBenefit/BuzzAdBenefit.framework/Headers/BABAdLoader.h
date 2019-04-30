//
//  BABAdLoader.h
//  BAB
//
//  Created by Jaehee Ko on 17/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BABAd.h"

NS_ASSUME_NONNULL_BEGIN

@interface BABAdLoader : NSObject

@property (nonatomic, copy, readonly) NSString *unitId;
@property (nonatomic, copy, readonly) NSDictionary *settings;
@property (nonatomic, readonly) BOOL isLoading;

- (instancetype)initWithUnitId:(NSString *)unitId;
- (void)loadAdWithOnSuccess:(void (^)(BABAd * _Nonnull ad))onSuccess onFailure:(void (^)(NSError * _Nullable error))onFailure;
- (void)loadAdsWithSize:(NSUInteger)size onSuccess:(void (^)(NSArray<BABAd *> * _Nonnull ads))onSuccess onFailure:(void (^)(NSError * _Nullable error))onFailure;
- (void)loadNextAdsWithSize:(NSUInteger)size onSuccess:(void (^)(NSArray<BABAd *> * _Nonnull ads))onSuccess onFailure:(void (^)(NSError * _Nullable error))onFailure;

@end

NS_ASSUME_NONNULL_END
