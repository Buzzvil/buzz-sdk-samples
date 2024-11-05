#import "RNBNotificationRenderer.h"

@implementation RNBNotificationRenderer
+ (void)renderWithUserInfo:(NSDictionary *)userInfo {
  UNNotificationContent *content = [self createNotificationContentWithUserInfo:userInfo];
  UNNotificationRequest *request = [self createNotificationRequestWithContent:content userInfo:userInfo];

  [[UNUserNotificationCenter currentNotificationCenter] addNotificationRequest:request withCompletionHandler:^(NSError * _Nullable error) {
    if (error != nil) { }
  }];
}

#pragma mark -private
+ (UNNotificationContent *)createNotificationContentWithUserInfo:(NSDictionary *)userInfo{
  UNMutableNotificationContent *content = [[UNMutableNotificationContent alloc] init];
  content.title = userInfo[@"title"];
  content.body = userInfo[@"body"];
  content.userInfo = userInfo;
  if (@available(iOS 15.0, *)) {
    content.interruptionLevel = UNNotificationInterruptionLevelCritical;
  }
  return content;
}

+ (UNNotificationRequest *)createNotificationRequestWithContent:(UNNotificationContent *)content
                                                       userInfo:(NSDictionary *)userInfo {
  NSString *requestId = userInfo[@"id"];
  return [UNNotificationRequest
          requestWithIdentifier:requestId
          content:content
          trigger:nil];
}

@end
