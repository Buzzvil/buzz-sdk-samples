//
//  BABFullscreenTransition.h
//  BABNative
//
//  Created by Jaehee Ko on 23/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface BABFullscreenTransition : NSObject <UIViewControllerTransitioningDelegate>

@property (nonatomic, readonly) BOOL enableBackgroundBlur;

- (instancetype)initWithEnableBackgroundBlur:(BOOL)enableBackgroundBlur;

@end

NS_ASSUME_NONNULL_END
