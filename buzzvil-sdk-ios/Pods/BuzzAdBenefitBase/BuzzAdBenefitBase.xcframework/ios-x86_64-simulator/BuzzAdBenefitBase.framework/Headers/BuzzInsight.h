@import Foundation;

@class BuzzInsightConfig;
@class BuzzInsightSaveEventUseCase;

@interface BuzzInsight : NSObject

+ (instancetype)insightWithConfig:(BuzzInsightConfig *)config saveEventUseCase:(BuzzInsightSaveEventUseCase *)saveEventUseCase;

- (void)trackEventWithType:(NSString *)type
                      name:(NSString *)name;

- (void)trackEventWithType:(NSString *)type
                      name:(NSString *)name
                attributes:(NSDictionary *)attributes;

- (void)trackEventWithType:(NSString *)type
                      name:(NSString *)name
                    unitId:(NSString *)unitId;

- (void)trackEventWithType:(NSString *)type
                      name:(NSString *)name
                attributes:(NSDictionary *)attributes
                    unitId:(NSString *)unitId;

@end
