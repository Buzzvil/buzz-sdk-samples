#import <Foundation/Foundation.h>

typedef NS_ENUM(NSUInteger, BuzzEventType) {
  BuzzEventTypeLanding,
  BuzzEventTypeParticipated,
  BuzzEventTypeUnknown
};

@class BuzzReward;

@interface BuzzEvent : NSObject

@property (nonatomic, assign, readonly) BuzzEventType type;
@property (nonatomic, copy, readonly) NSArray<NSString *> *trackingUrls;
@property (nonatomic, copy, readonly) NSDictionary *extra;
@property (nonatomic, copy, readonly) BuzzReward *reward;

- (instancetype)initWithDictionary:(NSDictionary *)dic;

@end
