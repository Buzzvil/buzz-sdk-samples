@import Foundation;

@class RACSignal<__covariant ValueType>;

NS_ASSUME_NONNULL_BEGIN

@protocol BZVNativeAdEventSessionProvider <NSObject>

- (RACSignal<NSString *> *)getSessionId;

@end

NS_ASSUME_NONNULL_END
