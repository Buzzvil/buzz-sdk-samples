#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#import <UIKit/UIKit.h>
#import <BuzzBoosterSDK/BuzzBoosterSDK.h>
#import <UserNotifications/UserNotifications.h>

@interface RNBuzzBooster : RCTEventEmitter <RCTBridgeModule, BSTOptInMarketingCampaignDelegate, BSTUserEventDelegate>

+ (void)userNotificationCenter:(UNUserNotificationCenter *)center
didReceiveNotificationResponse:(UNNotificationResponse *)response
         withCompletionHandler:(void (^)(void))completionHandler;

@end
