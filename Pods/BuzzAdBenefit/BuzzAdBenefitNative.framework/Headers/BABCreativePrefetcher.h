//
//  BABCreativePrefetcher.h
//  BABNative
//
//  Created by Jaehee Ko on 01/04/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

@class BABCreative;

NS_ASSUME_NONNULL_BEGIN

@interface BABCreativePrefetcher : NSObject

- (instancetype)initWithCreatives:(NSArray<BABCreative *> *)creatives;
- (void)prefetch;

@end

NS_ASSUME_NONNULL_END
