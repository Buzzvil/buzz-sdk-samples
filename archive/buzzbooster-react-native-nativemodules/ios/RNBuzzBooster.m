#import "RNBuzzBooster.h"
#import "RNBNotificationRenderer.h"

#ifdef RCT_NEW_ARCH_ENABLED
#import "RNBuzzBoosterSpec.h"
#endif

@implementation RNBuzzBooster {
  BOOL hasListeners;
}

+ (void)userNotificationCenter:(UNUserNotificationCenter *)center
didReceiveNotificationResponse:(UNNotificationResponse *)response
         withCompletionHandler:(void (^)(void))completionHandler {
  [BuzzBooster userNotificationCenter:center
                           didReceive:response
                withCompletionHandler:completionHandler];
}
// Will be called when this module's first listener is added.
-(void)startObserving {
  hasListeners = YES;
}

// Will be called when this module's last listener is removed, or on dealloc.
-(void)stopObserving {
  hasListeners = NO;
}

- (NSArray<NSString *> *)supportedEvents{
  return @[@"OptInMarketingCampaignMoveButtonClicked", @"onUserEventDidOccur"];
}

RCT_EXPORT_MODULE(BuzzBooster) //Specify Module name for using in JS

- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
}

RCT_EXPORT_METHOD(initIosApp:(NSString *)appKey) {
  BSTConfig *config = [BSTConfig configWithBlock:^(BSTConfigBuilder * _Nonnull builder) {
    builder.appKey = appKey;
  }];
  [BuzzBooster initializeWithConfig:config];
  [BuzzBooster setOptInMarketingCampaignDelegate:self];
  [BuzzBooster addUserEventDelegate:self];
}

RCT_EXPORT_METHOD(setPushToken:(NSString *)deviceToken) {
  [BuzzBooster setPushToken:deviceToken];
}

RCT_EXPORT_METHOD(isBuzzBoosterNotification:(NSDictionary *)userInfo
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject) { // promise를 사용하려면 reject를 넣어줘야함
  if (userInfo[@"BuzzBooster"] != nil) {
    resolve(@YES);
  } else {
    resolve(@NO);
  }
}

RCT_EXPORT_METHOD(setUser:(nullable NSDictionary *)userDictionary) {
  if (userDictionary) {
    BSTUser *user = [BSTUser userWithBlock:^(BSTUserBuilder * _Nonnull builder) {
      builder.userId = userDictionary[@"userId"];
      builder.properties = userDictionary[@"properties"];
      NSNumber *_Nullable optInMarketing = userDictionary[@"optInMarketing"];
      if (optInMarketing == nil || [optInMarketing isKindOfClass:[NSNull class]]) {
        builder.marketingStatus = BSTMarketingStatusUndetermined;
      } else {
        if ([optInMarketing boolValue]) {
          builder.marketingStatus = BSTMarketingStatusOptIn;
        } else {
          builder.marketingStatus = BSTMarketingStatusOptOut;
        }
      }
    }];
    [BuzzBooster setUser:user];
  } else {
    [BuzzBooster setUser:nil];
  }
}

RCT_EXPORT_METHOD(showInAppMessage) {
  UIViewController *vc = [UIApplication sharedApplication].delegate.window.rootViewController;
  [BuzzBooster showInAppMessageWithViewController:vc];
}

RCT_EXPORT_METHOD(showHome) {
  UIViewController *vc = [UIApplication sharedApplication].delegate.window.rootViewController;
  [BuzzBooster showHomeWithViewController:vc];
}

RCT_EXPORT_METHOD(showNaverPayExchange) {
  UIViewController *vc = [UIApplication sharedApplication].delegate.window.rootViewController;
  [BuzzBooster showNaverPayExchangeWithViewController:vc];
}

RCT_EXPORT_METHOD(showCampaignWithId:(NSString *)campaignId) {
  UIViewController *viewController = [UIApplication sharedApplication].delegate.window.rootViewController;
  [BuzzBooster showCampaignWithId:campaignId viewController:viewController];
}

