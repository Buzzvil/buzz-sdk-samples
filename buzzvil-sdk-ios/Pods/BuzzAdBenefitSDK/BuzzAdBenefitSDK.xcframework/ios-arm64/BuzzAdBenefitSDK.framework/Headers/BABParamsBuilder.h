//
//  BABParamsBuilder.h
//  BAB
//
//  Created by Jaehee Ko on 17/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface BABParamsBuilder : NSObject

+ (instancetype)defaultParams;
- (void)merge:(NSDictionary *)params;
- (NSDictionary *)build;

@end

NS_ASSUME_NONNULL_END
