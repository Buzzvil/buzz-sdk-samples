#import <Foundation/Foundation.h>
#import "BABAd.h"

@class BABAdLoaderParams;
@class BABError;

NS_ASSUME_NONNULL_BEGIN

@interface BABAdLoader : NSObject

- (instancetype)initWithUnitId:(NSString *)unitId autoResetCursor:(BOOL)autoResetCursor;

- (void)loadAdsWithParams:(BABAdLoaderParams *)params
                onSuccess:(void (^)(NSArray<BABAd *> *ads))onSuccess
                onFailure:(void (^)(BABError *error))onFailure;

- (void)loadAdsWithParams:(BABAdLoaderParams *)params
            requestTarget:(NSString *)requestTarget
                onSuccess:(void (^)(NSArray<BABAd *> * _Nonnull))onSuccess
                onFailure:(void (^)(BABError * _Nonnull))onFailure;

- (void)loadAvailableRewardWithParams:(BABAdLoaderParams *)params
                            onSuccess:(void (^)(NSNumber *availableReward))onSuccess
                            onFailure:(void (^)(BABError *error))onFailure;

- (void)resetCursor;

@end

NS_ASSUME_NONNULL_END
