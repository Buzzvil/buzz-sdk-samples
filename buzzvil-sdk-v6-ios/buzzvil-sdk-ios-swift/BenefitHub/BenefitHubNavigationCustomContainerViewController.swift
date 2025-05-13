import BuzzvilSDK
import UIKit

// 하위 뷰 컨트롤러로 베네핏허브 연동하기
class BenefitHubNavigationCustomContainerViewController: UIViewController {
  private lazy var benefitHub: BuzzBenefitHub = {
    let benefitHub = BuzzBenefitHub()
    let benefitHubConfig = BuzzBenefitHubConfig.Builder()
      .setNavigationBarHidden(false)
      .build()
    
    benefitHub.setConfig(benefitHubConfig)
    
    return benefitHub
  }()
  
  override func viewDidLoad() {
    super.viewDidLoad()
    self.navigationController?.delegate = self
    
    view.backgroundColor = .systemBackground

    displayContentViewController(benefitHub.createViewController())
  }
  
  private func displayContentViewController(_ contentViewController: UIViewController) {
    addChild(contentViewController)
    contentViewController.view.frame = view.bounds
    view.addSubview(contentViewController.view)
    contentViewController.didMove(toParent: self)
  }
  
  private func customNavigationBar(navigationController: UINavigationController, viewController: UIViewController) {
    navigationController.navigationBar.tintColor = .red
    viewController.navigationItem.largeTitleDisplayMode = .never
    let backButton = UIBarButtonItem(title: "Back", style: .plain, target: self, action: #selector(backButtonTapped))
    viewController.navigationItem.rightBarButtonItem = backButton
    viewController.navigationItem.leftBarButtonItem = backButton
    
    let navigationBar = navigationController.navigationBar
    let appearance = UINavigationBarAppearance()
    appearance.configureWithOpaqueBackground()
    appearance.backgroundColor = .systemBlue  // 원하는 색상으로 설정
    
    navigationBar.standardAppearance = appearance
    navigationBar.scrollEdgeAppearance = appearance
  }
  
  @objc
  private func backButtonTapped() {
    benefitHub.handleBackNavigation()
  }
}

extension BenefitHubNavigationCustomContainerViewController: UINavigationControllerDelegate {
  func navigationController(_ navigationController: UINavigationController, willShow viewController: UIViewController, animated: Bool) {
    customNavigationBar(navigationController: navigationController, viewController: viewController)
  }
}
