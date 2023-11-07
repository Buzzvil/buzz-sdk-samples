#import <Foundation/Foundation.h>

@class BABServerConfig;
@class BABServiceUrlProvider;

@class BuzzCheckStatusUseCase;
@class BuzzStoreKitUseCase;

@class BuzzEventProvider;
@class BABInquiryHandler;
@class BuzzInsight;
@class BABVideoAdMetadataLoader;
@class BABClickUrlEncoder;
@class BABImpressionUrlEncoder;
@class BABTracker;
@class BZVPrivacyPolicyManager;

@protocol BABAdRequestProtocol;
@protocol BABArticleRequestProtocol;
@protocol BABRewardRequestProtocol;

NS_ASSUME_NONNULL_BEGIN

@interface BABProvider : NSObject

@property (nonatomic, copy, nullable) BABServerConfig *serverConfig;
@property (nonatomic, copy, readonly) BABServiceUrlProvider *serviceUrlProvider;

@property (nonatomic, copy, readonly) BuzzCheckStatusUseCase *checkStatusUseCase;
@property (nonatomic, copy, readonly) BuzzStoreKitUseCase *storeKitUseCase;

@property (nonatomic, copy, readonly) BuzzEventProvider *eventProvider;
@property (nonatomic, copy, readonly) BuzzInsight *buzzInsight;
@property (nonatomic, copy, readonly) BABClickUrlEncoder *clickUrlEncoder;
@property (nonatomic, copy, readonly) BABImpressionUrlEncoder *impressionUrlEncoder;
@property (nonatomic, copy, readonly) BABTracker *tracker;
@property (nonatomic, copy, readonly) BABVideoAdMetadataLoader *videoAdMetadataLoader;
@property (nonatomic, copy, readonly) BZVPrivacyPolicyManager *privacyPolicyManager;

@property (nonatomic, copy, readonly) id<BABAdRequestProtocol> adRequest;
@property (nonatomic, copy, readonly) id<BABArticleRequestProtocol> articleRequest;
@property (nonatomic, copy, readonly) id<BABRewardRequestProtocol> rewardRequest;

- (BABInquiryHandler *)inquiryHandlerForUnitId:(nullable NSString *)unitId;

- (instancetype)initWithAppId:(NSString *)appId;

@end

NS_ASSUME_NONNULL_END
