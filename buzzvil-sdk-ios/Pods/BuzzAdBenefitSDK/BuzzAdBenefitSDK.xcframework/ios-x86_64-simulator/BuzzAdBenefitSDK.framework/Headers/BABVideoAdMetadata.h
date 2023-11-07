#import <Foundation/Foundation.h>

typedef enum {
  BABVideoMinimumTimeTypeUnknown = 0,
  BABVideoMinimumTimeTypePlayEnd,
  BABVideoMinimumTimeTypePlayStart,
  BABVideoMinimumTimeTypePercentage,
  BABVideoMinimumTimeTypeSecond
} BABVideoMinimumTimeType;

NS_ASSUME_NONNULL_BEGIN

@interface BABVideoAdMetadata : NSObject

@property (nonatomic, copy, readonly) NSString *clickId;
@property (nonatomic, copy, readonly) NSString *lineItemId;
@property (nonatomic, copy, readonly) NSString *unitId;
@property (nonatomic, copy, readonly) NSString *playtimeTrackingUrl;
@property (nonatomic, copy) NSString *landingUrl;
@property (nonatomic, copy, readonly) NSString *postbackUrl;
@property (nonatomic, copy, readonly) NSString *videoId;
@property (nonatomic, copy, readonly) NSString *sessionId;
@property (nonatomic, assign, readonly) BABVideoMinimumTimeType minimumTimeType;
@property (nonatomic, assign, readonly) NSUInteger minimumTime;
@property (nonatomic, assign, readonly) BOOL shouldShowWithLanding;

- (instancetype)initWithDictionary:(NSDictionary *)dic;
- (NSDictionary *)postbackParams;
- (NSDictionary *)playtimeParams;
- (NSTimeInterval)getMinimumTimeWithDuration:(NSTimeInterval)duration;

@end

NS_ASSUME_NONNULL_END
