//
//  BABCategory.h
//  BAB
//
//  Created by Jaehee Ko on 18/03/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface BABCategory : NSObject

@property (nonatomic, copy, readonly) NSString *Id;
@property (nonatomic, copy, readonly) NSString *name;
@property (nonatomic, copy, readonly) NSString *translation;
@property (nonatomic, copy, readonly) NSString *iconUrl;

- (instancetype)initWithDictionary:(NSDictionary *)dic;

@end

NS_ASSUME_NONNULL_END
