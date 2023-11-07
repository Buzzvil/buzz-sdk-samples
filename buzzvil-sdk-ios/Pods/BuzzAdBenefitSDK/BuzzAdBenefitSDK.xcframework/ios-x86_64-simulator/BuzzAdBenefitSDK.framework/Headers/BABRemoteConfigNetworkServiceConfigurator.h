@import Foundation;

NS_ASSUME_NONNULL_BEGIN

@protocol BABRemoteConfigNetworkServiceConfigurator

- (NSString *)getBaseURL;
- (NSDictionary<NSString *, NSString *> *)getHeaders;

@end

NS_ASSUME_NONNULL_END
