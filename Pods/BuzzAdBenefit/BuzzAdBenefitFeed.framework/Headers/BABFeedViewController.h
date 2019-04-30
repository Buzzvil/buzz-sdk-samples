//
//  BABFeedViewController.h
//  BABFeed
//
//  Created by Jaehee Ko on 07/03/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

@class BABFeedHeaderView;
@class BABFeedDataSource;

NS_ASSUME_NONNULL_BEGIN

@interface BABFeedViewController : UIViewController

@property (nonatomic, strong) BABFeedDataSource *dataSource;
@property (nonatomic, strong) Class adViewHolderClass;
@property (nonatomic, strong) Class articleViewHolderClass;
@property (nonatomic, strong) Class headerViewClass;

@end

NS_ASSUME_NONNULL_END
