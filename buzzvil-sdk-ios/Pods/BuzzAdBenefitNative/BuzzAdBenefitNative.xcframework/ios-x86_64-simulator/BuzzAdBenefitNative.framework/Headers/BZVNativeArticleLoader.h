@import Foundation;

@class BZVNativeArticle;
@class BZVNativeArticlesRequest;

NS_ASSUME_NONNULL_BEGIN

@interface BZVNativeArticleLoader : NSObject

+ (instancetype)loaderWithUnitId:(NSString *)unitId;

- (void)loadArticlesWithRequest:(BZVNativeArticlesRequest *)request
                      onSuccess:(void (^)(NSArray<BZVNativeArticle *> *nativeArticles))onSuccess
                      onFailure:(void (^)(NSError *error))onFailure;

- (void)resetCursor;

@end

NS_ASSUME_NONNULL_END
