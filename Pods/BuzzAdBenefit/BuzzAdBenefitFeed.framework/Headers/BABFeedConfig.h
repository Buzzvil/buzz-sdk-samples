//
//  BABFeedConfig.h
//  BABFeed
//
//  Created by Jaehee Ko on 07/03/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <BuzzAdBenefit/BABArticle.h>

@class BABFeedHeaderView;

NS_ASSUME_NONNULL_BEGIN

@interface BABFeedConfig : NSObject

@property (nonatomic, copy, readonly) NSString *unitId;
@property (nonatomic, copy) NSString *title;
@property (nonatomic, strong) Class headerViewClass;
@property (nonatomic, strong) Class adViewHolderClass;
@property (nonatomic, strong) Class articleViewHolderClass;
@property (nonatomic, assign) BOOL articlesEnabled;
@property (nonatomic, strong, nullable) NSArray<BABArticleCategoryName> *articleCategories;

- (instancetype)initWithUnitId:(NSString *)unitId;

@end

NS_ASSUME_NONNULL_END
