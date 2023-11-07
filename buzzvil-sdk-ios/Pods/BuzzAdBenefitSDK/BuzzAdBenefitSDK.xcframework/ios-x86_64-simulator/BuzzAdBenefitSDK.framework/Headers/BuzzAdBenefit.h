#import <BuzzAdBenefitSDK/BZVConfig.h>
#import <BuzzAdBenefitSDK/BZVLoginRequest.h>
#import <BuzzAdBenefitSDK/BZVVideoAutoPlayType.h>
#import <BuzzAdBenefitSDK/BZVError.h>
#import <BuzzAdBenefitSDK/BZVPrivacyPolicyManager.h>
#import <BuzzAdBenefitSDK/BZVUserInterfaceStyle.h>

#import "BZVLauncher.h"

extern NSString * _Nonnull const BuzzAdBenefitWebInterfaceName;

@class NSObject;
@class UIViewController;
@class BZVUserPreferences;

NS_ASSUME_NONNULL_BEGIN

@interface BuzzAdBenefit: NSObject

+ (instancetype)sharedInstance;

@end

@interface BuzzAdBenefit (Configuration)

+ (void)initializeWithConfig:(BZVConfig *)config;
+ (BZVConfig *)config;

+ (void)loginWithBlock:(BZVLoginRequestBuilderBlock)block onSuccess:(void(^)(void))onSuccess onFailure:(void(^)(NSError *error))onFailure;
+ (void)logout;
+ (BOOL)isLoggedIn;

+ (void)setUserInterfaceStyle:(BZVUserInterfaceStyle)userInterfaceStyle;
+ (BZVUserInterfaceStyle)userInterfaceStyle;

+ (void)setUserPreferences:(nullable BZVUserPreferences *)userPreferences;
+ (nullable BZVUserPreferences *)userPreferences;

+ (void)setLauncher:(id<BZVLauncher>)launcher;
+ (id<BZVLauncher>)launcher;

@end

@interface BuzzAdBenefit (InquiryPage)

+ (void)presentInquiryPageOnViewController:(UIViewController *)viewController;
+ (void)presentInquiryPageOnViewController:(UIViewController *)viewController unitId:(nullable NSString *)unitId;

@end

@interface BuzzAdBenefit (BaseReward)

+ (void)getAvailableFeedBaseRewardForUnitId:(NSString *)unitId onComplete:(void (^)(NSInteger baseReward))onComplete;

@end

@interface BuzzAdBenefit (PrivacyPolicy)

@property (class, nonatomic, strong, readonly) BZVPrivacyPolicyManager *privacyPolicyManager;

@end

NS_ASSUME_NONNULL_END