RCT_EXPORT_METHOD(showCampaignWithType:(NSString *)campaignType) {
  UIViewController *vc = [UIApplication sharedApplication].delegate.window.rootViewController;
  if ([campaignType isEqualToString:@"Attendance"]) {
    [BuzzBooster showCampaignWithType:BSTCampaignTypeAttendance viewController:vc];
  } else if ([campaignType isEqualToString:@"Referral"]) {
    [BuzzBooster showCampaignWithType:BSTCampaignTypeReferral viewController:vc];
  } else if ([campaignType isEqualToString:@"OptInMarketing"]) {
    [BuzzBooster showCampaignWithType:BSTCampaignTypeOptInMarketing viewController:vc];
  } else if ([campaignType isEqualToString:@"ScratchLottery"]) {
    [BuzzBooster showCampaignWithType:BSTCampaignTypeScratchLottery viewController:vc];
  } else if ([campaignType isEqualToString:@"Roulette"]) {
    [BuzzBooster showCampaignWithType:BSTCampaignTypeRoulette viewController:vc];
  } else {
    NSException *exception = [NSException exceptionWithName:@"Unknown Campaign Type"
                                                     reason:@""
                                                    userInfo:nil];
    @throw exception;
  }
}

RCT_EXPORT_METHOD(showPage:(NSString *)pageId) {
  [BuzzBooster showPageWithId:pageId];
}

RCT_EXPORT_METHOD(sendEvent:(NSString *)eventName eventValues:(nullable NSDictionary *)eventValues) {
  if (eventValues) {
    @try {
      [BuzzBooster sendEventWithName:eventName values:eventValues];  
    } @catch(id Exception) {
      NSLog(@"Cast is Failed, Input wrong format");
    }
  } else {
    [BuzzBooster sendEventWithName:eventName];
  }
}

RCT_EXPORT_METHOD(setTheme:(NSString *)theme) {
  if ([theme isEqualToString:@"Light"]) {
    [BuzzBooster setUserInterfaceStyle:BSTUserInterfaceStyleLight];
  } else if ([theme isEqualToString:@"Dark"]) {
    [BuzzBooster setUserInterfaceStyle:BSTUserInterfaceStyleDark];
  } else if ([theme isEqualToString:@"System"]) {
    [BuzzBooster setUserInterfaceStyle:BSTUserInterfaceStyleSystem];
  } else {
    NSException *exception = [NSException exceptionWithName:@"Unknown Theme"
                                                     reason:@""
                                                   userInfo:nil];
    @throw exception;
  }
}

RCT_EXPORT_METHOD(handleForegroundNotification:(NSDictionary *)userInfo) {
  [RNBNotificationRenderer renderWithUserInfo:userInfo];
}

RCT_EXPORT_METHOD(handleInitialNotification:(NSDictionary *)userInfo) {
  [BuzzBooster application:UIApplication.sharedApplication didReceiveRemoteNotification:userInfo fetchCompletionHandler:^(UIBackgroundFetchResult result) { }
  ];
}

RCT_EXPORT_METHOD(onNotificationOpenedApp:(NSDictionary *)userInfo) {
  [BuzzBooster application:UIApplication.sharedApplication didReceiveRemoteNotification:userInfo fetchCompletionHandler:^(UIBackgroundFetchResult result) { }
  ];
}

RCT_EXPORT_METHOD(postJavaScriptMessage:(NSString *)message) {
  NSError *error;
  UIViewController *viewController = [UIApplication sharedApplication].delegate.window.rootViewController;
  [BuzzBoosterWebKit handleWith:viewController
                    messageName:@"BuzzBooster"
                    messageBody:message
                          error:&error];
  if (error) {
    NSLog(@"%@", [error localizedDescription]);
  }  
}

#pragma mark --BSTOptInMarketingCampaignDelegate
- (void)onMoveButtonTappedIn:(UIViewController *)viewController {
  // Dimiss using rootViewController not tapped in viewcontroller
  UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;
  [rootViewController dismissViewControllerAnimated:NO completion:nil];
  if (hasListeners) {
    [self sendEventWithName:@"OptInMarketingCampaignMoveButtonClicked"
                       body:@{}];
  }
}

#pragma mark --BSTUserEventDelegate
- (void)userEventDidOccur:(BSTUserEvent * _Nonnull)userEvent {
  if (hasListeners) {
      NSString * _Nonnull userEventName = userEvent.name;
      NSDictionary<NSString *, id> * _Nullable userEventValues = userEvent.values;
    
      // Prevent Crash
      if (userEventValues != nil && ![userEventValues isKindOfClass:[NSNull class]]){
        [self sendEventWithName:@"onUserEventDidOccur"
                        body:@{
                                @"userEventName": userEventName,
                                @"userEventValues": userEventValues
                              }
        ];
      } else {
        [self sendEventWithName:@"onUserEventDidOccur"
                        body:@{
                                @"userEventName": userEventName,
                              }
        ];
      }
      
  }
}

// Don't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeBuzzBoosterSpecJSI>(params);
}
#endif

@end
