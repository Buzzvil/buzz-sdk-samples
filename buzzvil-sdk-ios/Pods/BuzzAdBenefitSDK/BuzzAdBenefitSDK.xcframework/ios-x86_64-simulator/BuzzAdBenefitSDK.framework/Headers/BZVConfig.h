@import Foundation;

@class BZVConfigBuilder;

NS_ASSUME_NONNULL_BEGIN

typedef void(^BZVConfigBuilderBlock)(BZVConfigBuilder *builder);

@interface BZVConfig : NSObject

@property (nonatomic, copy, readonly) NSString *appId;
@property (nonatomic, assign, readonly) BOOL logging;

@end

@interface BZVConfig (Builder)

+ (instancetype)configWithBlock:(BZVConfigBuilderBlock)block;

@end

@interface BZVConfigBuilder : NSObject

@property (nonatomic, copy, readwrite) NSString *appId;
@property (nonatomic, assign, readwrite) BOOL logging;

- (BZVConfig *)build;

@end

NS_ASSUME_NONNULL_END
