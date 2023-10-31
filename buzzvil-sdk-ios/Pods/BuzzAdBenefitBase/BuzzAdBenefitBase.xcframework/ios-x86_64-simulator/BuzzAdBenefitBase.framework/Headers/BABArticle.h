//
//  BABArticle.h
//  BAB
//
//  Created by Jaehee Ko on 18/03/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BABCreative.h"
#import "BABCategory.h"
#import "BABChannel.h"
#import "BABTrackable.h"
#import "BuzzEvent.h"

NS_ASSUME_NONNULL_BEGIN

typedef NSString *BABArticleCategoryName;

static const BABArticleCategoryName BABArticleCategoryAnimal = @"animal";
static const BABArticleCategoryName BABArticleCategoryBusiness = @"business";
static const BABArticleCategoryName BABArticleCategoryEntertainment = @"entertainment";
static const BABArticleCategoryName BABArticleCategoryFashion = @"fashion";
static const BABArticleCategoryName BABArticleCategoryFood = @"food";
static const BABArticleCategoryName BABArticleCategoryFun = @"fun";
static const BABArticleCategoryName BABArticleCategoryGame = @"game";
static const BABArticleCategoryName BABArticleCategoryHealth = @"health";
static const BABArticleCategoryName BABArticleCategoryLifestyle = @"lifestyle";
static const BABArticleCategoryName BABArticleCategoryNews = @"news";
static const BABArticleCategoryName BABArticleCategoryRelationship = @"relationship";
static const BABArticleCategoryName BABArticleCategorySports = @"sports";
static const BABArticleCategoryName BABArticleCategoryTechnology = @"technology";
static const BABArticleCategoryName BABArticleCategoryAll = @"";

@interface BABArticle : NSObject <BABTrackable>

@property (nonatomic, copy, readonly) NSString *Id;
@property (nonatomic, copy, readonly) NSString *payload;
@property (nonatomic, copy, readonly) NSDictionary *extra;
@property (nonatomic, copy, readonly) NSString *sourceUrl;
@property (nonatomic, strong, readonly) NSDate *createdAt;

@property (nonatomic, readonly) NSArray<BuzzEvent *> *events;
@property (nonatomic, readonly) NSDictionary *eventMap;

@property (nonatomic, strong, readonly) BABCreative *creative;
@property (nonatomic, strong, readonly) BABCategory *category;
@property (nonatomic, strong, readonly) BABChannel *channel;

- (instancetype)initWithDictionary:(NSDictionary *)dic;

- (BuzzEvent *)getEventWithType:(BuzzEventType)type;
- (BOOL)hasRewardForEventType:(BuzzEventType)type;

@end

NS_ASSUME_NONNULL_END
