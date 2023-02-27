import UIKit
import BuzzBooster
import UserNotifications
import Toast

@main
class AppDelegate: UIResponder, UIApplicationDelegate, UNUserNotificationCenterDelegate {
  static var APP_KEY: String = "279753136766115"
  static var USER_ID: String = NSUUID().uuidString

  var window: UIWindow?
  
  func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    UNUserNotificationCenter.current().delegate = self
    UIApplication.shared.registerForRemoteNotifications()
    let notificationOptions = BSTNotificationOptions { builder in
      builder.userInfo = ["sample_key":"sample_value"]
      builder.categoryIdentifier = "sample_category"
    }
    let config = BSTConfig { builder in
      builder.appKey = AppDelegate.APP_KEY
      builder.notificationOptions = notificationOptions
    }
    BuzzBooster.initialize(with: config)
    BuzzBooster.startService()
    
    window = UIWindow(frame: UIScreen.main.bounds)
    let navigationViewController = UINavigationController(rootViewController: ViewController())
    window?.rootViewController = navigationViewController
    window?.makeKeyAndVisible()
    return true
  }

  // The method will be called on the delegate when the user responded to the notification by opening the application,
  // dismissing the notification or choosing a UNNotificationAction. The delegate must be set before the application returns from application:didFinishLaunchingWithOptions:.
  func userNotificationCenter(
    _ center: UNUserNotificationCenter,
    didReceive response: UNNotificationResponse,
    withCompletionHandler completionHandler: @escaping () -> Void)
  {
    BuzzBooster.userNotificationCenter(center, didReceive: response, withCompletionHandler: completionHandler)
    completionHandler()
  }
  
  // The method will be called on the delegate only if the application is in the foreground. If the method is not implemented or the handler is not called in a timely manner then the notification will not be presented. The application can choose to have the notification presented as a sound, badge, alert and/or in the notification list. This decision should be based on whether the information in the notification is otherwise visible to the user.
  func userNotificationCenter(
    _ center: UNUserNotificationCenter,
    willPresent notification: UNNotification,
    withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void) {
      completionHandler([.alert, .sound])
  }
  
  func showToastIfAuthorizationDenied(center: UNUserNotificationCenter, content: UNNotificationContent) {
    center.getNotificationSettings { settings in
      if (settings.authorizationStatus == UNAuthorizationStatus.denied) {
        DispatchQueue.main.async {
          self.window?.makeToast(content.body)
        }
      }
    }
  }
}

//MARK: Push Notification
extension AppDelegate {
  func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
    BuzzBooster.setDeviceToken(deviceToken)
  }
}
