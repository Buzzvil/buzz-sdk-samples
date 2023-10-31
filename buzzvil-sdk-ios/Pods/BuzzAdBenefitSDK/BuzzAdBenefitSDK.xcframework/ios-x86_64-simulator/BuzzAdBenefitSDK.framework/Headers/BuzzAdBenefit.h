#import <BuzzAdBenefitSDK/BZVConfig.h>
#import <BuzzAdBenefitSDK/BZVLoginRequest.h>
#import <BuzzAdBenefitSDK/BZVVideoAutoPlayType.h>
#import <BuzzAdBenefitBase/BZVError.h>
#import <BuzzAdBenefitBase/BZVPrivacyPolicyManager.h>
#import <BuzzAdBenefitBase/BZVUserInterfaceStyle.h>
#import <BuzzAdBenefitNative/BuzzAdBenefitNative.h>
#import <BuzzAdBenefitFeed/BuzzAdBenefitFeed.h>
#import <BuzzAdBenefitInterstitial/BuzzAdBenefitInterstitial.h>

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

@interface BuzzAdBenefit (DeepLink)

/// This method checks whether a given URL is a BuzzAd SDK deep link or not.
///
/// @param URL The URL object to check.
/// @return YES if the URL is a BuzzAd SDK deep link, otherwise NO.
+ (BOOL)isBuzzAdDeepLink:(NSURL *)URL;

/// This method opens a given BuzzAD SDK deep link URL.
///
/// @param URL The BuzzAd SDK deep link URL to open.
+ (void)openBuzzAdDeepLink:(NSURL *)URL;

@end

NS_ASSUME_NONNULL_END
