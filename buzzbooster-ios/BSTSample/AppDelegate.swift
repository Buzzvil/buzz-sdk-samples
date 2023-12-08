import UIKit
import BuzzBoosterSDK
import UserNotifications

@main
class AppDelegate: UIResponder, UIApplicationDelegate, UNUserNotificationCenterDelegate {
  static var APP_KEY: String = "279753136766115" // TODO: Replace with your app key
  static var USER_ID: String = NSUUID().uuidString // TODO: Replace with your user id

  var window: UIWindow?
  
  func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    UNUserNotificationCenter.current().delegate = self
    UIApplication.shared.registerForRemoteNotifications()
    let config = BSTConfig { builder in
      builder.appKey = AppDelegate.APP_KEY
    }
    BuzzBooster.initialize(with: config)
    BuzzBooster.optInMarketingCampaignDelegate = self
    BuzzBooster.addUserEventDelegate(self)
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
    withCompletionHandler completionHandler: @escaping () -> Void
  ) {
    BuzzBooster.userNotificationCenter(
      center,
      didReceive: response,
      withCompletionHandler: completionHandler
    )
    completionHandler()
  }
  
  // The method will be called on the delegate only if the application is in the foreground. If the method is not implemented or the handler is not called in a timely manner then the notification will not be presented. The application can choose to have the notification presented as a sound, badge, alert and/or in the notification list. This decision should be based on whether the information in the notification is otherwise visible to the user.
  func userNotificationCenter(
    _ center: UNUserNotificationCenter,
    willPresent notification: UNNotification,
    withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void
  ) {
    completionHandler([.alert, .sound])
  }
}

// MARK: Push Notification
extension AppDelegate {
  func application(
    _ application: UIApplication,
    didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data
  ) {
    BuzzBooster.setDeviceToken(deviceToken)
  }
}

// Mark: Move to your OptInMarketingPage in this Delegate
extension AppDelegate: BSTOptInMarketingCampaignDelegate {
  func onMoveButtonTapped(in viewController: UIViewController) {
    let yourOptInMarketingViewController = OptInMarketingViewController()
    viewController.present(yourOptInMarketingViewController, animated: true)
  }
}

extension AppDelegate: BSTUserEventDelegate {
  func userEventDidOccur(_ userEvent: BSTUserEvent) {
    print("userEventDidOccur: \(userEvent.name) \(String(describing: userEvent.values))")
  }
}
