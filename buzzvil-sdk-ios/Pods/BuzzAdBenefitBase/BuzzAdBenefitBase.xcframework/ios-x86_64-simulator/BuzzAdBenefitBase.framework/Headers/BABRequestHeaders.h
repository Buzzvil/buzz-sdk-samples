//
//  BABRequestHeader.h
//  BAB
//
//  Created by Jaehee Ko on 01/07/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface BABRequestHeaders : NSObject

+ (NSDictionary<NSString *, NSString *> *)defaultHeaders;

@end

NS_ASSUME_NONNULL_END
