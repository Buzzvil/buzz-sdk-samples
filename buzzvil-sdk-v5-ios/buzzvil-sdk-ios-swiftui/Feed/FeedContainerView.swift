import SwiftUI
import BuzzvilSDK

struct FeedContainerView: UIViewControllerRepresentable {
  typealias UIViewControllerType = FeedContainerViewController
  
  func makeUIViewController(context: Context) -> FeedContainerViewController {
    return FeedContainerViewController()
  }
  
  func updateUIViewController(_ uiViewController: FeedContainerViewController, context: Context) {}
}

// 하위 뷰 컨트롤러로 베네핏허브 연동하기
class FeedContainerViewController: UIViewController {
  let buzzAdFeed = BZVBuzzAdFeed { _ in }
  
  override func viewDidLoad() {
    super.viewDidLoad()
    displayContentViewController(buzzAdFeed.viewController)
  }
  
  private func displayContentViewController(_ contentViewController: UIViewController) {
    addChild(contentViewController)
    contentViewController.view.frame = view.bounds
    view.addSubview(contentViewController.view)
    contentViewController.didMove(toParent: self)
  }
}
