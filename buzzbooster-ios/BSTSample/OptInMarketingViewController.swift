import UIKit
import BuzzBooster
import Toast

final class OptInMarketingViewController: UIViewController {
  var stackView: UIStackView!
  var textView: UILabel!
  var optInMarketingButton: UIButton!
  var backButton: UIButton!
  
  init() {
    super.init(nibName: nil, bundle: nil)
    self.modalPresentationStyle = .fullScreen
  }
  
  required init?(coder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }
  
  override func viewDidLoad() {
    super.viewDidLoad()
    setupView()
    setupLayout()
    bindEvent()
  }
  
  func setupView() {
    textView = UILabel()
    textView.text = "This is Sample App's Opt In Marketing Page"
    textView.textColor = .systemBlue
    
    optInMarketingButton = UIButton(type: .system)
    optInMarketingButton.setTitle("Opt In Marketing? Click this.", for: .normal)
    setButtonAttributes(button: optInMarketingButton)
    
    backButton = UIButton(type: .system)
    backButton.setTitle("Go Back", for: .normal)
    setButtonAttributes(button: backButton)

    view.addSubview(textView)
    view.addSubview(optInMarketingButton)
    view.addSubview(backButton)
  }
  
  func setButtonAttributes(button: UIButton) {
    button.backgroundColor = .systemBlue
    button.layer.cornerRadius = 8
    button.setTitleColor(.white, for: .normal)
  }
  
  func setupLayout() {
    textView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      textView.leadingAnchor.constraint(equalTo: view.layoutMarginsGuide.leadingAnchor),
      textView.trailingAnchor.constraint(equalTo: view.layoutMarginsGuide.trailingAnchor),
      textView.topAnchor.constraint(equalTo: view.layoutMarginsGuide.topAnchor),
      textView.heightAnchor.constraint(equalToConstant: 48)
    ])
      
    optInMarketingButton.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      optInMarketingButton.leadingAnchor.constraint(equalTo: view.layoutMarginsGuide.leadingAnchor),
      optInMarketingButton.trailingAnchor.constraint(equalTo: view.layoutMarginsGuide.trailingAnchor),
      optInMarketingButton.topAnchor.constraint(equalTo: textView.bottomAnchor),
      optInMarketingButton.heightAnchor.constraint(equalToConstant: 48)
    ])
    
    backButton.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      backButton.leadingAnchor.constraint(equalTo: view.layoutMarginsGuide.leadingAnchor),
      backButton.trailingAnchor.constraint(equalTo: view.layoutMarginsGuide.trailingAnchor),
      backButton.topAnchor.constraint(equalTo: optInMarketingButton.bottomAnchor),
      backButton.heightAnchor.constraint(equalToConstant: 48)
    ])
  }
  
  func bindEvent() {
    optInMarketingButton.addTarget(self, action: #selector(optInMarketingButtonAction), for: .touchUpInside)
    backButton.addTarget(self, action: #selector(backButtonAction), for: .touchUpInside)
  }
  
  @objc func optInMarketingButtonAction() {
    BuzzBooster.sendEvent(withEventName: "bb_opt_in_marketing")
  }
  
  @objc func backButtonAction() {
    dismiss(animated: true)
  }
}
