import UIKit
import BuzzvilSDK

class ViewController: UIViewController {
  private static let navigationItemTitle = "BuzzvilSDK"
  private static let nativeButtonTitle = "Native"
  private static let interstitialButtonTitle = "Interstitial"
  private static let buzzAdBenefitHubButtonTitle = "BenefitHub"
  private static let buzzAdBenefitHubConatinerButtonTitle = "BenefitHub Container"
  private static let buzzAdBannerButtonTitle = "Banner"
  private static let buzzAdMissionPackButtonTitle = "MissionPack"
  private static let carouselButtonTitle = "Carousel"
  private static let inquiryButtonTitle = "Inquiry"
  private static let buttonAspectRatio: CGFloat = 1.5
  private static let buttonMargin: CGFloat = 12
  private static let buttonCornerRadius: CGFloat = 8
  private static let buttonFontSize: CGFloat = 16
  
  private lazy var rootStackView: UIStackView = {
    let stackView = UIStackView(frame: .zero)
    stackView.axis = .vertical
    stackView.spacing = Self.buttonMargin
    stackView.distribution = .fillEqually
    return stackView
  }()
  
  private lazy var nativeButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle(Self.nativeButtonTitle, for: .normal)
    button.addTarget(self, action: #selector(pushNativeAdViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var interstitialButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle(Self.interstitialButtonTitle, for: .normal)
    button.addTarget(self, action: #selector(pushInterstitialViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var carouselButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle(Self.carouselButtonTitle, for: .normal)
    button.addTarget(self, action: #selector(pushCarouselViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var benefitHubButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle(Self.buzzAdBenefitHubButtonTitle, for: .normal)
    button.addTarget(self, action: #selector(pushFeedViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var benefitHubContainerButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle(Self.buzzAdBenefitHubConatinerButtonTitle, for: .normal)
    button.addTarget(self, action: #selector(pushFeedContainerViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var bannerButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle(Self.buzzAdBannerButtonTitle, for: .normal)
    button.addTarget(self, action: #selector(pushBannerViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var inquiryButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle(Self.inquiryButtonTitle, for: .normal)
    button.addTarget(self, action: #selector(showInquiry), for: .touchUpInside)
    return button
  }()
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    setupView()
    setupLayout()
  }
  
  private func setupView() {
    view.backgroundColor = .systemBackground

    navigationItem.title = Self.navigationItemTitle
    
    rootStackView.addArrangedSubview(nativeButton)
    rootStackView.addArrangedSubview(interstitialButton)
    rootStackView.addArrangedSubview(carouselButton)
    rootStackView.addArrangedSubview(benefitHubButton)
    rootStackView.addArrangedSubview(benefitHubContainerButton)
    rootStackView.addArrangedSubview(bannerButton)
    
    view.addSubview(rootStackView)
    
    let buttonsToSetupAppearance = [
      nativeButton,
      interstitialButton,
      carouselButton,
      benefitHubButton,
      benefitHubContainerButton,
      bannerButton,
    ]
    
    buttonsToSetupAppearance.forEach { button in
      setupAppearanceOfButton(button: button)
    }
  }
  
  private func setupLayout() {
    rootStackView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      rootStackView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
      rootStackView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor),
      rootStackView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor),
      rootStackView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor),
    ])
  }
  
  private func setupAppearanceOfButton(button: UIButton) {
    button.backgroundColor = .systemBlue
    button.layer.cornerRadius = Self.buttonCornerRadius
    button.setTitleColor(.white, for: .normal)
    button.titleLabel?.font = UIFont.systemFont(ofSize: Self.buttonFontSize)
    button.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      button.heightAnchor.constraint(equalToConstant: 32)
    ])
  }
  
  @objc private func pushNativeAdViewController() {
    let nativeAdViewController = NativeViewController()
    navigationController?.pushViewController(nativeAdViewController, animated: true)
  }
  
  @objc private func pushCarouselViewController() {
    let carouselConfigurationViewController = CarouselViewController()
    navigationController?.pushViewController(carouselConfigurationViewController, animated: true)
  }
  
  @objc private func pushInterstitialViewController() {
    let interstitialViewController = InterstitialViewController()
    navigationController?.pushViewController(interstitialViewController, animated: true)
  }
  
  @objc private func pushFeedViewController() {
    let feedViewController = BenefitHubViewController()
    navigationController?.pushViewController(feedViewController, animated: true)
  }
  
  @objc private func pushFeedContainerViewController() {
    let feedContainerViewController = BenefitHubContainerViewController()
    navigationController?.pushViewController(feedContainerViewController, animated: true)
  }
  
  @objc private func pushBannerViewController() {
    let bannerViewController = BannerViewController()
    navigationController?.pushViewController(bannerViewController, animated: true)
  }
  
  @objc private func showInquiry() {
    BuzzAdBenefit.presentInquiryPage(on: self, unitId: "YOUR_UNIT_ID")
  }
}
