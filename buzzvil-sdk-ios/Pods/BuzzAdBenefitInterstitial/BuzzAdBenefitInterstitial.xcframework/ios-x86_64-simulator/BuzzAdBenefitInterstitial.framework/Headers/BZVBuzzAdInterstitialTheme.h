@import UIKit;
@import BuzzAdBenefitBase.BZVTheme;

@class BZVBuzzAdInterstitialThemeBuilder;

NS_ASSUME_NONNULL_BEGIN

typedef void (^BZVBuzzAdInterstitialThemeBuilderBlock)(BZVBuzzAdInterstitialThemeBuilder *builder);

@interface BZVBuzzAdInterstitialTheme : BZVTheme

@property (nonatomic, strong, readonly) UIColor *backgroundColor;
@property (nonatomic, strong, readonly) UIColor *textColor;

@end

@interface BZVBuzzAdInterstitialTheme (Builder)

+ (instancetype)themeWithBlock:(BZVBuzzAdInterstitialThemeBuilderBlock)block;

@end

@interface BZVBuzzAdInterstitialThemeBuilder : BZVThemeBuilder<BZVBuzzAdInterstitialTheme *>

@property (nonatomic, strong, readwrite) UIColor *backgroundColor;
@property (nonatomic, strong, readwrite) UIColor *textColor;

- (BZVBuzzAdInterstitialTheme *)build;

@end

NS_ASSUME_NONNULL_END
