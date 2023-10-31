#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/** An object that holds data corresponding to the companion ad. */
@interface IMACompanionAd : NSObject

/** The value for the resource of this companion. */
@property(nonatomic, copy, readonly, nullable) NSString *resourceValue;

/** The API needed to execute this ad, or nil if unavailable. */
@property(nonatomic, copy, readonly, nullable) NSString *APIFramework;

/** The width of the companion in pixels. 0 if unavailable. */
@property(nonatomic, readonly) NSInteger width;

/** The height of the companion in pixels. 0 if unavailable. */
@property(nonatomic, readonly) NSInteger height;

/**
 * Obtain an instance from <code>IMAAd.companionAds</code>.
 * :nodoc:
 */
- (nonnull instancetype)init NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END
