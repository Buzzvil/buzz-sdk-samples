@import Foundation;

NS_ASSUME_NONNULL_BEGIN

@interface BABClickUrlEncoder : NSObject

- (NSString *)encodeRawClickUrl:(NSString *)rawClickUrl
                  campaignExtra:(nullable NSDictionary *)campaignExtra
                campaignPayload:(nullable NSString *)campaignPayload
                            ifv:(nullable NSString *)ifv
                      sessionId:(nullable NSString *)sessionId
                unitDeviceToken:(nullable NSString *)unitDeviceToken;

@end

NS_ASSUME_NONNULL_END
