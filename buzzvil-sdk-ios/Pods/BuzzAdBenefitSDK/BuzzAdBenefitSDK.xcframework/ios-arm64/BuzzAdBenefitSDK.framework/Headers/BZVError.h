@import Foundation;

NS_ASSUME_NONNULL_BEGIN

extern NSString * const BZVErrorDomain;

typedef NS_ERROR_ENUM(BZVErrorDomain, BZVErrorCode) {
  BZVUnkwounError,
  BZVServerError,
  BZVInvalidRequest,
  BZVRequestTimeout,
  BZVEmptyResponse,
  BZVWaitingForResponse,
  BZVAgeUnsatisfied,
  BZVPrivacyPolicyDisagreed,
  BZVNetworkDisconnected,
};

@interface NSError (BZVError)

+ (NSError *)bzvUnknownError;
+ (NSError *)bzvServerError;
+ (NSError *)bzvInvalidRequest;
+ (NSError *)bzvRequestTimeout;
+ (NSError *)bzvEmptyResponse;
+ (NSError *)bzvWaitingForResponse;
+ (NSError *)bzvAgeUnsatisfied;
+ (NSError *)bzvPrivacyPolicyDisagreed;
+ (NSError *)bzvNetworkDisconnected;

@end

NS_ASSUME_NONNULL_END
