@import Foundation;

@class BABRequest;
@class BABVideoAdMetadata;
@class RACSignal<__covariant ValueType>;

NS_ASSUME_NONNULL_BEGIN

@interface BABVideoAdMetadataLoader : NSObject

- (instancetype)init NS_UNAVAILABLE;
- (instancetype)initWithRequest:(BABRequest *)request NS_DESIGNATED_INITIALIZER;

- (RACSignal<BABVideoAdMetadata *> *)loadFromUrl:(NSString *)url;

@end

NS_ASSUME_NONNULL_END
