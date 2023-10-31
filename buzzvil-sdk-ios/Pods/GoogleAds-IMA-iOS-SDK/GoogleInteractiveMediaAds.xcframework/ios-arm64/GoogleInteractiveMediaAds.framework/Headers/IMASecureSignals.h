#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

// Stores the signal data for SecureSignals. Currently used to store signal for PCS only.
@interface IMASecureSignals : NSObject

/**
 * Secure Signal with custom data sent with ads request. Secure Signal with custom
 * data is an encrypted blob containing signals collected by the publisher and previously agreed
 * upon by the publisher and bidder。
 */

@property(nonatomic, copy, readonly) NSString *customData;

- (instancetype)initWithCustomData:(NSString *)customData NS_DESIGNATED_INITIALIZER;

/**
 * :nodoc:
 */
- (instancetype)init NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END
