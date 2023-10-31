#import <UIKit/UIKit.h>

@class IMAVersion;

NS_ASSUME_NONNULL_BEGIN

/** Completion handler for signal generation. Returns either signals or an error object. */
typedef void (^IMASignalCompletionHandler)(NSString *_Nullable signals, NSError *_Nullable error);

/** Adapter that provides secure signal(3rd party signal only) to the IMA SDK to be included in an
 * auction. */
@protocol IMASecureSignalsAdapter <NSObject>

/** Initializes the Secure Signal adapter. */
- (nullable instancetype)init;

/** The version of the adapter. */
+ (IMAVersion *)adapterVersion;

/** The version of the ad SDK. */
+ (IMAVersion *)adSDKVersion;

/**
 * Asks the receiver for encrypted signals. Signals are provided to the 3PAS at request time. The
 * receiver must call @c completionHandler with signals or an error.
 * This method is called on a non-main thread. The receiver should avoid using the main thread to
 * prevent signal collection timeouts.
 * @param completion The block to call when signal collection is complete.
 */
- (void)collectSignalsWithCompletion:(IMASignalCompletionHandler)completion;

@end

NS_ASSUME_NONNULL_END
