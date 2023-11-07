@import Foundation;

NS_ASSUME_NONNULL_BEGIN

typedef enum {
  BABUnknownError = 0,
  BABServerError,
  BABInvalidRequest,
  BABRequestTimeout,
  BABEmptyResponse,
  BABWaitingForResponse,
  BABAgeUnsatisfied,
  BABPrivacyPolicyDisagreed,
  BABNetworkDisconnected,
} BABErrorCode;

@interface BABError : NSObject

@property (nonatomic, strong, readonly) NSError *rawError;
@property (nonatomic, assign, readonly) NSInteger httpStatusCode;
@property (nonatomic, assign) BABErrorCode code;

- (instancetype)initWithCode:(BABErrorCode)code;
- (instancetype)initWithRawError:(NSError *)rawError httpStatusCode:(NSInteger)httpStatusCode;
+ (instancetype)errorWithRawError:(NSError *)rawError httpStatusCode:(NSInteger)httpStatusCode;

@end

NS_ASSUME_NONNULL_END
