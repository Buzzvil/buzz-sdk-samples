//
//  BABVideoAdMetadataLoader.h
//  BAB
//
//  Created by Jaehee Ko on 17/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@class BABCreative;

@interface BABVideoAdMetadataLoader : NSObject

- (instancetype)initWithVideoCreative:(BABCreative *)videoCreative;
- (void)loadMetadataWithOnSuccess:(void (^)(void))onSuccess onFailure:(void (^)(NSError * _Nullable error))onFailure;

@end

NS_ASSUME_NONNULL_END
