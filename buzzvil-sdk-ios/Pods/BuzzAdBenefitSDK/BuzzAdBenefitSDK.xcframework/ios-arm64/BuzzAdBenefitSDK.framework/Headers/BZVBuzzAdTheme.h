#import <Foundation/Foundation.h>
#import "BZVTheme.h"

@class BZVBuzzAdThemeBuilder;

NS_ASSUME_NONNULL_BEGIN

typedef void (^BZVBuzzAdThemeBuilderBlock)(BZVBuzzAdThemeBuilder *builder);

@interface BZVBuzzAdTheme : BZVTheme

+ (void)setGlobalTheme:(BZVBuzzAdTheme *)theme;
+ (BZVBuzzAdTheme *)globalTheme;

@end

@interface BZVBuzzAdTheme (Builder)

+ (instancetype)themeWithBlock:(BZVBuzzAdThemeBuilderBlock)block;

@end

@interface BZVBuzzAdThemeBuilder : BZVThemeBuilder<BZVBuzzAdTheme *>

@end

NS_ASSUME_NONNULL_END
