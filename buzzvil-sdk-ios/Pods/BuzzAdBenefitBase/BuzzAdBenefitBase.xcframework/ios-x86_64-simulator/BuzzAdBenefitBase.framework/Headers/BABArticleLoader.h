@import Foundation;
#import "BABArticle.h"

@class BABError;

NS_ASSUME_NONNULL_BEGIN

@interface BABArticleLoader : NSObject

- (instancetype)initWithUnitId:(NSString *)unitId;

- (void)loadArticlesWithSize:(NSUInteger)size
                  categories:(NSArray<BABArticleCategoryName> *)categories
                   onSuccess:(void (^)(NSArray<BABArticle *> *articles))onSuccess
                   onFailure:(void (^)(BABError *error))onFailure;

- (void)resetCursor;

@end

NS_ASSUME_NONNULL_END
