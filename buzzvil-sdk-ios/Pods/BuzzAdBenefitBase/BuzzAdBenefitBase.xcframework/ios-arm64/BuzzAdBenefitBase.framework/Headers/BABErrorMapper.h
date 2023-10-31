@import Foundation;

@class BABError;

NS_ASSUME_NONNULL_BEGIN

@interface BABErrorMapper : NSObject

- (NSError *)transformBABError:(BABError *)error;

@end

NS_ASSUME_NONNULL_END
