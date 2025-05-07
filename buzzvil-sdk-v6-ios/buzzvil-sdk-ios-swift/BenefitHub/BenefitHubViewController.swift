import UIKit
import BuzzvilSDK

class BenefitHubViewController: UIViewController {
  private lazy var stackView: UIStackView = {
    let stackView = UIStackView(arrangedSubviews: [showBenefitHubButton, showLuckyBoxButton, showMissionPackButton, showHistoryButton])
    stackView.axis = .vertical
    stackView.spacing = 8
    stackView.distribution = .fillEqually
    
    return stackView
  }()
  
  private lazy var showBenefitHubButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Show BenefitHub", for: .normal)
    button.addTarget(self, action: #selector(showBenefitHub), for: .touchUpInside)
    
    return button
  }()
  
  private lazy var showLuckyBoxButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Show LuckyBox", for: .normal)
    button.addTarget(self, action: #selector(showLuckyBox), for: .touchUpInside)

    return button
  }()
  
  private lazy var showMissionPackButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Show MissionPack", for: .normal)
    button.addTarget(self, action: #selector(showMissionPack), for: .touchUpInside)

    return button
  }()
  
  private lazy var showHistoryButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Show History", for: .normal)
    button.addTarget(self, action: #selector(showHistory), for: .touchUpInside)

    return button
  }()

  private lazy var benefitHub = BuzzBenefitHub()
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    setupView()
    setupLayout()
  }
  
  private func setupView() {
    view.backgroundColor = .systemBackground
    
    view.addSubview(stackView)
    
    let buttonsToSetupAppearance = [
      showBenefitHubButton,
      showLuckyBoxButton,
      showMissionPackButton,
      showHistoryButton,
    ]
    
    buttonsToSetupAppearance.forEach { button in
      setupAppearanceOfButton(button: button)
    }
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
  
  private func setupLayout() {
    stackView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      stackView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
      stackView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor),
      stackView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor),
      stackView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor),
    ])
  }
  
  // 베네핏허브 표시하기
  @objc
  private func showBenefitHub() {
    benefitHub = BuzzBenefitHub()
    
    benefitHub.show(on: self)
  }
  
  // 럭키박스 표시하기
  @objc
  private func showLuckyBox() {
    benefitHub = BuzzBenefitHub()
    let benefitHubConfig = BuzzBenefitHubConfig.Builder()
      .setQueryParams(BuzzBenefitHubPage.luckyBox.toRedirectQueryParams())
      .build()
    benefitHub.setConfig(benefitHubConfig)
    benefitHub.show(on: self)
  }
  
  // 미션팩 표시하기
  @objc
  private func showMissionPack() {
    benefitHub = BuzzBenefitHub()
    let benefitHubConfig = BuzzBenefitHubConfig.Builder()
      .setQueryParams(BuzzBenefitHubPage.missionPack.toRedirectQueryParams())
      .build()
    benefitHub.setConfig(benefitHubConfig)

    benefitHub.show(on: self)
  }
  
  // 적립내역 표시하기
  @objc
  private func showHistory() {
    benefitHub = BuzzBenefitHub()
    let benefitHubConfig = BuzzBenefitHubConfig.Builder()
      .setQueryParams(BuzzBenefitHubPage.history.toRedirectQueryParams())
      .build()
    benefitHub.setConfig(benefitHubConfig)
    
    benefitHub.show(on: self)
  }
}
