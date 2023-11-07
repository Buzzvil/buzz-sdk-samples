#import <Foundation/Foundation.h>

typedef NS_ENUM(NSInteger, BZVControlState) {
  BZVControlStateNormal,
  BZVControlStateHighlight,
};

@class BZVControlStateResourceBuilder;

NS_ASSUME_NONNULL_BEGIN

typedef void (^BZVViewStateResourceBuilderBlock)(BZVControlStateResourceBuilder *builder);

@interface BZVControlStateResource<ResourceType> : NSObject

- (ResourceType)valueForState:(BZVControlState)state;

@end

@interface BZVControlStateResource (Builder)

+ (instancetype)resourceWithBlock:(BZVViewStateResourceBuilderBlock)block;

@end

@interface BZVControlStateResourceBuilder<ResourceType> : NSObject

- (void)setValue:(ResourceType)value forState:(BZVControlState)state;

- (BZVControlStateResource<ResourceType> *)build;

@end

NS_ASSUME_NONNULL_END
