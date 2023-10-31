#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface IMAVersion : NSObject
/** Major version. */
@property(nonatomic) NSInteger majorVersion;
/** Minor version. */
@property(nonatomic) NSInteger minorVersion;
/** Patch version. */
@property(nonatomic) NSInteger patchVersion;
@end

NS_ASSUME_NONNULL_END
