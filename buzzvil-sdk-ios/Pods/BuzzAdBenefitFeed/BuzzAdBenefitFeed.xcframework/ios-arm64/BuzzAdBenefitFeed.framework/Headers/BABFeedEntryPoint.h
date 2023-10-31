@import Foundation;

NS_ASSUME_NONNULL_BEGIN

@interface BABFeedEntryPoint : NSObject

@property (nonatomic, copy, readonly) NSString *name;
@property (nonatomic, copy, readonly) NSString *unitId;

- (instancetype)initWithName:(NSString *)name unitId:(NSString *)unitId;

@end

NS_ASSUME_NONNULL_END
