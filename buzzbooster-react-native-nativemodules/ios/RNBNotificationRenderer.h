@import Foundation;
@import UserNotifications;

NS_ASSUME_NONNULL_BEGIN

@interface RNBNotificationRenderer : NSObject

+ (void)renderWithUserInfo:(NSDictionary *)userInfo;

@end

NS_ASSUME_NONNULL_END
