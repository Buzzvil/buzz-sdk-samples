import UIKit
import BuzzvilSDK

final class EntryPointViewController: UIViewController {
  private lazy var stackView: UIStackView = {
    let stackView = UIStackView(frame: .zero)
    stackView.axis = .vertical
    stackView.spacing = 12
    stackView.distribution = .fill
    return stackView
  }()
  
  private lazy var showFabButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Show FAB", for: .normal)
    button.addTarget(self, action: #selector(showFabButtonTapped), for: .touchUpInside)
    return button
  }()
  
  private lazy var showBannerButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Show Banner", for: .normal)
    button.addTarget(self, action: #selector(showBannerButtonTapped), for: .touchUpInside)
    return button
  }()
  
  private lazy var showPopupButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Show Popup", for: .normal)
    button.addTarget(self, action: #selector(showPopupButtonTapped), for: .touchUpInside)
    return button
  }()
  
  private lazy var showBottomSheetButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Show BottomSheet", for: .normal)
    button.addTarget(self, action: #selector(showBottomSheetButtonTapped), for: .touchUpInside)
    return button
  }()
  
  private lazy var customEntryButton: UIButton = {
    let button = UIButton(frame: .zero)
    button.setTitle("Custom Entry", for: .normal)
    button.addTarget(self, action: #selector(customEntryButtonTapped), for: .touchUpInside)
    return button
  }()
  
  private lazy var entryBannerView: BuzzEntryPointBannerView = {
    let view = BuzzEntryPointBannerView(frame: .zero)
    view.isHidden = true
    return view
  }()
  
  private lazy var spacingView: UIView = {
    return UIView(frame: .zero)
  }()
  
  private lazy var activityIndicator: UIActivityIndicatorView = {
    let indicatorView = UIActivityIndicatorView(style: .medium)
    indicatorView.hidesWhenStopped = true
    return indicatorView
  }()
  
  private var isFabPresented = false
  
  override func viewDidLoad() {
    super.viewDidLoad()
    setup()
    loadEntryPoints()
  }
  
  private func setup() {
    view.backgroundColor = .systemBackground
    
    view.addSubview(stackView)
    stackView.addArrangedSubview(showFabButton)
    stackView.addArrangedSubview(showBannerButton)
    stackView.addArrangedSubview(showPopupButton)
    stackView.addArrangedSubview(showBottomSheetButton)
    stackView.addArrangedSubview(customEntryButton)
    stackView.addArrangedSubview(entryBannerView)
    stackView.addArrangedSubview(spacingView)
    view.addSubview(activityIndicator)
    
    let buttonsToSetupAppearance = [
      showFabButton,
      showBannerButton,
      showPopupButton,
      showBottomSheetButton,
      customEntryButton
    ]
    
    buttonsToSetupAppearance.forEach { button in
      setupAppearanceOfButton(button: button)
    }
    
    stackView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      stackView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
      stackView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor),
      stackView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor),
      stackView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -100),
    ])
    
    entryBannerView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      entryBannerView.heightAnchor.constraint(equalTo: entryBannerView.widthAnchor, multiplier: 112.0/351.0),
    ])
    
    spacingView.setContentHuggingPriority(.defaultLow, for: .vertical)
    spacingView.setContentCompressionResistancePriority(.defaultLow, for: .vertical)
    
    activityIndicator.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      activityIndicator.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
      activityIndicator.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor),
      activityIndicator.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor),
      activityIndicator.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor),
    ])
  }
  
  private func setupAppearanceOfButton(button: UIButton) {
    button.backgroundColor = .systemBlue
    button.layer.cornerRadius = 8
    button.setTitleColor(.white, for: .normal)
    button.titleLabel?.font = UIFont.systemFont(ofSize: 16)
    button.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      button.heightAnchor.constraint(equalToConstant: 48)
    ])
  }
  
  private func loadEntryPoints() {
    activityIndicator.startAnimating()
    
    BuzzEntryPoint.shared.load(
      onSuccess: { [weak self] _ in
        self?.activityIndicator.stopAnimating()
      },
      onFailure: { [weak self] _ in
        self?.activityIndicator.stopAnimating()
      }
    )
  }
  
  @objc
  private func showFabButtonTapped() {
    guard BuzzEntryPoint.shared.canShow(type: .fab) else { return }
    guard !isFabPresented else { return }
    BuzzEntryPoint.shared.showFab(on: self)
    isFabPresented = true
  }
  
  @objc
  private func showBannerButtonTapped() {
    guard BuzzEntryPoint.shared.canShow(type: .banner) else { return }
    entryBannerView.isHidden = false
    BuzzEntryPoint.shared.showBanner(on: entryBannerView)
  }
  
  @objc
  private func showPopupButtonTapped() {
    guard BuzzEntryPoint.shared.canShow(type: .popup) else { return }
    BuzzEntryPoint.shared.showPopup(on: self)
  }
  
  @objc
  private func showBottomSheetButtonTapped() {
    guard BuzzEntryPoint.shared.canShow(type: .bottomSheet) else { return }
    BuzzEntryPoint.shared.showBottomSheet(on: self)
  }
  
  @objc
  private func customEntryButtonTapped() {
    BuzzEntryPoint.shared.customEntryPointClicked(identifier: "YOUR_CUSTOM_ENTRY_IDENTIFIER", on: self)
  }
}
