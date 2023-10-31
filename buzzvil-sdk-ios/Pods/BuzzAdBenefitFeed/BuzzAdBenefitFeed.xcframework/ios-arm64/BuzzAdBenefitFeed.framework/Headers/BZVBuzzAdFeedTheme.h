@import UIKit;

#import <BuzzAdBenefitBase/BZVTheme.h>
#import <BuzzAdBenefitBase/BZVControlStateResource.h>

@class BZVBuzzAdFeedThemeBuilder;

NS_ASSUME_NONNULL_BEGIN

typedef void (^BZVBuzzAdFeedThemeBuilderBlock)(BZVBuzzAdFeedThemeBuilder *builder);

@interface BZVBuzzAdFeedTheme : BZVTheme

@property (nonatomic, strong, readonly) UIColor *feedBackgroundColor;
@property (nonatomic, strong, readonly) UIColor *tabBackgroundColor;
@property (nonatomic, strong, readonly) BZVControlStateResource<UIColor *> *tabTextColor;
@property (nonatomic, strong, readonly) UIColor *tabIndicatorColor DEPRECATED_MSG_ATTRIBUTE("Use tabIndicatorColors instead.");
@property (nonatomic, strong, readonly) BZVControlStateResource<UIColor *> *tabIndicatorColors;
@property (nonatomic, strong, readonly) BZVControlStateResource<UIColor *> *filterBackgroundColor;
@property (nonatomic, strong, readonly) BZVControlStateResource<UIColor *> *filterTextColor;
@property (nonatomic, strong, readonly) UIColor *separatorColor;
@property (nonatomic, assign, readonly) CGFloat separatorHeight;
@property (nonatomic, assign, readonly) CGFloat separatorHorizontalMargin;

@end

@interface BZVBuzzAdFeedTheme (Builder)

+ (instancetype)themeWithBlock:(BZVBuzzAdFeedThemeBuilderBlock)block;

@end

@interface BZVBuzzAdFeedThemeBuilder : BZVThemeBuilder<BZVBuzzAdFeedTheme *>

@property (nonatomic, strong, readwrite) UIColor *feedBackgroundColor;
@property (nonatomic, strong, readwrite) UIColor *tabBackgroundColor;
@property (nonatomic, strong, readwrite) BZVControlStateResource<UIColor *> *tabTextColor;
@property (nonatomic, strong, readwrite) UIColor *tabIndicatorColor DEPRECATED_MSG_ATTRIBUTE("Use tabIndicatorColors instead.");
@property (nonatomic, strong, readwrite) BZVControlStateResource<UIColor *> *tabIndicatorColors;
@property (nonatomic, strong, readwrite) BZVControlStateResource<UIColor *> *filterBackgroundColor;
@property (nonatomic, strong, readwrite) BZVControlStateResource<UIColor *> *filterTextColor;
@property (nonatomic, strong, readwrite) UIColor *separatorColor;
@property (nonatomic, assign, readwrite) CGFloat separatorHeight;
@property (nonatomic, assign, readwrite) CGFloat separatorHorizontalMargin;

@end

NS_ASSUME_NONNULL_END
