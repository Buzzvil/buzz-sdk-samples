import UIKit
import BuzzvilSDK

class ViewController: UIViewController {
  private lazy var rootStackView: UIStackView = {
    let stackView = UIStackView(frame: .zero)
    stackView.axis = .vertical
    stackView.spacing = 12
    stackView.distribution = .fillEqually
    return stackView
  }()
  
  private lazy var nativeButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Native", for: .normal)
    button.addTarget(self, action: #selector(pushNativeAdViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var interstitialButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Interstitial", for: .normal)
    button.addTarget(self, action: #selector(pushInterstitialViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var carouselButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Carousel", for: .normal)
    button.addTarget(self, action: #selector(pushCarouselViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var benefitHubButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("BenefitHub", for: .normal)
    button.addTarget(self, action: #selector(pushBenefitHubViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var benefitHubContainerButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("BenefitHub Container", for: .normal)
    button.addTarget(self, action: #selector(pushBenefitHubContainerViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var benefitHubCustomNaviContainerButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("BenefitHub CustomNavi Container", for: .normal)
    button.addTarget(self, action: #selector(pushBenefitHubCustomNaviContainerViewController), for: .touchUpInside)
    return button
  }()

  private lazy var bannerButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Banner", for: .normal)
    button.addTarget(self, action: #selector(pushBannerViewController), for: .touchUpInside)
    return button
  }()
  
  private lazy var inquiryButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Inquiry", for: .normal)
    button.addTarget(self, action: #selector(showInquiry), for: .touchUpInside)
    return button
  }()
  
  private lazy var privacyConsentStatusLabel: UILabel = {
    let label = UILabel(frame: .zero)
    label.text = "Before Load Status"
    label.numberOfLines = 0
    
    return label
  }()
  
  private lazy var loadPrivacyConsentStatusButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Load PrivacyConsent Status", for: .normal)
    button.addTarget(self, action: #selector(loadPrivacyConsentStatus), for: .touchUpInside)
    
    return button
  }()
  
  private lazy var grantPrivacyConsentButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Grant PrivacyConsent", for: .normal)
    button.addTarget(self, action: #selector(grantPrviacyConsent), for: .touchUpInside)
    
    return button
  }()

  override func viewDidLoad() {
    super.viewDidLoad()
    
    setupView()
    setupLayout()
  }
  
  private func setupView() {
    view.backgroundColor = .systemBackground

    navigationItem.title = "BuzzvilSDK-Swift"
    
    rootStackView.addArrangedSubview(nativeButton)
    rootStackView.addArrangedSubview(interstitialButton)
    rootStackView.addArrangedSubview(carouselButton)
    rootStackView.addArrangedSubview(benefitHubButton)
    rootStackView.addArrangedSubview(benefitHubContainerButton)
    rootStackView.addArrangedSubview(benefitHubCustomNaviContainerButton)
    rootStackView.addArrangedSubview(bannerButton)
    rootStackView.addArrangedSubview(inquiryButton)
    rootStackView.addArrangedSubview(privacyConsentStatusLabel)
    rootStackView.addArrangedSubview(loadPrivacyConsentStatusButton)
    rootStackView.addArrangedSubview(grantPrivacyConsentButton)

    view.addSubview(rootStackView)
    
    let buttonsToSetupAppearance = [
      nativeButton,
      interstitialButton,
      carouselButton,
      benefitHubButton,
      benefitHubContainerButton,
      benefitHubCustomNaviContainerButton,
      bannerButton,
      inquiryButton,
      loadPrivacyConsentStatusButton,
      grantPrivacyConsentButton,
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
      rootStackView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: 8),
      rootStackView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -8),
    ])
  }
  
  private func setupAppearanceOfButton(button: UIButton) {
    button.backgroundColor = .systemBlue
    button.layer.cornerRadius = 8
    button.setTitleColor(.white, for: .normal)
    button.titleLabel?.font = UIFont.systemFont(ofSize: 16)
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
  
  @objc private func pushBenefitHubViewController() {
    let benefitHubViewController = BenefitHubViewController()
    navigationController?.pushViewController(benefitHubViewController, animated: true)
  }

  @objc private func pushBenefitHubContainerViewController() {
    let benefitHubContainerViewController = BenefitHubContainerViewController()
    navigationController?.pushViewController(benefitHubContainerViewController, animated: true)
  }
  
  @objc private func pushBenefitHubCustomNaviContainerViewController() {
    let benefitHubCustomNaviViewController = BenefitHubNavigationCustomContainerViewController()
    navigationController?.pushViewController(benefitHubCustomNaviViewController, animated: true)
  }

  @objc private func pushBannerViewController() {
    let bannerViewController = BannerViewController()
    navigationController?.pushViewController(bannerViewController, animated: true)
  }
  
  @objc private func showInquiry() {
    BuzzAdBenefit.shared.openInquiryPage(unitId: "YOUR_UNIT_ID")
  }
  
  @objc private func loadPrivacyConsentStatus() {
    BuzzAdBenefit.shared.loadPrivacyConsentStatus { [weak self] status in
      guard let self = self else { return }
      switch status {
      case .granted:
        self.privacyConsentStatusLabel.text = "Granted"
      case .revoked:
        self.privacyConsentStatusLabel.text = "Revoked"
      }
    } onFailure: { [weak self] error in
      guard let self = self else { return }
      
      self.privacyConsentStatusLabel.text = "LoadPrivacyConsentStatus is failed Error: \(error)"
    }
  }
  
  @objc private func grantPrviacyConsent() {
    BuzzAdBenefit.shared.grantPrivacyConsent { [weak self] in
      guard let self = self else { return }
      self.privacyConsentStatusLabel.text = "Granted"
    } onFailure: { [weak self] error in
      guard let self = self else { return }
      
      self.privacyConsentStatusLabel.text = "GrantPrivacyConsentStatus is failed Error: \(error)"
    }
  }
}
