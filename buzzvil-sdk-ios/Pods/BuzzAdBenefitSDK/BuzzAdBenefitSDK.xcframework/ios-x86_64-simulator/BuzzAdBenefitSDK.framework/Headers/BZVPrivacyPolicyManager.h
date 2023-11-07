@import UIKit;

NS_ASSUME_NONNULL_BEGIN

@interface BZVPrivacyPolicyManager : NSObject

- (instancetype)init NS_UNAVAILABLE;

/// Checks whether privacy policy consent is granted.
///
/// @see grantConsent
/// @see revokeConsent
/// @see presentConsentFormOnViewController:onComplete:
/// @return YES if consent is granted by above APIs,
///         NO otherwise.
- (BOOL)isConsentGranted;

/// Grants privacy policy consent.
- (void)grantConsent;

/// Revokes privacy policy consent.
- (void)revokeConsent;

/// Presents privacy policy consent form to the user.
/// The user's response (i.e. agreement or disagreement) will be persisted across app launches.
///
/// @param viewController The view controller on which the consent form will be presented.
/// @param onComplete     The block to execute when the consent form completes. This block has no return value and
///                       takes one argument: the boolean reperesenting if the user granted consent. You may specify
///                       nil for this parameter.
- (void)presentConsentFormOnViewController:(UIViewController *)viewController
                                onComplete:(nullable void (^)(BOOL accepted))onComplete;

@end

NS_ASSUME_NONNULL_END
