@import Foundation;

#import "BZVNativeArticleCategory.h"

NS_ASSUME_NONNULL_BEGIN

@interface BZVNativeArticlesRequest : NSObject

@property (nonatomic, assign, readwrite) NSUInteger count;
@property (nonatomic, assign, readwrite) BZVNativeArticleCategory category;

@end

NS_ASSUME_NONNULL_END
