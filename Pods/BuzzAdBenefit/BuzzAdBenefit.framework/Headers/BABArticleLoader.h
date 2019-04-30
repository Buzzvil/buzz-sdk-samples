//
//  BABArticleLoader.h
//  BAB
//
//  Created by Jaehee Ko on 18/03/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BABArticle.h"

NS_ASSUME_NONNULL_BEGIN

@interface BABArticleLoader : NSObject

@property (nonatomic, strong, nullable) NSArray<BABArticleCategoryName> *categories;
@property (nonatomic, readonly) BOOL isLoading;

- (void)loadArticlesWithSize:(NSUInteger)size onSuccess:(void (^)(NSArray<BABArticle *> * _Nonnull articles))onSuccess onFailure:(void (^)(NSError * _Nullable error))onFailure;
- (void)loadNextArticlesWithSize:(NSUInteger)size onSuccess:(void (^)(NSArray<BABArticle *> * _Nonnull articles))onSuccess onFailure:(void (^)(NSError * _Nullable error))onFailure;

@end

NS_ASSUME_NONNULL_END
