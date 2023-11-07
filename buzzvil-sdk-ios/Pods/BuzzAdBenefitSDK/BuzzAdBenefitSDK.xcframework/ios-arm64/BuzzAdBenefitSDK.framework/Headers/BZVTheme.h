@import UIKit;

#import "BZVControlStateResource.h"

@class BZVThemeBuilder;

NS_ASSUME_NONNULL_BEGIN

typedef void (^BZVThemeBuilderBlock)(BZVThemeBuilder *builder);

@interface BZVTheme : NSObject

@property (nonatomic, strong, readonly) UIColor *colorPrimary;
@property (nonatomic, strong, readonly) UIColor *colorPrimaryDark;
@property (nonatomic, strong, readonly) UIColor *colorPrimaryLight;
@property (nonatomic, strong, readonly) UIColor *colorPrimaryLighter;
@property (nonatomic, strong, readonly) UIColor *colorPrimaryLightest;
@property (nonatomic, strong, readonly) UIImage *rewardIcon;
@property (nonatomic, strong, readonly) UIImage *participatedIcon;
@property (nonatomic, strong, readonly) BZVControlStateResource<UIColor *> *ctaBackgroundColor;
@property (nonatomic, strong, readonly) BZVControlStateResource<UIColor *> *ctaTextColor;
@property (nonatomic, copy, readonly) NSNumber *ctaTextSize;

+ (instancetype)defaultTheme;
- (instancetype)initWithTheme:(BZVTheme *)theme;

@end

@interface BZVTheme (Builder)

+ (instancetype)themeWithBlock:(BZVThemeBuilderBlock)block;

@end

@interface BZVThemeBuilder<ThemeType: BZVTheme *> : NSObject

@property (nonatomic, strong) UIColor *colorPrimary;
@property (nonatomic, strong) UIColor *colorPrimaryDark;
@property (nonatomic, strong) UIColor *colorPrimaryLight;
@property (nonatomic, strong) UIColor *colorPrimaryLighter;
@property (nonatomic, strong) UIColor *colorPrimaryLightest;
@property (nonatomic, strong, readwrite) UIImage *rewardIcon;
@property (nonatomic, strong, readwrite) UIImage *participatedIcon;
@property (nonatomic, strong, readwrite, nullable) BZVControlStateResource<UIColor *> *ctaBackgroundColor;
@property (nonatomic, strong, readwrite, nullable) BZVControlStateResource<UIColor *> *ctaTextColor;
@property (nonatomic, copy, readwrite, nullable) NSNumber *ctaTextSize;

- (instancetype)initWithTheme:(BZVTheme *)theme;

- (ThemeType)build;

@end

NS_ASSUME_NONNULL_END
