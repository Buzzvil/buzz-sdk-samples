//
//  BABStateValue.h
//  BABNative
//
//  Created by Jaehee Ko on 25/02/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface BABStateValue<T> : NSObject
@property (nonatomic, strong) T enabled;
@property (nonatomic, strong) T disabled;

- (instancetype)initWithEnabled:(T)enabled disabled:(T)disabled;
@end

NS_ASSUME_NONNULL_END
