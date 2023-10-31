@import UIKit;

@class BZVNativeArticle;
@class BZVNativeArticleView;
@class BZVNativeArticleViewBinderBuilder;

NS_ASSUME_NONNULL_BEGIN

typedef void (^BZVNativeArticleViewBinderBlock)(BZVNativeArticleViewBinderBuilder *builder);

@interface BZVNativeArticleViewBinder : NSObject

- (void)bindWithNativeArticle:(BZVNativeArticle *)nativeArticle;

@end

@interface BZVNativeArticleViewBinder (Builder)

+ (instancetype)viewBinderWithBlock:(BZVNativeArticleViewBinderBlock)block;

@end

@interface BZVNativeArticleViewBinderBuilder : NSObject

@property (nonatomic, strong, readwrite, nonnull) BZVNativeArticleView *nativeArticleView;
@property (nonatomic, strong, readwrite, nonnull) UIImageView *imageView;
@property (nonatomic, strong, readwrite, nullable) UIImageView *iconImageView;
@property (nonatomic, strong, readwrite, nullable) UILabel *channelLabel;
@property (nonatomic, strong, readwrite, nullable) UILabel *timestampLabel;
@property (nonatomic, strong, readwrite, nullable) UILabel *titleLabel;
@property (nonatomic, strong, readwrite, nullable) UILabel *descriptionLabel;
@property (nonatomic, strong, readwrite, nullable) NSArray<UIView *> *clickableViews;

- (BZVNativeArticleViewBinder *)build;

@end

NS_ASSUME_NONNULL_END
