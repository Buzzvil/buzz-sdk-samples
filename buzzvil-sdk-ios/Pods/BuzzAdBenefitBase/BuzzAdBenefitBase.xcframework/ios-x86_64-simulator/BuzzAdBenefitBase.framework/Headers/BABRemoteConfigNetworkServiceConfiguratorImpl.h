@import Foundation;

#import "BABRemoteConfigNetworkServiceConfigurator.h"

NS_ASSUME_NONNULL_BEGIN

@interface BABRemoteConfigNetworkServiceConfiguratorImpl : NSObject <BABRemoteConfigNetworkServiceConfigurator>

- (instancetype)initWithBaseUrl:(NSString *)baseUrl;

@end

NS_ASSUME_NONNULL_END
