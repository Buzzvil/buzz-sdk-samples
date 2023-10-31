@import Foundation;

NS_ASSUME_NONNULL_BEGIN

extern NSString * const BABGlobalThemeKey;

@class BABConfig;
@class BABUserPreference;
@class UIViewController;
@class BABError;
@class BABProvider;
@class BuzzInsight;
@class BZVPrivacyPolicyManager;

@interface BABBuzzAdBenefitBase: NSObject

@property (nonatomic, strong, readonly) BABConfig *config;
@property (nonatomic, readwrite, nullable) BABUserPreference *userPreference;
@property (nonatomic, readwrite) id launcher;
@property (nonatomic, strong, readonly) BABProvider *provider;
@property (nonatomic, strong, readonly) BuzzInsight *buzzInsight;

@property (nonatomic, strong) NSMutableDictionary<NSString *, id> *configStore;
// TODO : theme store?
@property (nonatomic, readwrite) NSMutableDictionary<NSString *, id> *themeStore;
@property (nonatomic, strong, readonly) BZVPrivacyPolicyManager *privacyPolicyManager;

+ (BABBuzzAdBenefitBase *)sharedInstance;
- (void)initializeWithConfig:(BABConfig *)config;
- (void)showInquiryPageOnViewController:(UIViewController *)viewController unitId:(nullable NSString *)unitId;
- (void)trackEventWithType:(NSString *)type name:(NSString *)name attributes:(NSDictionary *)attributes unitId:(NSString *)unitId;

@end

NS_ASSUME_NONNULL_END
