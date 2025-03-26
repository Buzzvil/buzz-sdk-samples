import UIKit

@available(iOS 13.0, *)
class SceneDelegate: UIResponder, UIWindowSceneDelegate {
  
  var window: UIWindow?
  
  func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
    guard let windowScene = (scene as? UIWindowScene) else { return }
    let window = UIWindow(windowScene: windowScene)
    let initialViewController = ViewController()
    let navigation = UINavigationController(rootViewController: initialViewController)
    self.window = window
    window.rootViewController = navigation
    window.makeKeyAndVisible()
  }
}
