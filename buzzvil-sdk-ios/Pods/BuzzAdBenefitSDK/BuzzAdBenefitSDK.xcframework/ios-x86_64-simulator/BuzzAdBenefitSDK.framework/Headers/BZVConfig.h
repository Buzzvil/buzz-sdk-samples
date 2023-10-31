@import Foundation;

@class BZVConfigBuilder;
@class BZVFeedConfig;

NS_ASSUME_NONNULL_BEGIN

typedef void(^BZVConfigBuilderBlock)(BZVConfigBuilder *builder);

@interface BZVConfig : NSObject

@property (nonatomic, copy, readonly) NSString *appId;
@property (nonatomic, assign, readonly) BOOL logging;
@property (nonatomic, strong, readonly, nullable) BZVFeedConfig *defaultFeedConfig;

@end

@interface BZVConfig (Builder)

+ (instancetype)configWithBlock:(BZVConfigBuilderBlock)block;

@end

@interface BZVConfigBuilder : NSObject

@property (nonatomic, copy, readwrite) NSString *appId;
@property (nonatomic, assign, readwrite) BOOL logging;
@property (nonatomic, strong, readwrite, nullable) BZVFeedConfig *defaultFeedConfig;

- (BZVConfig *)build;

@end

NS_ASSUME_NONNULL_END
