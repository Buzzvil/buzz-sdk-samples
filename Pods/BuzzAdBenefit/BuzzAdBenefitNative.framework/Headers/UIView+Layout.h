//
//  UIView+Layout.h
//  BABNative
//
//  Created by Jaehee Ko on 23/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface UIView (Layout)

- (void)edgesToContainer:(UIView *)container;
- (void)edgesToContainer:(UIView *)container withEqualMargin:(CGFloat)margin;
- (void)edgesToContainer:(UIView *)container withMargin:(UIEdgeInsets)margin;

@end

NS_ASSUME_NONNULL_END
