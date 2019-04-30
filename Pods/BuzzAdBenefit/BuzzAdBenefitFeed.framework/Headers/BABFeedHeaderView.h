//
//  BABFeedHeaderView.h
//  BABFeed
//
//  Created by Jaehee Ko on 21/03/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface BABFeedHeaderView : UIView

+ (CGFloat)desiredHeight;
- (void)availableRewardDidUpdate:(double)reward;

@end

NS_ASSUME_NONNULL_END
