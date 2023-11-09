import UIKit

class ViewController: UIViewController {
  private static let navigationItemTitle = "BuzzAdBenefit"
  private static let nativeButtonTitle = "Native"
  private static let interstitialButtonTitle = "Interstitial"
  private static let benefitHubButtonTitle = "BenefitHub"
  private static let carouselButtonTitle = "Carousel"
  private static let buttonAspectRatio: CGFloat = 1.5
  private static let buttonMargin: CGFloat = 12
  private static let buttonCornerRadius: CGFloat = 8
  private static let buttonFontSize: CGFloat = 16
  
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
  
  private lazy var feedButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle(Self.benefitHubButtonTitle, for: .normal)
    button.addTarget(self, action: #selector(pushBenefitHubViewController), for: .touchUpInside)
    return button
  }()
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    setupView()
    setupLayout()
  }
  
  private func setupView() {
    navigationItem.title = Self.navigationItemTitle
    view.addSubview(nativeButton)
    view.addSubview(interstitialButton)
    view.addSubview(carouselButton)
    view.addSubview(feedButton)
    
    let buttonsToSetupAppearance = [
      nativeButton,
      interstitialButton,
      carouselButton,
      feedButton
    ]
    
    buttonsToSetupAppearance.forEach { button in
      setupAppearanceOfButton(button: button)
    }
  }
  
  private func setupLayout() {
    nativeButton.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      nativeButton.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: Self.buttonMargin),
      nativeButton.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: Self.buttonMargin),
      nativeButton.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.centerXAnchor, constant: -Self.buttonMargin/2),
      nativeButton.widthAnchor.constraint(equalTo: nativeButton.heightAnchor, multiplier: Self.buttonAspectRatio)
    ])
    
    interstitialButton.translatesAutoresizingMaskIntoConstraints = false
    interstitialButton.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      interstitialButton.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: Self.buttonMargin),
      interstitialButton.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.centerXAnchor, constant: Self.buttonMargin/2),
      interstitialButton.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -Self.buttonMargin),
      interstitialButton.widthAnchor.constraint(equalTo: interstitialButton.heightAnchor, multiplier: Self.buttonAspectRatio)
    ])
    
    carouselButton.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      carouselButton.topAnchor.constraint(equalTo: nativeButton.bottomAnchor, constant: Self.buttonMargin),
      carouselButton.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: Self.buttonMargin),
      carouselButton.trailingAnchor.constraint(equalTo: view.centerXAnchor, constant: -Self.buttonMargin/2),
      carouselButton.widthAnchor.constraint(equalTo: carouselButton.heightAnchor, multiplier: Self.buttonAspectRatio)
    ])
    
    feedButton.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      feedButton.topAnchor.constraint(equalTo: interstitialButton.bottomAnchor, constant: Self.buttonMargin),
      feedButton.leadingAnchor.constraint(equalTo: view.centerXAnchor, constant: Self.buttonMargin/2),
      feedButton.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -Self.buttonMargin),
      feedButton.widthAnchor.constraint(equalTo: feedButton.heightAnchor, multiplier: Self.buttonAspectRatio)
    ])
  }
  
  private func setupAppearanceOfButton(button: UIButton) {
    button.backgroundColor = UIColor(white: 0.15, alpha: 1)
    button.layer.cornerRadius = Self.buttonCornerRadius
    button.setTitleColor(.white, for: .normal)
    button.titleLabel?.font = UIFont.systemFont(ofSize: Self.buttonFontSize)
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
  
}
