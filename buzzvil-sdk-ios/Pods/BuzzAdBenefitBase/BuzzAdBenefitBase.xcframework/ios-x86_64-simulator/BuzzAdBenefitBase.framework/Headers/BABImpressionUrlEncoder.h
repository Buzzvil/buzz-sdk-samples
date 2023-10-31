@import Foundation;

NS_ASSUME_NONNULL_BEGIN

@interface BABImpressionUrlEncoder : NSObject

- (NSString *)encodeRawImpressionUrl:(NSString *)rawImpressionUrl
                           sessionId:(nullable NSString *)sessionId;

@end

NS_ASSUME_NONNULL_END
