@import UIKit;

@class BZVNativeArticle;
@class BZVNativeArticleView;

NS_ASSUME_NONNULL_BEGIN

@protocol BZVNativeArticleViewDelegate <NSObject>
- (void)BZVNativeArticleView:(BZVNativeArticleView *)articleView didImpressArticle:(BZVNativeArticle *)article;
- (void)BZVNativeArticleView:(BZVNativeArticleView *)articleView didClickArticle:(BZVNativeArticle *)article;
@end

@interface BZVNativeArticleView : UIView

@property (nonatomic, weak, readwrite, nullable) id<BZVNativeArticleViewDelegate> delegate;

@end

NS_ASSUME_NONNULL_END
