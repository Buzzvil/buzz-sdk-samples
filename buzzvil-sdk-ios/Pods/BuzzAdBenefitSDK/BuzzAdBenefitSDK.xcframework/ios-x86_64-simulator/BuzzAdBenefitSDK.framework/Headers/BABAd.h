#import <Foundation/Foundation.h>
#import "BABCreative.h"
#import "BABTrackable.h"
#import "BuzzEvent.h"
#import "BABProduct.h"
#import "BABRevenueType.h"

NS_ASSUME_NONNULL_BEGIN

typedef enum {
  BABAdRewardStatusUnknown = 0,
  BABAdRewardReceived
} BABAdRewardStatus;

@interface BABAd : NSObject <BABTrackable>

@property (nonatomic, copy, readonly) NSString *Id;
@property (nonatomic, assign, readonly) NSUInteger ttl;
@property (nonatomic, assign, readonly) double landingReward;
@property (nonatomic, assign, readonly) double actionReward;
@property (nonatomic, assign, readonly) BOOL isActionType;
@property (nonatomic, assign, readonly) BOOL isVideoType;
@property (nonatomic, assign, readonly) long adnetworkCampaignType;
@property (nonatomic, copy, readonly) NSString *payload;
@property (nonatomic, copy, readonly) NSDictionary *extra;
@property (nonatomic, copy, readonly, nullable) NSString *preferredBrowser;
@property (nonatomic, readonly) BABAdRewardStatus rewardStatus;
@property (nonatomic, strong, readonly) BABRevenueType *revenueType;
@property (nonatomic, readonly) NSArray<BuzzEvent *> *events;
@property (nonatomic, readonly) NSDictionary *eventMap;
@property (nonatomic, strong, readonly) BABCreative *creative;
@property (nonatomic, copy, readonly) NSString *skStoreProductUrl;

@property (nonatomic, strong, readonly) NSString *conversionCheckUrl;

@property (nonatomic, copy, readonly) BABProduct *product;

@property (nonatomic, readonly) double reward;

@property (nonatomic, readonly) NSString *unitId;
@property (nonatomic, readonly, nullable) NSDate *fetchedAt;

- (instancetype)initWithDictionary:(NSDictionary *)dic unitId:(NSString *)unitId fetchedAt:(nullable NSDate *)fetchedAt;

@end

@interface BABAd (Extension)

- (BOOL)isLegacyReward;

- (double)getTotalReward;

- (double)getAvailableReward;

- (BOOL)isActionTypeForClient;

- (BuzzEvent *)getEventWithType:(BuzzEventType)type;

- (BOOL)hasRewardForEventType:(BuzzEventType)type;

- (double)getEventReward;

@end

NS_ASSUME_NONNULL_END
